import java.util.concurrent.atomic.AtomicInteger;

/*
    Ques-Find race conditions in a shared counter (Synchronization)
    Race Condition using 2 threads and working on same variable
    so incrementing the value on this variable will result in inconsistent result of counter variable
    To resolve this we have 3 approach
        1. Use Syncronized and wrap the counter increment
        2. Use Atomic variables to work on counter and achieve concurrency for the same
 */
public class Ques7 {
    private static final AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        final int COUNTER_FINAL = 1_000_000;
        Thread t1 = new Thread(() -> {
            for (int i = 1; i <= COUNTER_FINAL; i++)
                    counter.getAndIncrement();

        }, "t1");

        Thread t2 = new Thread(() -> {
            for (int i = 1; i <= COUNTER_FINAL; i++)
                counter.getAndIncrement();
        }, "t2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(counter.get());
    }
}
