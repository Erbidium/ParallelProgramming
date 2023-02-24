package Task6;
public class Main {
    public static void main(String[] args) {
        Counter counter = new Counter();
        CounterSyncMethod counterSyncMethod = new CounterSyncMethod();
        CounterSyncBlock counterSyncBlock = new CounterSyncBlock();
        RunCounterTest(counter, "Counter: ");
        RunCounterTest(counterSyncMethod, "Counter sync method: ");
        RunCounterTest(counterSyncBlock, "Counter sync block: ");
    }

    private static void RunCounterTest
    (
        ICounter counter,
        String message
    )
    {
        CounterThread counterThreadIncrementing = new CounterThread(counter, true);
        CounterThread counterThreadDecrementing = new CounterThread(counter, false);

        counterThreadIncrementing.start();
        counterThreadDecrementing.start();

        try {
            counterThreadIncrementing.join();
            counterThreadDecrementing.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(message + counter.getValue());
    }
}
