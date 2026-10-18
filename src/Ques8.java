/*
    Ques-8	Implement a counter using AtomicInteger	Atomic operations
* */

import java.util.concurrent.atomic.AtomicInteger;

public class Ques8 {
    public static final AtomicInteger counter = new AtomicInteger(0);
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 1; i <= 10; i++)
                counter.getAndIncrement();
        });

        Thread t2 = new Thread(() -> {
            for (int i = 1; i <= 10; i++)
                counter.getAndIncrement();
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(counter.get());
    }
}
