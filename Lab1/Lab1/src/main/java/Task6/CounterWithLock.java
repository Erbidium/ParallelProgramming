package Task6;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CounterWithLock implements ICounter {
    private final Lock lock = new ReentrantLock();
    private int value = 0;

    public void increment() {
        try
        {
            lock.lock();
            value++;
        }
        finally
        {
            lock.unlock();
        }
    }

    public void decrement() {
        try
        {
            lock.lock();
            value--;
        }
        finally
        {
            lock.unlock();
        }
    }

    public int getValue() {
        return value;
    }
}
