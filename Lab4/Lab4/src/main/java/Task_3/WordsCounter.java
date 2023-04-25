package Task_3;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class WordsCounter {
    static String[] wordsIn(String line) {
        return line.trim().split("(\\s|\\p{Punct})+");
    }

    static Set<String> getUniqueWords(Document document) {
        var uniqueWords = new HashSet<String>();

        for (String line : document.getLines()) {
            uniqueWords.addAll(Stream.of(wordsIn(line))
                    .map(String::toLowerCase)
                    .filter(word -> word.length() > 1)
                    .toList());
        }

        return uniqueWords;
    }
}


