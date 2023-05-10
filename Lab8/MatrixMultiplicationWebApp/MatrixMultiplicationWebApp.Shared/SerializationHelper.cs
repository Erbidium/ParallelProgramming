using System.Text.Json;

namespace MatrixMultiplicationWebApp.Shared;

public static class SerializationHelper
{
    public static int[][] DeserializeMatrixFromBytes(byte[] serializedBytes)
    {
        var utf8Reader = new Utf8JsonReader(serializedBytes);
        return JsonSerializer.Deserialize<int[][]>(ref utf8Reader)!;
    }
}