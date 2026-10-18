import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

// 5	Implement a producer-consumer problem	Blocking queues
public class Ques5 {
    public static void main(String[] args) {
        // below data structure means it can hold upto 3 element at the same time
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(3);

        Thread producer = new Thread(() -> {
            int value = 1;
            while (value <= 100) {
                System.out.println(Thread.currentThread().getName() + " : " + value);
                value++;
                try {
                    queue.put(value);
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "producer-thread");
        Thread consumer = new Thread(() -> {
            while (true) {
                try {
                    Integer take = queue.take();
                    System.out.println(Thread.currentThread().getName() + " : " + take);
                    if (take == 100)
                        break;
                    Thread.currentThread().sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "consumer-thread");

        producer.start();
        consumer.start();
    }

}
