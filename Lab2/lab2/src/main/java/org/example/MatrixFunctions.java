package org.example;

public class MatrixFunctions {
    public static float[][] GetTransposed(float [][] matrix)
    {
        var rows = matrix.length;
        var columns = matrix[0].length;

        var transposedMatrix = new float[columns][rows];

        for(int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns; j++)
            {
                transposedMatrix[j][i] = matrix[i][j];
            }
        }

        return transposedMatrix;
    }

    public static float[][][][] SplitMatrixOnBlocks(float[][] matrix, int blockSize)
    {
        int matrixSize = matrix.length;

        var blockNumber = matrixSize / blockSize;

        var subMatrices = new float[blockNumber][blockNumber][][];

        for (int i = 0; i < blockNumber; i++)
        {
            for (int j = 0; j < blockNumber; j++)
            {
                float[][] subMatrix = new float[blockSize][blockSize];
                for (int k = 0; k < blockSize; k++)
                {
                    var subMatrixRow = new float[blockSize];
                    System.arraycopy(matrix[i * blockSize], j * blockSize, subMatrixRow, 0, blockSize);
                    subMatrix[k] = subMatrixRow;
                }

                subMatrices[i][j] = subMatrix;
            }
        }

        return subMatrices;
    }

    public static float[][] Multiply(float[][] matrixA, float[][] matrixB) {
        int matrixSize = matrixA.length;

        var result = new float[matrixSize][matrixSize];

        for (int i = 0; i < matrixA.length; i++) {
            for (int j = 0; j < matrixB[0].length; j++) {
                for (int k = 0; k < matrixA[0].length; k++) {
                    result[i][j] += matrixA[i][k] * matrixB[k][j];
                }
            }
        }

        return result;
    }
}
