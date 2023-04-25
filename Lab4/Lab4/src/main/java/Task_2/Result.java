package Task_2;

public class Result {
    private int[][] matrix;
    public Result(int matrixSize)
    {
        matrix = new int[matrixSize][matrixSize];
    }

    public Result(int[][] matrix)
    {
        this.matrix = matrix;
    }

    public int[][] getMatrix() {
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
        for (int i = 0; i < subMatrix.length; i++)
        {
            for (int j = 0; j < subMatrix[0].length; j++)
            {
                matrix[row + i][column + j] += subMatrix[i][j];
            }
        }
    }
}
