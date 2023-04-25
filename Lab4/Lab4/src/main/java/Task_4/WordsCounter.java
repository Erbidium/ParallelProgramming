package Task_4;

import java.util.*;

public class WordsCounter {
    static String[] wordsIn(String line) {
        return line.trim().split("(\\s|\\p{Punct})+");
    }

    static Map<String, Integer> getKeywords(Document document, Set<String> keywords) {
        var foundKeywords = new HashMap<String, Integer>();

        for (String line : document.getLines()) {
            for (String word : wordsIn(line)) {
                var wordLowerCase = word.toLowerCase();

                if (keywords.contains(wordLowerCase))
                {
                    foundKeywords.compute
                    (
                        wordLowerCase,
                        (key, value) -> (value == null) ? 1 : value + 1
                    );
                }
            }
        }

        return foundKeywords;
    }
}


