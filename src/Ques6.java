// 6. Demonstrate a deadlock and fix it	Lock ordering
// Now if we see clearly in code we have 2 thread and in both the thread we are acquiring lock on object a and b
// to resolve this we can solve using 2 approach
// 1. rearrange the acquiring the lock A and B in the same order
// 2. use the reentrant lock or try lock for the same
public class Ques6 {
    private static final Object lockA = new Object();
    private static final Object lockB = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (lockA) {
                System.out.println("Thread 1: Acquired LockA");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (lockB) {
                    System.out.println("Thread 1: Acquired LockA & LockB");
                }
            }
        }, "Thread-1"
        );
        Thread t2 = new Thread(() -> {
            synchronized (lockB) {
                System.out.println("Thread 2: Acquired LockB");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                synchronized (lockA) {
                    System.out.println("Thread 2: Acquired LockA & LockB");
                }
            }
        }, "Thread-2");

        t1.start();
        t2.start();

    }
}
