package Task_2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class FoxRecursiveSubmatrixTask extends RecursiveTask<int[][]> {
    private int[][] matrixA;

    private int[][] matrixB;

    public FoxRecursiveSubmatrixTask(int[][] matrixA, int[][] matrixB)
    {
        this.matrixA = matrixA;
        this.matrixB = matrixB;
    }

    @Override
    public int[][] compute() {
        int matrixSize = matrixA.length;

        var blockNumber =  2;

        if (matrixSize <= 100)
        {
            return  MatrixFunctions.Multiply(matrixA, matrixB);
        }

        var matrixABlocks = MatrixFunctions.SplitMatrixOnBlocks(matrixA, matrixSize / blockNumber);
        var matrixBBlocks = MatrixFunctions.SplitMatrixOnBlocks(matrixB, matrixSize / blockNumber);

        int[][][][] matrixAOrderedBlocks = new int[blockNumber][blockNumber][][];
        int[][][][] matrixBOrderedBlocks = new int[blockNumber][blockNumber][][];

        ArrayList<FoxRecursiveSubmatrixTask> tasks = new ArrayList<>();

        List<int[][]> calculatedSubBlocks = new ArrayList<>();

        var result = new Result(matrixSize);

        for (int k = 0; k < blockNumber; k++)
        {
            for (int i = 0; i < blockNumber; i++)
            {
                for (int j = 0; j < blockNumber; j++)
                {
                    matrixAOrderedBlocks[i][j] = matrixABlocks[i][(i + k) % blockNumber];
                    matrixBOrderedBlocks[i][j] = matrixBBlocks[(i + k) % blockNumber][j];

                    var subtask = new FoxRecursiveSubmatrixTask(matrixAOrderedBlocks[i][j], matrixBOrderedBlocks[i][j]);
                    tasks.add(subtask);
                    subtask.fork();
                }
            }

            for (var task : tasks) {
                var subMatrix = task.join();
                calculatedSubBlocks.add(subMatrix);
            }

            tasks.clear();

            for (int i = 0; i < blockNumber; i++)
            {
                for (int j = 0; j < blockNumber; j++)
                {
                    result.AddSubMatrix(calculatedSubBlocks.get(i * blockNumber + j) , i * matrixSize / blockNumber, j * matrixSize / blockNumber);
                }
            }
        }

        return result.getMatrix();
    }
}
