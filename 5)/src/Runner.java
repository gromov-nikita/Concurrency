import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
Exercise 5: (2) Modify Exercise 2 so that the task is a Callable that sums the values of
all the Fibonacci numbers. Create several tasks and display the results.
 */
public class Runner {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        List<Integer> list1 = null;
        List<Integer> list2 = null;
        try {
            list1 = service.submit(new Fibonacci(10)).get();
            list2 = service.submit(new Fibonacci(5)).get();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            service.shutdown();
        }
        System.out.println("list1: ");
        System.out.println(list1.toString());
        System.out.println("list2: ");
        System.out.println(list2.toString());
        int sum = 0;
        for(Integer x : list1) {
            sum += x;
        }
        System.out.println("list1: " + sum);
        sum = 0;
        for(Integer x : list2) {
            sum += x;
        }
        System.out.println("list2: " + sum);
    }
}
