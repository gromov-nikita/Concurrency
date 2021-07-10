import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;

public class Fibonacci implements Generator<Integer>, Callable<List<Integer>> {
    private static int n = 0;
    private int id;
    private int amount;
    private int count = 0;
    public Fibonacci(int amount) {
        this.amount = amount;
        id = n++;
    }
    public Integer next() { return fib(count++); }
    private int fib(int n) {
        if(n < 2) return 1;
        return fib(n-2) + fib(n-1);
    }
    @Override
    public List<Integer> call() throws Exception {
        List<Integer> list = new LinkedList<>();
        for(int i = 0; i < amount; i++) {
            list.add(next());
            System.out.println(id);
        }
        return list;
    }
}
