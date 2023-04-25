package Task_2;

public class MatrixFunctions {
    public static int[][] GetTransposed(int [][] matrix)
    {
        var rows = matrix.length;
        var columns = matrix[0].length;

        var transposedMatrix = new int[columns][rows];

        for(int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns; j++)
            {
                transposedMatrix[j][i] = matrix[i][j];
            }
        }

        return transposedMatrix;
    }

    public static int[][][][] SplitMatrixOnBlocks(int[][] matrix, int blockSize)
    {
        int matrixSize = matrix.length;

        var blockNumber = matrixSize / blockSize;

        var subMatrices = new int[blockNumber][blockNumber][][];

        for (int i = 0; i < blockNumber; i++)
        {
            for (int j = 0; j < blockNumber; j++)
            {
                int[][] subMatrix = new int[blockSize][blockSize];
                for (int k = 0; k < blockSize; k++)
                {
                    var subMatrixRow = new int[blockSize];
                    System.arraycopy(matrix[i * blockSize + k], j * blockSize, subMatrixRow, 0, blockSize);
                    subMatrix[k] = subMatrixRow;
                }

                subMatrices[i][j] = subMatrix;
            }
        }

        return subMatrices;
    }

    public static int[][] Multiply(int[][] matrixA, int[][] matrixB) {
        int matrixSize = matrixA.length;

        var result = new int[matrixSize][matrixSize];

        for (int i = 0; i < matrixA.length; i++) {
            for (int j = 0; j < matrixB[0].length; j++) {
                for (int k = 0; k < matrixA[0].length; k++) {
                    result[i][j] += matrixA[i][k] * matrixB[k][j];
                }
            }
        }

        return result;
    }

    public static boolean MatricesEqual(int[][] matrixA, int[][] matrixB)
    {
        if (matrixA.length != matrixB.length || matrixA[0].length != matrixB[0].length)
            return false;

        var rows = matrixA.length;
        var columns = matrixA[0].length;

        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns; j++)
            {
                if (matrixA[i][j] != matrixB[i][j])
                    return false;
            }
        }

        return true;
    }
}