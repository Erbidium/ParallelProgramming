package org.example;

public class MatricesMultiplicationBenchmark {
    private IMatrixMultiplier matrixMultiplier;
    private int[] matrixSizes;
    private int[] threadsCount;

    private int attemptsCount;

    public MatricesMultiplicationBenchmark
    (
            IMatrixMultiplier matrixMultiplier,
            int[] matrixSizes,
            int[] threadsCount,
            int attemptsCount
    )
    {
        this.matrixMultiplier = matrixMultiplier;
        this.threadsCount = threadsCount;
        this.matrixSizes = matrixSizes;
        this.attemptsCount = attemptsCount;
    }

    public int[][] Run() throws Exception {
        var benchmarkResult = new int[matrixSizes.length][threadsCount.length];

        for (int i = 0; i < matrixSizes.length; i++)
        {
            var matrixA = MatrixGenerator.GenerateRandomMatrix(matrixSizes[i]);
            var matrixB = MatrixGenerator.GenerateRandomMatrix(matrixSizes[i]);

            for (int j = 0; j < threadsCount.length; j++)
            {
                if (matrixMultiplier instanceof IThreadsMultiplier)
                {
                    ((IThreadsMultiplier) matrixMultiplier).setThreads(threadsCount[j]);
                }

                int attemptsSum = 0;

                int sequentialSum = 0;

                for (int k = 0; k < attemptsCount; k++)
                {
                    var startTime = System.currentTimeMillis();
                    var parallelMultipliedResult = matrixMultiplier.Multiply(matrixA, matrixB);
                    var endTime = System.currentTimeMillis();

                    var executionTime = endTime - startTime;

                    attemptsSum += executionTime;

                    var sequentialMultiplier = new SequentialMatrixMultiplier();

                    var startSequentialTime = System.currentTimeMillis();
                    var sequentialMultipliedResult = sequentialMultiplier.Multiply(matrixA, matrixB);
                    var endSequentialTime = System.currentTimeMillis();

                    sequentialSum += endSequentialTime - startSequentialTime;

                    if (!MatrixFunctions.MatricesEqual(parallelMultipliedResult.getMatrix(), sequentialMultipliedResult.getMatrix()))
                    {
                        throw new Exception("Parallel algorithm wrong computations result");
                    }
                }

                var finalExecutionTime = attemptsSum / attemptsCount;

                System.out.println("parallel " + finalExecutionTime);

                System.out.println("sequential " + sequentialSum / attemptsCount);

                benchmarkResult[i][j] = finalExecutionTime;
            }
        }

        return benchmarkResult;
    }
}
