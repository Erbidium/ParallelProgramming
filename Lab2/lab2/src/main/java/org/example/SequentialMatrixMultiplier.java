package org.example;

public class SequentialMatrixMultiplier implements IMatrixMultiplier {
    @Override
    public Result Multiply(float[][] matrixA, float[][] matrixB) {
        var resultMatrix = MatrixFunctions.Multiply(matrixA, matrixB);

        return new Result(resultMatrix);
    }
 }
