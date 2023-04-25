package org.example;

import java.util.concurrent.RecursiveTask;

class DocumentSearchTask extends RecursiveTask<Long> {
    private final Document document;
    private final String searchedWord;

    DocumentSearchTask(Document document, String searchedWord) {
        super();
        this.document = document;
        this.searchedWord = searchedWord;
    }

    @Override
    protected Long compute() {
        return WordCounter.occurrencesCount(document, searchedWord);
    }
}