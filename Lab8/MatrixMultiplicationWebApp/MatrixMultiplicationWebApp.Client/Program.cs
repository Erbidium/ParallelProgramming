using System.Diagnostics;
using Client;

var attemptsCount = 5;
double timeSum = 0;

for (int i = 0; i < attemptsCount; i++)
{
    var startTime = Stopwatch.GetTimestamp();
    
    //var resultMatrix = await MatricesMultiplicationRequestSender.SendMultiplicationRequestWithSize(1000);
    var resultMatrix = await MatricesMultiplicationRequestSender.SendRequestWithMatrices(500);
    
    var totalTime = Stopwatch.GetElapsedTime(startTime);
    timeSum += totalTime.TotalMilliseconds;
    
    //MatrixPrinter.Print(resultMatrix);
}

Console.WriteLine(timeSum / attemptsCount);