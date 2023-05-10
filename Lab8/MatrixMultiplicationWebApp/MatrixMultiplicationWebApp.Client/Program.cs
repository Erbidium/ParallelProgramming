using System.Diagnostics;
using Client;

var matricesMultiplicationRequestSender = new MatricesMultiplicationRequestSender();

var startTime = Stopwatch.GetTimestamp();

var resultMatrix = await matricesMultiplicationRequestSender.SendMultiplicationRequestWithSize(2000);
//var resultMatrix = await matricesMultiplicationRequestSender.SendRequestWithMatrices(2000);

var totalTime = Stopwatch.GetElapsedTime(startTime);
Console.WriteLine(totalTime.TotalSeconds);
//MatrixPrinter.Print(resultMatrix);