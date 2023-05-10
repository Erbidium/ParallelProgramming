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

    public int[][] GetMatrix() {
        return _matrix;
    }

    public void WriteValueToCell(int cellValue, int row, int column)
    {
        _matrix[row][column] = cellValue;
    }
}