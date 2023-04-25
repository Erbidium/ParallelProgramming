package Task_4;

import java.util.*;
import java.util.concurrent.RecursiveTask;

class FolderSearchTask extends RecursiveTask<Map<String, Map<String, Integer>>> {
    private final Folder folder;

    private Set<String> keywords;

    FolderSearchTask(Folder folder, Set<String> keywords) {
        super();
        this.folder = folder;
        this.keywords = keywords;
    }

    @Override
    protected Map<String, Map<String, Integer>> compute() {
        var keywords = new HashMap<String, Map<String, Integer>>();
        List<RecursiveTask<Map<String, Map<String, Integer>>>> forks = new LinkedList<>();

        for (Folder subFolder : folder.getSubFolders()) {
            FolderSearchTask task = new FolderSearchTask(subFolder, this.keywords);
            forks.add(task);
            task.fork();
        }

        for (Document document : folder.getDocuments()) {
            DocumentSearchTask task = new DocumentSearchTask(document, this.keywords);
            forks.add(task);
            task.fork();
        }

        for (var task : forks) {
            keywords.putAll(task.join());
        }

        return keywords;
    }
}
