package Task_2;

import java.util.concurrent.*;

public class ForkJoinFoxMatrixMultiplier implements IMatrixMultiplier {
    private int threadsNumber;

    public ForkJoinFoxMatrixMultiplier(int threadsNumber)
    {
        this.threadsNumber = threadsNumber;
    }

    public ForkJoinFoxMatrixMultiplier()
    {
        this.threadsNumber = 4;
    }

    private final ForkJoinPool forkJoinPool = new ForkJoinPool();

    public int getThreads() {
        return threadsNumber;
    }

    public void setThreads(int threads) {
        threadsNumber = threads;
    }

    @Override
    public Result Multiply(int[][] matrixA, int[][] matrixB) {
        return new Result(forkJoinPool.invoke(new FoxRecursiveSubmatrixTask(matrixA, matrixB)));
    }
}
