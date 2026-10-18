
/*
    Ques9-Execute five tasks using a fixed thread pool	ExecutorService
* */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Ques9 {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(5);

        for (int i = 1; i <= 5; i++) {
            int taskId = i;
            service.submit(() -> {
                System.out.println("Task Submitted by TaskId " + taskId + ", Thread Name: " + Thread.currentThread().getName());
                try {
                    Thread.currentThread().sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        service.shutdown();
        System.out.println("All tasks finished. Thread pool shut down.");
    }
}
