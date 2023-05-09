namespace MatrixMultiplicationWebApp.Core;

public class ParallelStrippedMatrixMultiplier : IMatrixMultiplier
{
    public ParallelStrippedMatrixMultiplier()
    {
    }
    
    public ParallelStrippedMatrixMultiplier(int threadsNumber)
    {
        ThreadPool.GetMinThreads(out _, out var IOMin);
        ThreadPool.SetMinThreads(threadsNumber, IOMin);
        
        ThreadPool.GetMaxThreads(out _, out var IOMax);
        ThreadPool.SetMaxThreads(threadsNumber, IOMax);
    }

    public async Task<Result> Multiply(int[][] matrixA, int[][] matrixB) {
        var matrixBColumns = MatrixFunctions.GetTransposed(matrixB);

        int matrixSize = matrixA.Length;

        var columnIndices = new int[matrixSize];

        for (int i = 0; i < matrixSize; i++)
        {
            columnIndices[i] = i;
        }

        var tasks = new List<Task<int>>();

        var result = new Result(matrixSize);

        for (int i = 0; i < matrixSize; i++)
        {
            for (int j = 0; j < matrixSize; j++)
            {
                var index = j;
                tasks.Add(Task.Run(() =>
                {
                    var row = matrixA[index];
                    var column = matrixBColumns[columnIndices[index]];

                    int multiplicationResult = 0;
                    for (int k = 0; k < row.Length; k++)
                    {
                        multiplicationResult += row[k] * column[k];
                    }

                    return multiplicationResult;
                }));
            }

            for (int j = 0; j < matrixSize; j++) {
                result.WriteValueToCell(await tasks[j], j, columnIndices[j]);
            }

            tasks.Clear();

            var lastIndex = columnIndices[matrixSize - 1];
            for (int j = matrixSize - 2; j >= 0; j--) {
                columnIndices[j + 1] = columnIndices[j];
            }
            columnIndices[0] = lastIndex;
        }

        return result;
    }
}