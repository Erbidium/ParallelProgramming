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
    public async Task<int[][]> MultiplyRandomMatrices(int matrixSize)
    {
        var matrixA = MatrixGenerator.GenerateMatrixFilledWithValue(matrixSize, 1);
        var matrixB = MatrixGenerator.GenerateMatrixFilledWithValue(matrixSize, 1);

        var matrixMultiplier = new ParallelStrippedMatrixMultiplier();

        int[][]? DeserializeMatrixBytes(byte[] serializedBytes)
        {
            var utf8Reader = new Utf8JsonReader(serializedBytes);
            return JsonSerializer.Deserialize<int[][]>(ref utf8Reader);
        }

        var multiplicationResult = await matrixMultiplier.Multiply(matrixA, matrixB);

        var serializedBytes = JsonSerializer.SerializeToUtf8Bytes(multiplicationResult.GetMatrix());

        return DeserializeMatrixBytes(serializedBytes)!;
    }

    [HttpPost("multiply-given-matrices/")]
    public int[][] MultiplyRandomMatrices(MultiplicationDataDto multiplicationData)
    {
        return new[] { new [] { 1 }, new [] { 1 }, new [] { 1 } };
    }
}