package restaurant.staff;

import restaurant.Restaurant;

import java.util.concurrent.TimeUnit;

public class BusBoy implements Runnable {
    private Restaurant restaurant;

    public BusBoy(Restaurant r) {
        restaurant = r;
    }
    @Override
    public void run() {
        try {
            while(!Thread.interrupted()) {
                synchronized (this) {
                    while (restaurant.getMeal() != null) {
                        wait();
                    }
                }
                System.out.println("BusBoy clean ");
                TimeUnit.MILLISECONDS.sleep(100);
            }
        }
        catch (InterruptedException e) {
            System.out.println("BusBoy interrupted");
        }
    }
}
