
/*
    Ques10	Return results from parallel tasks using Callable and Future	Asynchronous results
* */


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class Ques10 {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);
        List<Integer> list = IntStream.range(0, 10001).boxed().toList();
        List<Future<Integer>> futures = new ArrayList<>();

        for (int num : list) {
            Callable<Integer> task = () -> num * num;
            futures.add(service.submit(task));
        }

        for (Future<Integer> future : futures) {
            try {
                Integer i = future.get();
                System.out.println("Future Data " + i);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
        service.shutdown();
    }
}
