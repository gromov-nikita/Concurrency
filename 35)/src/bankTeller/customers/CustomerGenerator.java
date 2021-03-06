package bankTeller.customers;

import bankTeller.teller.Teller;
import bankTeller.teller.TellerManager;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class CustomerGenerator implements Runnable {
    private CustomerLine customers;
    private static Random rand = new Random(47);
    private TellerManager tellerManager;
    public CustomerGenerator(CustomerLine cq, TellerManager tellerManager) {
        customers = cq;
        this.tellerManager = tellerManager;
    }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                //TimeUnit.MILLISECONDS.sleep(rand.nextInt(300));
                if(tellerManager.getWorkingTellers().size() > 1 &&
                        customers.size() / tellerManager.getWorkingTellers().size() < 2)
                    customers.put(new Customer(rand.nextInt(1000)));
            }
        }
        catch(InterruptedException e) {
            System.out.println("CustomerGenerator interrupted");
        }
        System.out.println("CustomerGenerator terminating");
    }
}
