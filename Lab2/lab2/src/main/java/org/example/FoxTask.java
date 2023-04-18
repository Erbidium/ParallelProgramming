package org.example;

import java.util.concurrent.Callable;

public class FoxTask implements Callable<Float[][]>  {
    private float[][] firstBlock;

    private float[][] secondBlock;

    public FoxTask(float[][] firstBlock, float[][] secondBlock)
    {
        this.firstBlock = firstBlock;
        this.secondBlock = secondBlock;
    }

    @Override
    public Float[][] call() {
        return null;
    }
}
