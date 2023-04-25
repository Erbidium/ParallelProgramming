package Task_1;

import java.util.HashMap;
import java.util.concurrent.ForkJoinPool;

public class ParallelFrequenciesSearchTask implements IFrequenciesSearchTask {
    private final ForkJoinPool forkJoinPool = new ForkJoinPool();

    @Override
    public HashMap<Integer, Integer> countFrequencies(Folder folder) {
        return forkJoinPool.invoke(new FolderSearchTask(folder));
    }
}
