package Task_1;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

class FolderSearchTask extends RecursiveTask<HashMap<Integer, Integer>> {
    private final Folder folder;

    FolderSearchTask(Folder folder) {
        super();
        this.folder = folder;
    }

    @Override
    protected HashMap<Integer, Integer> compute() {
        var frequenciesOfWordLengths = new HashMap<Integer, Integer>();

        List<RecursiveTask<HashMap<Integer, Integer>>> forks = new LinkedList<>();

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

        for (var task : forks) {
            var taskFrequencies = task.join();

            taskFrequencies.forEach((key, value) ->
                    frequenciesOfWordLengths.merge(key, value, Integer::sum));
        }

        return frequenciesOfWordLengths;
    }
}
