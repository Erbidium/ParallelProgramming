namespace MatrixMultiplicationWebApp.Core;

public static class MatrixFunctions
{
    public static int[][] GetTransposed(int [][] matrix)
    {
        var rows = matrix.Length;
        var columns = matrix[0].Length;

        var transposedMatrix = new int[columns][];

        for(int i = 0; i < rows; i++)
        {
            transposedMatrix[i] = new int[rows];
            for (int j = 0; j < columns; j++)
            {
                transposedMatrix[j][i] = matrix[i][j];
            }
        }

        return transposedMatrix;
    }
}