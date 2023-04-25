package org.example;

import java.util.concurrent.ForkJoinPool;

public class WordCounter {
    private final ForkJoinPool forkJoinPool =
            new ForkJoinPool();


    Long countOccurrencesInParallel(Folder folder, String searchedWord) {
        return forkJoinPool.invoke(new FolderSearchTask(folder, searchedWord));
    }

    static String[] wordsIn(String line) {
        return line.trim().split("(\\s|\\p{Punct})+");
    }

    static Long occurrencesCount(	Document document,
                              String searchedWord) {
        long count = 0;
        for (String line : document.getLines()) {
            for (String word : wordsIn(line)) {
                if (searchedWord.equals(word)) {
                    count++;
                }
            }
        }
        return count;
    }

    Long countOccurrencesOnSingleThread( Folder folder,
                                         String searchedWord) {
        long count = 0;
        for (Folder subFolder : folder.getSubFolders()) {
            count = count + countOccurrencesOnSingleThread(
                    subFolder, searchedWord);
        }
        for (Document document : folder.getDocuments()) {
            count = count + occurrencesCount(document,
                    searchedWord);
        }
        return count;
    }
}


