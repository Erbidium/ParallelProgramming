using System.Text.Json;
using MatrixMultiplicationWebApp.Shared.DTO;
using MatrixMultiplicationWebApp.Core;
using MatrixMultiplicationWebApp.Shared;
using Microsoft.AspNetCore.Mvc;
using System.Web;

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
    public async Task<FileStreamResult> MultiplyGeneratedMatrices(int matrixSize, bool generateRandomMatrices)
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

        var multiplicationResult = await _matrixMultiplier.Multiply(matrixA, matrixB);

        return MatrixToFileStreamResult(multiplicationResult.GetMatrix());
    }

    [HttpPost("multiply-given-matrices/")]
    [RequestSizeLimit(100_000_000)]
    public async Task<FileStreamResult> MultiplyRandomMatrices(IFormFileCollection files)
    {
        int[][]? DeserializeMatrixBytes(byte[] serializedBytes)
        {
            var utf8Reader = new Utf8JsonReader(serializedBytes);
            return JsonSerializer.Deserialize<int[][]>(ref utf8Reader);
        }

        byte[] FileToBytes(IFormFile file)
        {
            using var stream = new MemoryStream();
            file.CopyTo(stream);
            return stream.ToArray();
        }

        var fileA = files[0];
        var fileB = files[1];

        var matrixA = DeserializeMatrixBytes(FileToBytes(fileA));
        var matrixB = DeserializeMatrixBytes(FileToBytes(fileB));

        var multiplicationResult = await _matrixMultiplier.Multiply(matrixA, matrixB);

        return MatrixToFileStreamResult(multiplicationResult.GetMatrix());
    }

    private FileStreamResult MatrixToFileStreamResult(int[][] matrix)
    {
        var serializedBytes = JsonSerializer.SerializeToUtf8Bytes(matrix);

        return File(new MemoryStream(serializedBytes), "application/octet-stream");
    }
}