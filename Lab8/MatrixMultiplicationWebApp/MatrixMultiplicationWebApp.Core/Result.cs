namespace MatrixMultiplicationWebApp.Core;

public class Result {
    private readonly int[][] _matrix;

    public Result(int matrixSize)
    {
        _matrix = new int[matrixSize][];
        for (int i = 0; i < _matrix.Length; i++)
        {
            _matrix[i] = new int[matrixSize];
        }
    }

    public Result(int[][] matrix)
    {
        _matrix = matrix;
    }

    public int[][] GetMatrix() {
        return _matrix;
    }

    public void WriteValueToCell(int cellValue, int row, int column)
    {
        _matrix[row][column] = cellValue;
    }

    public void AddValueToCell(int cellValue, int row, int column)
    {
        _matrix[row][column] += cellValue;
    }

    public void AddSubMatrix(int[][] subMatrix, int row, int column)
    {
        for (int i = 0; i < subMatrix.Length; i++)
        {
            for (int j = 0; j < subMatrix[0].Length; j++)
            {
                _matrix[row + i][column + j] += subMatrix[i][j];
            }
        }
    }
}