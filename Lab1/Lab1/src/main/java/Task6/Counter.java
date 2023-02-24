package Task6;

public class Counter implements ICounter {
    private int value = 0;

    public int getValue()
    {
        return value;
    }

    public void increment()
    {
        value++;
    }

    public void decrement()
    {
        value--;
    }
}
