using System.Net.Http.Headers;
using System.Text.Json;
using MatrixMultiplicationWebApp.Shared;

namespace Client;

public class MatricesMultiplicationRequestSender
{
    private static async Task<int[][]> SendMultiplicationRequest(string requestUri, HttpContent? content)
    {
        using var client = new HttpClient();
        var response = await client.PostAsync(requestUri, content);
        if (response.IsSuccessStatusCode)
        {
            var base64String = await response.Content.ReadAsStringAsync();
            var bytes = Convert.FromBase64String(base64String);
            int[][] resultMatrix = SerializationHelper.DeserializeMatrixFromBytes(bytes)!;

            return resultMatrix;
        }
    
        throw new Exception($"Error: {response.StatusCode}");
    }
    
    public static async Task<int[][]> SendMultiplicationRequestWithSize(int matrixSize)
    {
        return await SendMultiplicationRequest($"http://localhost:5172/api/MatricesMultiplication/multiply-generated-matrices/{matrixSize}", null);
    }
    
    public static async Task<int[][]> SendRequestWithMatrices(int matrixSize)
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

        var matrixA = MatrixGenerator.GenerateMatrixFilledWithValue(matrixSize, 1);
        var matrixB = MatrixGenerator.GenerateMatrixFilledWithValue(matrixSize, 1);

        using var content = new MultipartFormDataContent();
    
        var serializedMatrixA = JsonSerializer.SerializeToUtf8Bytes(matrixA);
        var serializedMatrixB = JsonSerializer.SerializeToUtf8Bytes(matrixB);

        content.Add(CreateFileContent(new MemoryStream(serializedMatrixA), "matrixA", "application/octet-stream"));
        content.Add(CreateFileContent(new MemoryStream(serializedMatrixB), "matrixB", "application/octet-stream"));

        return await SendMultiplicationRequest("http://localhost:5172/api/MatricesMultiplication/multiply-given-matrices", content);
    }
}