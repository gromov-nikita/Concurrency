import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class MyClass implements Runnable {
    @Override
    public void run() {
        Random r = new Random();
        int time = r.nextInt(10);
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(time);
    }
}
