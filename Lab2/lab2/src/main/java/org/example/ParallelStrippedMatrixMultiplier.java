package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ParallelStrippedMatrixMultiplier implements IThreadsMultiplier {
    private int threadsNumber;

    public ParallelStrippedMatrixMultiplier(int threadsNumber)
    {
        this.threadsNumber = threadsNumber;
    }

    public ParallelStrippedMatrixMultiplier()
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
        var matrixBColumns = MatrixFunctions.GetTransposed(matrixB);

        int matrixSize = matrixA.length;

        var columnIndices = new int[matrixSize];

        for (int i = 0; i < matrixSize; i++)
        {
            columnIndices[i] = i;
        }

        ExecutorService executor = Executors.newFixedThreadPool(threadsNumber);

        ArrayList<StrippedTask> tasks = new ArrayList<>();

        var result = new Result(matrixSize);

        for (int i = 0; i < matrixSize; i++)
        {
            for (int j = 0; j < matrixSize; j++) {
                tasks.add(new StrippedTask(matrixA[j], matrixBColumns[columnIndices[j]]));
            }

            List<Future<Float>> calculatedElements;
            try {
                calculatedElements = executor.invokeAll(tasks);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            for (int j = 0; j < matrixSize; j++) {
                try {
                    result.WriteValueToCell(calculatedElements.get(j).get(), j, columnIndices[j]);
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }

            tasks.clear();

            var lastIndex = columnIndices[matrixSize - 1];
            for (int j = matrixSize - 2; j >= 0; j--) {
                columnIndices[j + 1] = columnIndices[j];
            }
            columnIndices[0] = lastIndex;
        }

        executor.shutdown();

        return result;
    }
}
