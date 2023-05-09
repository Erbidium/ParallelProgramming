// See https://aka.ms/new-console-template for more information

using System.Text.Json;

int[][]? DeserializeMatrixBytes(byte[] serializedBytes)
{
    var utf8Reader = new Utf8JsonReader(serializedBytes);
    return JsonSerializer.Deserialize<int[][]>(ref utf8Reader);
}

Console.WriteLine("Hello, World!");

using var client = new HttpClient();

var response = await client.PostAsync($"http://localhost:5172/WeatherForecast/multiply-random-matrices/{2000}", null);
if (response.IsSuccessStatusCode)
{
    var bytes = await response.Content.ReadAsByteArrayAsync();
    int[][] resultMatrix = DeserializeMatrixBytes(bytes)!;
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