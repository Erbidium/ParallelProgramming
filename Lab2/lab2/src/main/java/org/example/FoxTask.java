package org.example;

import java.util.concurrent.Callable;

public class FoxTask implements Callable<int[][]>  {
    private int[][] firstBlock;

    private int[][] secondBlock;

    public FoxTask(int[][] firstBlock, int[][] secondBlock)
    {
        this.firstBlock = firstBlock;
        this.secondBlock = secondBlock;
    }

    @Override
    public int[][] call() {
        return MatrixFunctions.Multiply(this.firstBlock, this.secondBlock);
    }
}
