
// 4	Implement a thread-safe Singleton	Safe initialization
public class Ques4 {
    public static void main(String[] args) {
        Singleton singleton1 = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();

        if (singleton1 == singleton2)
            System.out.println("Instances are same " + singleton1 + " " + singleton2);
        else
            System.out.println("Instances are not same");
    }
}

class Singleton {
    private static volatile Singleton instance = null;

    private Singleton() {

    }


    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null)
                    instance = new Singleton();
            }
        }
        return instance;
    }

}
