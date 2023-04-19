package org.example;

public class SequentialMatrixMultiplier implements IMatrixMultiplier {
    @Override
    public Result Multiply(int[][] matrixA, int[][] matrixB) {
        var resultMatrix = MatrixFunctions.Multiply(matrixA, matrixB);

        return new Result(resultMatrix);
    }
 }
