// 1	Print numbers 1–10 using two threads, one printing 1–5 and the other 6–10	Coordination
public class Ques1 {
    private static final int MAX = 10;
    private static int count = 1;
    private static final Object lock = new Object();

    public static void main(String[] args) {
        Thread oddThread = new Thread(() -> {
            while (count <= MAX) {
                synchronized (lock) {
                    if (count % 2 == 0) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    if (count <= MAX) {
                        System.out.println(Thread.currentThread().getName() + " : " + count);
                        count++;
                        lock.notifyAll();
                    }
                }
            }
        }, "OddThread");

        Thread evenThread = new Thread(() -> {
            while (count <= MAX) {
                synchronized (lock) {
                    if (count % 2 == 1) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    if (count <= MAX) {
                        System.out.println(Thread.currentThread().getName() + " : "  + count);
                        count++;
                        lock.notifyAll();
                    }
                }
            }
        }, "EvenThread");

        oddThread.start();
        evenThread.start();
    }
}
