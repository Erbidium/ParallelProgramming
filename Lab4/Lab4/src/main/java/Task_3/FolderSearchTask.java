package Task_3;

import java.util.*;
import java.util.concurrent.RecursiveTask;

class FolderSearchTask extends RecursiveTask<Set<String>> {
    private final Folder folder;

    FolderSearchTask(Folder folder) {
        super();
        this.folder = folder;
    }

    @Override
    protected Set<String> compute() {
        List<RecursiveTask<Set<String>>> forks = new LinkedList<>();

        for (Folder subFolder : folder.getSubFolders()) {
            FolderSearchTask task = new FolderSearchTask(subFolder);
            forks.add(task);
            task.fork();
        }

        for (Document document : folder.getDocuments()) {
            DocumentSearchTask task = new DocumentSearchTask(document);
            forks.add(task);
            task.fork();
        }

        var commonWords = forks.get(0).join();
        for (int i = 1; i < forks.size(); i++) {
            var taskUniqueWords = forks.get(i).join();

            commonWords.retainAll(taskUniqueWords);
        }

        return commonWords;
    }
}
