package org.example;

import java.util.Random;

public class MatrixGenerator {
    public static float[][] GenerateMatrixFilledWithValue(int matrixSize, float value)
    {
        var matrix = new float[matrixSize][matrixSize];
        for (int i = 0; i < matrixSize; i++)
        {
            for (int j = 0; j < matrixSize; j++)
            {
                matrix[i][j] = value;
            }
        }

        return matrix;
    }

    public static float[][] GenerateRandomMatrix(int matrixSize)
    {
        var random = new Random();
        var matrix = new float[matrixSize][matrixSize];
        for (int i = 0; i < matrixSize; i++)
        {
            for (int j = 0; j < matrixSize; j++)
            {
                matrix[i][j] = random.nextFloat();
            }
        }

        return matrix;
    }
}
