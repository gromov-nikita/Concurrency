package restaurant.staff;

import restaurant.Restaurant;

import java.util.concurrent.TimeUnit;

public class WaitPerson implements Runnable {
    private Restaurant restaurant;

    public WaitPerson(Restaurant r) {
        restaurant = r;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    while (restaurant.getMeal() == null)
                        wait(); // ... for the chef to produce a meal
                }
                System.out.println("Waitperson got " + restaurant.getMeal());
                synchronized (restaurant.getBusBoy()) {
                    restaurant.setMeal(null);
                    restaurant.getBusBoy().notifyAll();
                }
                synchronized (restaurant.getChef()) {
                    restaurant.getChef().notifyAll(); // Ready for another
                }
            }
        } catch (InterruptedException e) {
            System.out.println("WaitPerson interrupted");
        }
    }
}
