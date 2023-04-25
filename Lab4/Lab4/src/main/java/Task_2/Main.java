package Task_2;

public class Main {
    public static void main(String[] args) {
        var matrixMultiplier = new ParallelFoxMatrixMultiplier();

        var matrixA = MatrixGenerator.GenerateMatrixFilledWithValue(100, 1);
        var matrixB = MatrixGenerator.GenerateMatrixFilledWithValue(100, 1);
        var result = matrixMultiplier.Multiply(matrixA, matrixB);
        MatrixPrinter.Print(result.getMatrix());
    }
}
