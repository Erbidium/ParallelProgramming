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

    final int[] items;
    int currentItemsCount;

    public Drop(int bufferSize)
    {
        items = new int[bufferSize];
    }

    public void put(int value) {
        lock.lock();
        try {
            while (currentItemsCount == items.length) {
                notFull.await();
            }

            items[currentItemsCount++] = value;
            producedMessagesCount++;

            notEmpty.signalAll();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public int take() {
        lock.lock();
        try {
            while (currentItemsCount == 0) {
                notEmpty.await();
            }

            int value = items[--currentItemsCount];
            consumedMessagesCount++;

            notFull.signalAll();

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
