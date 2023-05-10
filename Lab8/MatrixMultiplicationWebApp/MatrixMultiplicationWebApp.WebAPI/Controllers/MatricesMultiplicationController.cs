using System.Text.Json;
using MatrixMultiplicationWebApp.Core;
using MatrixMultiplicationWebApp.Shared;
using Microsoft.AspNetCore.Mvc;

namespace MatrixMultiplicationWebApp.WebAPI.Controllers;

[ApiController]
[Route("api/[controller]")]
public class MatricesMultiplicationController : ControllerBase
{
    private readonly IMatrixMultiplier _matrixMultiplier;
    
    public MatricesMultiplicationController(IMatrixMultiplier matrixMultiplier)
    {
        _matrixMultiplier = matrixMultiplier;
    }
    
    
    [HttpPost("multiply-generated-matrices/{matrixSize:int}")]
    public string MultiplyGeneratedMatrices(int matrixSize, bool generateRandomMatrices)
    {
        int[][] matrixA;
        int[][] matrixB;
        if (generateRandomMatrices)
        {
            matrixA = MatrixGenerator.GenerateRandomMatrix(matrixSize);
            matrixB = MatrixGenerator.GenerateRandomMatrix(matrixSize);
        }
        else
        {
            matrixA = MatrixGenerator.GenerateMatrixFilledWithValue(matrixSize, 1);
            matrixB = MatrixGenerator.GenerateMatrixFilledWithValue(matrixSize, 1);
        }

        var multiplicationResult = _matrixMultiplier.Multiply(matrixA, matrixB);

        return MatrixToBase64String(multiplicationResult.GetMatrix());
    }

    [HttpPost("multiply-given-matrices/")]
    [RequestSizeLimit(100_000_000)]
    public string MultiplyGivenMatrices(IFormFileCollection files)
    {
        var matrixA = SerializationHelper.DeserializeMatrixFromBytes(FileToBytes(files[0]));
        var matrixB = SerializationHelper.DeserializeMatrixFromBytes(FileToBytes(files[1]));

        var multiplicationResult = _matrixMultiplier.Multiply(matrixA, matrixB);

        return MatrixToBase64String(multiplicationResult.GetMatrix());
    }
    
    private static byte[] FileToBytes(IFormFile file)
    {
        using var stream = new MemoryStream();
        file.CopyTo(stream);
        return stream.ToArray();
    }

    private string MatrixToBase64String(int[][] matrix)
    {
        var serializedBytes = JsonSerializer.SerializeToUtf8Bytes(matrix);

        return Convert.ToBase64String(serializedBytes);
    }
}