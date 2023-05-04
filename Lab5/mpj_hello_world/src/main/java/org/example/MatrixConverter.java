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
}
