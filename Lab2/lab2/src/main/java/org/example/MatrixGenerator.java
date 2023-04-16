package org.example;

public class MatrixGenerator {
    public float[][] GenerateMatrixFilledWithValue(int matrixSize, float value)
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
}
