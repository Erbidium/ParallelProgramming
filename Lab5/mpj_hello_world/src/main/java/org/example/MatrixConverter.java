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

    public static int[][] ConvertToMatrix(int[] array, int rows, int columns)
    {
        var matrix = new int[rows][columns];

        for (int i = 0; i < rows; i++)
        {
            System.arraycopy(array, i * columns, matrix[i], 0, columns);
        }

        return matrix;
    }
}
