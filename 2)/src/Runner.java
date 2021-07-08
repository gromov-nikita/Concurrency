/*
Exercise 2: (2) Following the form of generics/Fibonacci.java, create a task that
produces a sequence of n Fibonacci numbers, where n is provided to the constructor of the
task. Create a number of these tasks and drive them using threads.
 */
public class Runner {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Fibonacci(15));
        Thread t2 = new Thread(new Fibonacci(15));
        t1.start();
        t2.start();
    }
}
