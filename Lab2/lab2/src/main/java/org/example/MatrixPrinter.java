package org.example;

public class MatrixPrinter {
    public void Print(float[][] matrix)
    {
        for (float[] row : matrix) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(row[j] + " ");
            }
            System.out.println();
        }
    }
}
