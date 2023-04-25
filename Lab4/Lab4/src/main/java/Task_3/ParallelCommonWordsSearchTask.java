package Task_3;

import java.util.Set;
import java.util.concurrent.ForkJoinPool;

public class ParallelCommonWordsSearchTask {
    private final ForkJoinPool forkJoinPool = new ForkJoinPool();

    public Set<String> findCommonWords(Folder folder) {
        return forkJoinPool.invoke(new FolderSearchTask(folder));
    }
}
