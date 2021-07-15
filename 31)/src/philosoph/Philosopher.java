package philosoph;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Philosopher implements Runnable {
    private Chopstick left;
    private Chopstick right;
    private BasketOfChopsticks basket;
    private final int id;
    private final int ponderFactor;
    private Random rand = new Random(47);
    private void pause() throws InterruptedException {
        if(ponderFactor == 0) return;
        TimeUnit.MILLISECONDS.sleep(
                rand.nextInt(ponderFactor * 250));
    }
    public Philosopher(Chopstick[] basketOfChopsticks,
                       int ident, int ponder) {
        basket = new BasketOfChopsticks(basketOfChopsticks);
        id = ident;
        ponderFactor = ponder;
    }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                System.out.println(this + " " + "thinking");
                pause();
                // Philosopher becomes hungry
                System.out.println(this + " " + "grabbing right");
                right = basket.take();
                System.out.println(this + " " + "grabbing left");
                left = basket.take();
                System.out.println(this + " " + "eating");
                pause();
                basket.drop(right);
                basket.drop(left);
                right = null;
                left = null;
            }
        } catch(InterruptedException e) {
            System.err.println(this + " " + "exiting via interrupt");
        }
    }
    public String toString() { return "Philosopher " + id; }
}