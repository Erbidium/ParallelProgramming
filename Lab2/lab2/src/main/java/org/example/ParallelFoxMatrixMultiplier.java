package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParallelFoxMatrixMultiplier implements IMatrixMultiplier {
    private int threadsNumber;

    public ParallelFoxMatrixMultiplier(int threadsNumber)
    {
        this.threadsNumber = threadsNumber;
    }

    @Override
    public Result Multiply(float[][] matrixA, float[][] matrixB) {
        int matrixSize = matrixA.length;

        int blocksSize = 10;
        int processesNumber = (int)Math.pow(matrixSize / (float)blocksSize, 2);

        var blocks = MatrixFunctions.SplitMatrixOnBlocks(matrixA, blocksSize);

        ExecutorService executor = Executors.newFixedThreadPool(threadsNumber);

        var result = new Result(matrixSize);

        executor.shutdown();

        return result;
    }
}
