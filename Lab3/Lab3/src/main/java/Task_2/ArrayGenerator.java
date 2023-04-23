package Task_2;

import java.util.Random;

public class ArrayGenerator {
    public static int[] GenerateConsecutiveArray(int arraySize)
    {
        var random = new Random();
        var array = new int[arraySize];
        for (int i = 0; i < arraySize; i++)
        {
            array[i] = i + 1;
        }

        return array;
    }
}
