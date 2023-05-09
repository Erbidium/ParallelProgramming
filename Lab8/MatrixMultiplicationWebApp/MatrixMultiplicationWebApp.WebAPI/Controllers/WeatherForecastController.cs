using MatrixMultiplicationWebApp.WebAPI.DTO;
using Microsoft.AspNetCore.Mvc;

namespace MatrixMultiplicationWebApp.WebAPI.Controllers;

[ApiController]
[Route("[controller]")]
public class WeatherForecastController : ControllerBase
{
    [HttpPost("multiply-random-matrices/{matrixSize:int}")]
    public int[][] MultiplyRandomMatrices(int matrixSize)
    {
        return new[] { new [] { 1 }, new [] { 1 }, new [] { 1 } };
    }

    [HttpPost("multiply-given-matrices/")]
    public int[][] MultiplyRandomMatrices(MultiplicationDataDto multiplicationData)
    {
        return new[] { new [] { 1 }, new [] { 1 }, new [] { 1 } };
    }
}