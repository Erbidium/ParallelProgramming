package Task_2;

public class Main {
    public static void main(String[] args) {
        var threadPoolFoxMatrixMultiplier = new ThreadPoolFoxMatrixMultiplier();

        var matrixA = MatrixGenerator.GenerateMatrixFilledWithValue(3000, 1);
        var matrixB = MatrixGenerator.GenerateMatrixFilledWithValue(3000, 1);

        var startTime = System.currentTimeMillis();

        var result = threadPoolFoxMatrixMultiplier.Multiply(matrixA, matrixB);

        var endTime = System.currentTimeMillis();

        var executionTime = endTime - startTime;
        System.out.println(executionTime);

        //MatrixPrinter.Print(result.getMatrix());
    }
}