package Task_2;

public class MatrixPrinter {
    public static void Print(int[][] matrix)
    {
        for (int[] row : matrix) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(row[j] + " ");
            }
            System.out.println();
        }
    }
}