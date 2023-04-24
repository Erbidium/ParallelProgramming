package Task_2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Drop {
    private int producedMessagesCount;

    private int consumedMessagesCount;

    final Lock lock = new ReentrantLock();
    final Condition notFull  = lock.newCondition();
    final Condition notEmpty = lock.newCondition();

    private final int[] items;

    private int putPtr, takePtr, count;

    public Drop(int bufferSize)
    {
        items = new int[bufferSize];
    }

    public void put(int value) {
        lock.lock();
        try {
            while (count == items.length) {
                notFull.await();
            }

            items[putPtr] = value;
            if (++putPtr == items.length)
                putPtr = 0;

            ++count;
            ++producedMessagesCount;

            notEmpty.signal();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public int take() {
        lock.lock();
        try {
            while (count == 0) {
                notEmpty.await();
            }

            int value = items[takePtr];
            if (++takePtr == items.length)
                takePtr = 0;

            --count;
            ++consumedMessagesCount;

            notFull.signal();
            return value;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public int getConsumedMessagesCount() {
        return consumedMessagesCount;
    }

    public int getProducedMessagesCount() {
        return producedMessagesCount;
    }
}
