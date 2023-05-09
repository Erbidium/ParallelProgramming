namespace MatrixMultiplicationWebApp.Core;

public interface IMatrixMultiplier
{
    public Result Multiply(int[][] matrixA, int[][] matrixB);
}