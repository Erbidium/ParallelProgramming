package org.example;

import java.util.concurrent.Callable;

public class StrippedTask implements Callable<Float> {
    private float[] row;

    private float[] column;
    public StrippedTask(float[] row, float[] column)
    {
        this.row = row;
        this.column = column;
    }

    @Override
    public Float call() {
        float result = 0;
        for (int i = 0; i < row.length; i++)
        {
            result += row[i] * column[i];
        }

        return result;
    }
}
