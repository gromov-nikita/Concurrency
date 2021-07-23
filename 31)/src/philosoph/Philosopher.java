package philosoph;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.*;
public class Philosopher implements Runnable {
    private static Logger log = Logger.getLogger("Philosopher");
    static {
        log.setUseParentHandlers(false);
        try {
            FileHandler f = new FileHandler("src/log/philosopherLOG.txt");
            f.setFormatter(new SimpleFormatter());
            log.addHandler(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
        log.info("new Philosopher " + ident + '\n');
    }
    public void run() {
        log.info(this + " run" + '\n');
        try {
            while(!Thread.interrupted()) {
                System.out.println(this + " " + "thinking");
                log.info(this + " thinking" + '\n');
                pause();
                // Philosopher becomes hungry
                System.out.println(this + " " + "grabbing right");
                log.info(this + " grabbing right" + '\n');
                right = basket.take();
                System.out.println(this + " " + " grabbing left");
                log.info(this + " grabbing left" + '\n');
                left = basket.take();
                System.out.println(this + " " + "eating");
                log.info(this + " eating" + '\n');
                pause();
                basket.drop(right);
                log.info(this + " drop right stick" + '\n');
                basket.drop(left);
                log.info(this + " drop left stick" + '\n');
                right = null;
                left = null;
            }
        }
        catch(InterruptedException e) {
            System.err.println(this + " " + "exiting via interrupt");
            log.warning(this + " " + "exiting via interrupt");
            log.warning(e.getStackTrace().toString() + '\n');
        }
        catch (NullPointerException ex) {
            ex.printStackTrace();
            log.warning("NullPointerException" + " " + " ***************************************************************** " +'\n');
        }
        catch (Exception ex) {
            ex.printStackTrace();
            log.warning(ex.getStackTrace().toString() + " " + " ***************************************************************** " +'\n');
        }
    }
    public String toString() { return "Philosopher " + id; }
}