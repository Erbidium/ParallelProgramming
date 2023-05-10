namespace Client;

public static class MatrixPrinter
{
    public static void Print(int[][] matrix)
    {
        foreach (var row in matrix)
        {
            foreach (var element in row)
            {
                Console.Write($"{element} ");
            }
            Console.WriteLine();
        }
    }
}