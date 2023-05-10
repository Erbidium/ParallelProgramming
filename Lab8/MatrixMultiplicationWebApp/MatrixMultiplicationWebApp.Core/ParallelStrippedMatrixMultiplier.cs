namespace MatrixMultiplicationWebApp.Core;

public class ParallelStrippedMatrixMultiplier : IMatrixMultiplier
{
    private int? _threadsNumber;
    
    public ParallelStrippedMatrixMultiplier()
    {
    }
    
    public ParallelStrippedMatrixMultiplier(int threadsNumber)
    {
        _threadsNumber = threadsNumber;
    }

    public Result Multiply(int[][] matrixA, int[][] matrixB) {
        var matrixBColumns = MatrixFunctions.GetTransposed(matrixB);

        int matrixSize = matrixA.Length;

        var columnIndices = new int[matrixSize];

        for (int i = 0; i < matrixSize; i++)
        {
            columnIndices[i] = i;
        }

        var result = new Result(matrixSize);

        var parallelOptions = new ParallelOptions { MaxDegreeOfParallelism = _threadsNumber ?? 8 };

        void Task(int rowIndex)
        {
            var row = matrixA[rowIndex];
            var column = matrixBColumns[columnIndices[rowIndex]];

            int multiplicationResult = 0;
            for (int k = 0; k < row.Length; k++)
            {
                multiplicationResult += row[k] * column[k];
            }

            result.WriteValueToCell(multiplicationResult, rowIndex, columnIndices[rowIndex]);
        }

        for (int i = 0; i < matrixSize; i++)
        {
            Parallel.For(0, matrixSize, parallelOptions, Task);

            var lastIndex = columnIndices[matrixSize - 1];
            for (int j = matrixSize - 2; j >= 0; j--) {
                columnIndices[j + 1] = columnIndices[j];
            }
            columnIndices[0] = lastIndex;
        }

        return result;
    }
}