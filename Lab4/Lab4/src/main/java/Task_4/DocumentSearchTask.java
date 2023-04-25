package Task_4;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.RecursiveTask;

class DocumentSearchTask extends RecursiveTask<Map<String, Map<String, Integer>>> {
    private final Document document;

    private Set<String> keywords;

    DocumentSearchTask(Document document, Set<String> keywords) {
        super();
        this.document = document;
        this.keywords = keywords;
    }

    @Override
    protected Map<String, Map<String, Integer>> compute() {
        var foundKeywords = WordsCounter.getKeywords(document, keywords);

        var result = new HashMap<String, Map<String, Integer>>();
        result.put(document.getPath(), foundKeywords);

        return result;
    }
}