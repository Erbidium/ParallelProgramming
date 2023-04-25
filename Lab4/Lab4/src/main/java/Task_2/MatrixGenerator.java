package Task_2;

import java.util.Random;

public class MatrixGenerator {
    public static int[][] GenerateMatrixFilledWithValue(int matrixSize, int value)
    {
        var matrix = new int[matrixSize][matrixSize];
        for (int i = 0; i < matrixSize; i++)
        {
            for (int j = 0; j < matrixSize; j++)
            {
                matrix[i][j] = value;
            }
        }

        return matrix;
    }

    public static int[][] GenerateRandomMatrix(int matrixSize)
    {
        var random = new Random();
        var matrix = new int[matrixSize][matrixSize];
        for (int i = 0; i < matrixSize; i++)
        {
            for (int j = 0; j < matrixSize; j++)
            {
                matrix[i][j] = random.nextInt();
            }
        }

        return matrix;
    }
}