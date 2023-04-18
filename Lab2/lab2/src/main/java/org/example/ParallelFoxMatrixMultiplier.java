package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ParallelFoxMatrixMultiplier implements IMatrixMultiplier, IThreadsMultiplier {
    private int threadsNumber;

    public ParallelFoxMatrixMultiplier(int threadsNumber)
    {
        this.threadsNumber = threadsNumber;
    }

    public ParallelFoxMatrixMultiplier()
    {
        this.threadsNumber = 4;
    }

    @Override
    public int getThreads() {
        return threadsNumber;
    }

    @Override
    public void setThreads(int threads) {
        threadsNumber = threads;
    }

    @Override
    public Result Multiply(float[][] matrixA, float[][] matrixB) {
        int matrixSize = matrixA.length;

        int blockSize = 10;
        var blockNumber = matrixSize / blockSize;

        var matrixABlocks = MatrixFunctions.SplitMatrixOnBlocks(matrixA, blockSize);
        var matrixBBlocks = MatrixFunctions.SplitMatrixOnBlocks(matrixB, blockSize);

        float[][][][] matrixAOrderedBlocks = new float[blockNumber][blockNumber][][];
        float[][][][] matrixBOrderedBlocks = new float[blockNumber][blockNumber][][];

        ArrayList<FoxTask> tasks = new ArrayList<>();

        List<Future<float[][]>> calculatedSubBlocks;

        ExecutorService executor = Executors.newFixedThreadPool(threadsNumber);

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

            try {
                calculatedSubBlocks = executor.invokeAll(tasks);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

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

        executor.shutdown();

        return result;
    }
}
