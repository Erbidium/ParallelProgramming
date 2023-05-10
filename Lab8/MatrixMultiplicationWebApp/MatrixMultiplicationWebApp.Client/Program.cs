using System.Diagnostics;
using Client;

var startTime = Stopwatch.GetTimestamp();

//var resultMatrix = await MatricesMultiplicationRequestSender.SendMultiplicationRequestWithSize(2000);
var resultMatrix = await MatricesMultiplicationRequestSender.SendRequestWithMatrices(2000);

var totalTime = Stopwatch.GetElapsedTime(startTime);
Console.WriteLine(totalTime.TotalSeconds);
//MatrixPrinter.Print(resultMatrix);