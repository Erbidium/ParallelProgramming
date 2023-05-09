namespace MatrixMultiplicationWebApp.Core;

public class Result {
    private int[][] matrix;

    public Result(int matrixSize)
    {
        matrix = new int[matrixSize][];
        for (int i = 0; i < matrix.Length; i++)
        {
            matrix[i] = new int[matrixSize];
        }
    }

    public Result(int[][] matrix)
    {
        this.matrix = matrix;
    }

    public int[][] GetMatrix() {
        return matrix;
    }

    public void WriteValueToCell(int cellValue, int row, int column)
    {
        matrix[row][column] = cellValue;
    }

    public void AddValueToCell(int cellValue, int row, int column)
    {
        matrix[row][column] += cellValue;
    }

    public void AddSubMatrix(int[][] subMatrix, int row, int column)
    {
        for (int i = 0; i < subMatrix.Length; i++)
        {
            for (int j = 0; j < subMatrix[0].Length; j++)
            {
                matrix[row + i][column + j] += subMatrix[i][j];
            }
        }
    }
}