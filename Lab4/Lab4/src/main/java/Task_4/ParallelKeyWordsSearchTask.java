package Task_4;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;

public class ParallelKeyWordsSearchTask {
    private final ForkJoinPool forkJoinPool = new ForkJoinPool();

    public Map<String, Map<String, Integer>> findKeywords(Folder folder, Set<String> keywords) {
        return forkJoinPool.invoke(new FolderSearchTask(folder, keywords));
    }
}
