package restaurant.staff;
import restaurant.Restaurant;
import restaurant.menu.Meal;
import java.util.concurrent.TimeUnit;

public class Chef implements Runnable {
    private Restaurant restaurant;
    private int count = 0;
    public Chef(Restaurant r) { restaurant = r; }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                synchronized(this) {
                    while(restaurant.getMeal() != null)
                        wait(); // ... for the meal to be taken
                }
                if(++count == 10) {
                    System.out.println("Out of food, closing");
                    restaurant.getExec().shutdownNow();
                }
                System.out.println("Order up! ");
                synchronized(restaurant.getWaitPerson()) {
                    restaurant.setMeal(new Meal(count));
                    restaurant.getWaitPerson().notifyAll();
                }
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch(InterruptedException e) {
            System.out.println("Chef interrupted");
        }
    }
}
