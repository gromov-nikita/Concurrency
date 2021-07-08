import static java.lang.Thread.yield;

public class MyClass implements Runnable {
    private static int amount = 0;
    private int id;
    public MyClass() {
        id = amount++;
        System.out.println("MyClass " + id);
    }
    @Override
    public void run() {
        int i = 0;
        while (i < 3) {
            System.out.println("Run " + id);
            i++;
            yield();
        }
        System.out.println("End " + id);
    }
}

