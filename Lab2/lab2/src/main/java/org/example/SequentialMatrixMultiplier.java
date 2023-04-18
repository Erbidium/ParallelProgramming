package org.example;

public class SequentialMatrixMultiplier implements IMatrixMultiplier {
    public Result Multiply(float[][] matrixA, float[][] matrixB) {
        int matrixSize = matrixA.length;

        var result = new Result(matrixSize);

        for (int i = 0; i < matrixA.length; i++) {
            for (int j = 0; j < matrixB[0].length; j++) {
                for (int k = 0; k < matrixA[0].length; k++) {
                    result.AddValueToCell(matrixA[i][k] * matrixB[k][j], i, j);
                }
            }
        }

        return result;
    }
 }
