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

    public float[][] Run()
    {
        var benchmarkResult = new float[matrixSizes.length][threadsCount.length];

        for (int i = 0; i < matrixSizes.length; i++)
        {
            for (int j = 0; j < threadsCount.length; j++)
            {

            }
        }
    }
}
