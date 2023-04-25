package Task_1;

import java.util.HashMap;
import java.util.concurrent.RecursiveTask;

class DocumentSearchTask extends RecursiveTask<HashMap<Integer, Integer>> {
    private final Document document;

    DocumentSearchTask(Document document) {
        super();
        this.document = document;
    }

    @Override
    protected HashMap<Integer, Integer> compute() {
        return FrequenciesCounter.calculateFrequenciesOfWorldLengths(document);
    }
}