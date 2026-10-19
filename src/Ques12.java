import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
* Ques12	Implement a bounded blocking queue	Coordination and capacity
*
* */

public class Ques12 {
    public static void main(String[] args) throws InterruptedException {
        BoundedBlockingQueue<Integer> integerBoundedBlockingQueue = new BoundedBlockingQueue<>(10);
        integerBoundedBlockingQueue.put(10);
        int size = integerBoundedBlockingQueue.size();
        Integer take = integerBoundedBlockingQueue.take();
        System.out.println(size);
    }
}


class BoundedBlockingQueue<T> {
    private final Object[] items;
    private int putIndex;
    private int takeIndex;
    private int count;

    private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public BoundedBlockingQueue(int capacity) {
        if (capacity <= 0)
            throw new IllegalArgumentException("Capacity must be greater than 0");
        this.items = new Object[capacity];
    }

    public void put(T item) throws InterruptedException {
        lock.lockInterruptibly();
        try {
            while (count == items.length)
                notFull.await();
            items[putIndex] = item;
            if (++putIndex == items.length)
                putIndex = 0;
            count++;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public T take() throws InterruptedException {
        lock.lockInterruptibly();
        try {
            while (count == 0)
                notEmpty.await();
            T item = (T) items[takeIndex];
            items[takeIndex] = null;
            if (++takeIndex == items.length)
                takeIndex = 0;
            count--;
            notFull.signal();
            return item;
        } finally {
            lock.unlock();
        }
    }

    public int size() {
        lock.lock();
        try {
            return count;
        } finally {
            lock.unlock();
        }
    }
}