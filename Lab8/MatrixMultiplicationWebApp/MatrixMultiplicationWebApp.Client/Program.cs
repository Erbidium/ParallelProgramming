// See https://aka.ms/new-console-template for more information

Console.WriteLine("Hello, World!");

using var client = new HttpClient();

var response = await client.PostAsync($"http://localhost:5172/WeatherForecast/multiply-random-matrices/{20}", null);
if (response.IsSuccessStatusCode)
{
    var content = await response.Content.ReadAsStringAsync();
    Console.WriteLine(content);
}
else
{
    Console.WriteLine($"Error: {response.StatusCode}");
}