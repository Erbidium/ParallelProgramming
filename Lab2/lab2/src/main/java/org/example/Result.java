package org.example;

public class Result {
    private float[][] matrix;
    public Result(int matrixSize)
    {
        matrix = new float[matrixSize][matrixSize];
    }

    public float[][] getMatrix() {
        return matrix;
    }

    public void WriteValueToCell(float cellValue, int row, int column)
    {
        matrix[row][column] = cellValue;
    }

    public void AddValueToCell(float cellValue, int row, int column)
    {
        matrix[row][column] += cellValue;
    }

    public void AddSubMatrix(Float[][] subMatrix, int row, int column)
    {
        for (int i = 0; i < subMatrix.length; i++)
        {
            for (int j = 0; j < subMatrix[0].length; j++)
            {
                matrix[row + i][column + j] += subMatrix[i][j];
            }
        }
    }
}
