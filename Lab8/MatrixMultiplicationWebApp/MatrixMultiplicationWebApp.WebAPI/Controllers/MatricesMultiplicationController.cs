using System.Text.Json;
using System.Text.Json.Serialization;
using MatrixMultiplicationWebApp.Core;
using MatrixMultiplicationWebApp.WebAPI.DTO;
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
    public async Task<FileStreamResult> MultiplyRandomMatrices(MultiplicationDataDto multiplicationData)
    {
        var matrixA = multiplicationData.MatrixA;
        var matrixB = multiplicationData.MatrixB;

        var multiplicationResult = await _matrixMultiplier.Multiply(matrixA, matrixB);

        return MatrixToFileStreamResult(multiplicationResult.GetMatrix());
    }

    private FileStreamResult MatrixToFileStreamResult(int[][] matrix)
    {
        var serializedBytes = JsonSerializer.SerializeToUtf8Bytes(matrix);

        return File(new MemoryStream(serializedBytes), "application/octet-stream");
    }
}