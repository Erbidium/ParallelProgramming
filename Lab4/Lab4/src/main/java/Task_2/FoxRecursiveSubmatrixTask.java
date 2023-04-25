package Task_2;

import java.util.HashMap;
import java.util.concurrent.RecursiveTask;

public class FoxRecursiveSubmatrixTask extends RecursiveTask<int[][]> {
    private int[][] firstBlock;

    private int[][] secondBlock;

    public FoxRecursiveSubmatrixTask(int[][] firstBlock, int[][] secondBlock)
    {
        this.firstBlock = firstBlock;
        this.secondBlock = secondBlock;
    }

    @Override
    protected int[][] compute() {
        return MatrixFunctions.Multiply(this.firstBlock, this.secondBlock);
    }
}
