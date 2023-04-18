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

        int blockSize = 10;
        var blockNumber = matrixSize / blockSize;

        var matrixABlocks = MatrixFunctions.SplitMatrixOnBlocks(matrixA, blockSize);
        var matrixBBlocks = MatrixFunctions.SplitMatrixOnBlocks(matrixB, blockSize);

        float[][][][] matrixAOrderedBlocks = new float[blockNumber][blockNumber][][];
        float[][][][] matrixBOrderedBlocks = new float[blockNumber][blockNumber][][];

        for (int i = 0; i < blockNumber; i++)
        {
            for (int j = 0; j < blockNumber; j++)
            {
                matrixAOrderedBlocks[i][j] = matrixABlocks[i][i];
                matrixBOrderedBlocks[i][j] = matrixBBlocks[i][j];
            }
        }

        ExecutorService executor = Executors.newFixedThreadPool(threadsNumber);

        var result = new Result(matrixSize);

        executor.shutdown();

        return result;
    }
}
