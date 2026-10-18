
// 3	Print A, B, C, D in sequence using four threads	Ordering

public class Ques3 {
    private static int turn = 1;
    private static final int TOTAL_ROUNDS = 5;
    public static void main(String[] args) {
        Ques3 coordinate = new Ques3();
        Thread t1 = new Thread(() -> coordinate.printLetter('A', 1, 2), "t1");
        Thread t2 = new Thread(() -> coordinate.printLetter('B', 2, 3), "t2");
        Thread t3 = new Thread(() -> coordinate.printLetter('C', 3, 4), "t3");
        Thread t4 = new Thread(() -> coordinate.printLetter('D', 4, 1), "t3");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }

    public synchronized void printLetter(char letter, int myTurn, int nextTurn) {
        for (int i = 1; i <= TOTAL_ROUNDS; i++) {
            while (turn != myTurn) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            System.out.print(letter + " ");
            turn = nextTurn;
            notifyAll();
        }
    }
}
