package org.example;

import java.util.concurrent.Callable;

public class StrippedTask implements Callable<Integer> {
    private int[] row;

    private int[] column;
    public StrippedTask(int[] row, int[] column)
    {
        this.row = row;
        this.column = column;
    }

    @Override
    public Integer call() {
        int result = 0;
        for (int i = 0; i < row.length; i++)
        {
            result += row[i] * column[i];
        }

        return result;
    }
}
