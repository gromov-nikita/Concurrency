import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
Exercise 6: (2) Create a task that sleeps for a random amount of time between 1 and 10
seconds, then displays its sleep time and exits. Create and run a quantity (given on the
command line) of these tasks.
 */
public class Runner {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("amount :");
        int amount = scanner.nextInt();
        ExecutorService exec = Executors.newFixedThreadPool(amount);
        for(int i = 0; i < amount; i++) {
            exec.submit(new MyClass());
        }
        exec.shutdown();
    }
}
