package org.example;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParalellStrippedMatrixMultiplier {
    public float[][] Multiply(float[][] matrixA, float[][] matrixB) throws InterruptedException, ExecutionException {
        var matrixBColumns = MatrixFunctions.GetTransposed(matrixB);

        int matrixSize = matrixA.length;

        var rowIndices = new int[matrixSize];
        var columnIndices = new int[matrixSize];

        for (int i = 0; i < matrixSize; i++)
        {
            rowIndices[i] = i;
            columnIndices[i] = i;
        }

        ExecutorService executor = Executors.newFixedThreadPool(4);

        ArrayList<StrippedTask> tasks = new ArrayList<>();

        var result = new Result(matrixSize);

        for (int i = 0; i < matrixSize; i++)
        {
            for (int j = 0; j < matrixSize; j++) {
                tasks.add(new StrippedTask(matrixA[rowIndices[j]], matrixBColumns[columnIndices[j]]));
            }

            var calculatedElements = executor.invokeAll(tasks);

            for (int j = 0; j < matrixSize; j++) {
                result.WriteValueToCell(calculatedElements.get(j).get(), rowIndices[j], columnIndices[j]);
            }

            tasks.clear();

            var lastIndex = columnIndices[matrixSize - 1];
            for (int j = matrixSize - 2; j >= 0; j--) {
                columnIndices[j + 1] = columnIndices[j];
            }
            columnIndices[0] = lastIndex;
        }

        return result.getMatrix();
    }
}
