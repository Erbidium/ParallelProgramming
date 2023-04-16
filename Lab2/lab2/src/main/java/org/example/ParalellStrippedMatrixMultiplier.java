package org.example;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParalellStrippedMatrixMultiplier {
    public float[][] Multiply(float[][] matrixA, float[][] matrixB) throws InterruptedException, ExecutionException {
        var matrixBColumns = MatrixFunctions.GetTransposed(matrixB);

        ExecutorService executor = Executors.newFixedThreadPool(4);

        ArrayList<StrippedTask> tasks = new ArrayList<>();

        float matrixSize = matrixA.length;

        for (int i = 0; i < matrixSize; i++)
        {
            tasks.add(new StrippedTask(matrixA[i], matrixBColumns[i]));
        }

        var calculatedElements = executor.invokeAll(tasks);
        for (var element: calculatedElements) {
            System.out.print(element.get() + " ");
        }

        return new float[2][2];
    }
}
