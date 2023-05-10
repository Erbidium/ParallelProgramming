// See https://aka.ms/new-console-template for more information

using System.Diagnostics;
using System.Net.Http.Headers;
using System.Text.Json;
using MatrixMultiplicationWebApp.Shared;

async Task SendRequestWithSize()
{
    using var client = new HttpClient();

    var startTime = Stopwatch.GetTimestamp();
    var response = await client.PostAsync($"http://localhost:5172/api/MatricesMultiplication/multiply-generated-matrices/{2000}", null);
    if (response.IsSuccessStatusCode)
    {
        var base64String = await response.Content.ReadAsStringAsync();
        var bytes = Convert.FromBase64String(base64String);
        int[][] resultMatrix = SerializationHelper.DeserializeMatrixFromBytes(bytes)!;
        var totalTime = Stopwatch.GetElapsedTime(startTime);
        Console.WriteLine(totalTime.TotalSeconds);
        
        foreach (var row in resultMatrix)
        {
            foreach (var element in row)
            {
                Console.Write($"{element} ");
            }
            Console.WriteLine();
        }
        
    }
    else
    {
        Console.WriteLine($"Error: {response.StatusCode}");
    }
}

async Task SendRequestWithMatrices()
{
    StreamContent CreateFileContent(Stream stream, string fileName, string contentType)
    {
        var fileContent = new StreamContent(stream);
        fileContent.Headers.ContentDisposition = new ContentDispositionHeaderValue("form-data") 
        { 
            Name = "\"files\"", 
            FileName = "\"" + fileName + "\""
        };
        fileContent.Headers.ContentType = new MediaTypeHeaderValue(contentType);            
        return fileContent;
    }
    
    using var client = new HttpClient();

    var startTime = Stopwatch.GetTimestamp();

    var matrixA = MatrixGenerator.GenerateMatrixFilledWithValue(2000, 1);
    var matrixB = MatrixGenerator.GenerateMatrixFilledWithValue(2000, 1);

    using var content = new MultipartFormDataContent();
    
    var serializedMatrixA = JsonSerializer.SerializeToUtf8Bytes(matrixA);
    var serializedMatrixB = JsonSerializer.SerializeToUtf8Bytes(matrixB);

    content.Add(CreateFileContent(new MemoryStream(serializedMatrixA), "matrixA", "application/octet-stream"));
    content.Add(CreateFileContent(new MemoryStream(serializedMatrixB), "matrixB", "application/octet-stream"));

    var response = await client.PostAsync($"http://localhost:5172/api/MatricesMultiplication/multiply-given-matrices", content);
    if (response.IsSuccessStatusCode)
    {
        var base64String = await response.Content.ReadAsStringAsync();
        var bytes = Convert.FromBase64String(base64String);
        int[][] resultMatrix = SerializationHelper.DeserializeMatrixFromBytes(bytes)!;
        var totalTime = Stopwatch.GetElapsedTime(startTime);
        Console.WriteLine(totalTime.TotalSeconds);
        /*foreach (var row in resultMatrix)
        {
            foreach (var element in row)
            {
                Console.Write($"{element} ");
            }
            Console.WriteLine();
        }*/
    }
    else
    {
        Console.WriteLine($"Error: {response.StatusCode}");
    }
}

await SendRequestWithSize();
//await SendRequestWithMatrices();