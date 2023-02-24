package Task6;

public class CounterThread extends Thread {
    private ICounter counter;
    private boolean shouldIncrement;
    public CounterThread(ICounter counter, boolean shouldIncrement)
    {
        this.counter = counter;
        this.shouldIncrement = shouldIncrement;
    }

    @Override
    public void run(){
        for (int i = 0; i < 100000; i++)
        {
            if (shouldIncrement)
            {
                counter.increment();
            }
            else
            {
                counter.decrement();
            }
        }
    }
}
