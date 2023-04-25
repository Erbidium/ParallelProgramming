package Task_2;

import java.util.ArrayList;
import java.util.List;
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
        int matrixSize = matrixA.length;

        int blockSize = 100;
        var blockNumber = matrixSize / blockSize;

        var matrixABlocks = MatrixFunctions.SplitMatrixOnBlocks(matrixA, blockSize);
        var matrixBBlocks = MatrixFunctions.SplitMatrixOnBlocks(matrixB, blockSize);

        int[][][][] matrixAOrderedBlocks = new int[blockNumber][blockNumber][][];
        int[][][][] matrixBOrderedBlocks = new int[blockNumber][blockNumber][][];

        ArrayList<FoxTask> tasks = new ArrayList<>();

        List<Future<int[][]>> calculatedSubBlocks;

        var result = new Result(matrixSize);

        for (int k = 0; k < blockNumber; k++)
        {
            for (int i = 0; i < blockNumber; i++)
            {
                for (int j = 0; j < blockNumber; j++)
                {
                    matrixAOrderedBlocks[i][j] = matrixABlocks[i][(i + k) % blockNumber];
                    matrixBOrderedBlocks[i][j] = matrixBBlocks[(i + k) % blockNumber][j];
                    tasks.add(new FoxTask(matrixAOrderedBlocks[i][j], matrixBOrderedBlocks[i][j]));
                }
            }

            calculatedSubBlocks = forkJoinPool.invokeAll(tasks);
            tasks.clear();

            for (int i = 0; i < blockNumber; i++)
            {
                for (int j = 0; j < blockNumber; j++)
                {
                    try {
                        result.AddSubMatrix(calculatedSubBlocks.get(i * blockNumber + j).get() , i * blockSize, j * blockSize);
                    } catch (InterruptedException | ExecutionException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

        return result;
    }
}
