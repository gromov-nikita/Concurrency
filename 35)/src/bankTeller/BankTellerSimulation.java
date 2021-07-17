package bankTeller;

import bankTeller.customers.CustomerGenerator;
import bankTeller.customers.CustomerLine;
import bankTeller.teller.Teller;
import bankTeller.teller.TellerManager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BankTellerSimulation {
    static final int MAX_LINE_SIZE = 50;
    static final int ADJUSTMENT_PERIOD = 1000;
    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        // If line is too long, customers will leave:
        CustomerLine customers =
                new CustomerLine(MAX_LINE_SIZE);
        TellerManager meneger = new TellerManager(
                exec, customers, ADJUSTMENT_PERIOD, 10);
        exec.execute(new CustomerGenerator(customers, meneger));
        // Manager will add and remove tellers as necessary:

        exec.execute(meneger);

        if(args.length > 0) // Optional argument
            TimeUnit.SECONDS.sleep(new Integer(args[0]));
        else {
            System.out.println("Press ‘Enter’ to quit");
            System.in.read();
        }
        exec.shutdownNow();
    }
}
