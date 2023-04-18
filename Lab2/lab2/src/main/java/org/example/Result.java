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
}
