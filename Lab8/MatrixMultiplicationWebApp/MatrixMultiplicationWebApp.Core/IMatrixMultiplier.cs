namespace MatrixMultiplicationWebApp.Core;

public interface IMatrixMultiplier
{
    public Task<Result> Multiply(int[][] matrixA, int[][] matrixB);
}