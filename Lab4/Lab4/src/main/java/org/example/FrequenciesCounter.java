package org.example;

import java.util.HashMap;
import java.util.concurrent.ForkJoinPool;

public class FrequenciesCounter {
    private final ForkJoinPool forkJoinPool = new ForkJoinPool();

    HashMap<Integer, Integer> countFrequenciesInParallel(Folder folder) {
        return forkJoinPool.invoke(new FolderSearchTask(folder));
    }

    static String[] wordsIn(String line) {
        return line.trim().split("(\\s|\\p{Punct})+");
    }

    static HashMap<Integer, Integer> calculateFrequenciesOfWorldLengths(Document document) {
        var frequenciesOfWordLengths = new HashMap<Integer, Integer>();

        for (String line : document.getLines()) {
            for (String word : wordsIn(line)) {
                frequenciesOfWordLengths.compute
                (
                    word.length(),
                    (key, value) -> (value == null) ? 1 : value + 1
                );
            }
        }
        return frequenciesOfWordLengths;
    }

    HashMap<Integer, Integer> countFrequenciesOnSingleThread(Folder folder) {
        var frequenciesOfWordLengths = new HashMap<Integer, Integer>();

        for (Folder subFolder : folder.getSubFolders()) {
            var subFolderFrequencies = countFrequenciesOnSingleThread(subFolder);

            subFolderFrequencies.forEach((key, value) ->
                    frequenciesOfWordLengths.merge(key, value, Integer::sum));
        }
        for (Document document : folder.getDocuments()) {
            var documentFrequencies = calculateFrequenciesOfWorldLengths(document);

            documentFrequencies.forEach((key, value) ->
                    frequenciesOfWordLengths.merge(key, value, Integer::sum));
        }
        return frequenciesOfWordLengths;
    }
}


