package org.example;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MatrixConverter {
    public static int[] ConvertToSingleDimensional(int[][] matrix)
    {
        return Stream.of(matrix)
                .flatMapToInt(IntStream::of)
                .toArray();
    }

    public static int[][] ConvertToMatrix(int[] array, int matrixSize)
    {
        var matrix = new int[matrixSize][matrixSize];

        for (int i = 0; i < matrixSize; i++)
        {
            System.arraycopy(array, i * matrixSize, matrix[i], 0, matrixSize);
        }

        return matrix;
    }
}
