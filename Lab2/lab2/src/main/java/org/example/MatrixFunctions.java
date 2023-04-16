package org.example;

public class MatrixFunctions {
    public float[][] GetTransposed(float [][] matrix)
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
}
