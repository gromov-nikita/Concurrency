import generator.interfaces.Generator;

public class Fibonacci implements Generator<Integer>, Runnable {
    private int amount;
    private int count = 0;
    private static int n = 0;
    private int id;
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
    public void run() {
        for(int i = 0; i < amount; i++)
            System.out.println(next() + " " + id);
    }
}