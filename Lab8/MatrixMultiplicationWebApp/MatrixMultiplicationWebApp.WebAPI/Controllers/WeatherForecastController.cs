using System.Text.Json;
using System.Text.Json.Serialization;
using MatrixMultiplicationWebApp.Core;
using MatrixMultiplicationWebApp.WebAPI.DTO;
using Microsoft.AspNetCore.Mvc;

namespace MatrixMultiplicationWebApp.WebAPI.Controllers;

[ApiController]
[Route("[controller]")]
public class WeatherForecastController : ControllerBase
{
    [HttpPost("multiply-random-matrices/{matrixSize:int}")]
    public async Task<FileStreamResult> MultiplyRandomMatrices(int matrixSize)
    {
        var matrixA = MatrixGenerator.GenerateMatrixFilledWithValue(matrixSize, 1);
        var matrixB = MatrixGenerator.GenerateMatrixFilledWithValue(matrixSize, 1);

        var matrixMultiplier = new ParallelStrippedMatrixMultiplier();

        var multiplicationResult = await matrixMultiplier.Multiply(matrixA, matrixB);

        var serializedBytes = JsonSerializer.SerializeToUtf8Bytes(multiplicationResult.GetMatrix());

        return File(new MemoryStream(serializedBytes), "application/octet-stream");
    }

    [HttpPost("multiply-given-matrices/")]
    public int[][] MultiplyRandomMatrices(MultiplicationDataDto multiplicationData)
    {
        return new[] { new [] { 1 }, new [] { 1 }, new [] { 1 } };
    }
}