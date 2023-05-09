using MatrixMultiplicationWebApp.Core;
using MatrixMultiplicationWebApp.WebAPI.DTO;
using Microsoft.AspNetCore.Mvc;

namespace MatrixMultiplicationWebApp.WebAPI.Controllers;

[ApiController]
[Route("[controller]")]
public class WeatherForecastController : ControllerBase
{
    [HttpPost("multiply-random-matrices/{matrixSize:int}")]
    public async Task<int[][]> MultiplyRandomMatrices(int matrixSize)
    {
        var matrixA = MatrixGenerator.GenerateMatrixFilledWithValue(matrixSize, 1);
        var matrixB = MatrixGenerator.GenerateMatrixFilledWithValue(matrixSize, 1);

        var matrixMultiplier = new ParallelStrippedMatrixMultiplier();

        var multiplicationResult = await matrixMultiplier.Multiply(matrixA, matrixB);

        return multiplicationResult.GetMatrix();
    }

    [HttpPost("multiply-given-matrices/")]
    public int[][] MultiplyRandomMatrices(MultiplicationDataDto multiplicationData)
    {
        return new[] { new [] { 1 }, new [] { 1 }, new [] { 1 } };
    }
}