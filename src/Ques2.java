
// 2	Print odd and even numbers using two threads	wait() / notify()
public class Ques2 {
    private static final int MAX = 10;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 1; i < (MAX / 2) + 1; i++)
                System.out.println(Thread.currentThread().getName() + " : " + i);
        }, "t1");
        Thread t2 = new Thread(() -> {
            for (int i = (MAX / 2) + 1; i <= MAX; i++)
                System.out.println(Thread.currentThread().getName() + " : " + i);
        }, "t2");

        t1.start();
        t1.join();
        t2.start();
    }
}
