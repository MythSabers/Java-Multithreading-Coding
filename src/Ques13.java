
/*
    Ques13	Demonstrate volatile with a stop flag	Visibility
* */

public class Ques13 {
    private static volatile boolean keepRunning = true;
    public static void main(String[] args) throws InterruptedException {
        Thread workerThread = new Thread(() -> {
            System.out.println("Worker thread: Starter running loop....");
            while (keepRunning) {
                Thread.onSpinWait();
            }
            System.out.println("Worker thread:  Detected stop flag! Exiting loop cleanly");
        });
        workerThread.start();
        Thread.sleep(1000);
        System.out.println("Main thread: Changing keepRunning flag to false...");
        keepRunning = false;

        workerThread.join();
        System.out.println("Main thread: Worker thread has successfully stopped.");

    }
}
