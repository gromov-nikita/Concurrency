import java.util.concurrent.TimeUnit;

/*
Exercise 1: (2) Implement a Runnable. Inside run( ), print a message, and then call
yield( ). Repeat this three times, and then return from run( ). Put a startup message in the
constructor and a shutdown message when the task terminates. Create a number of these
tasks and drive them using threads.
 */
public class Runner {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new MyClass());
        Thread t2 = new Thread(new MyClass());
        t1.start();
        t2.start();
        TimeUnit.NANOSECONDS.sleep(100);
        System.out.println("**********");
    }
}