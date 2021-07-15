package philosoph;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;

public class BasketOfChopsticks {
    private List<Chopstick> list;
    private int freeN;
    public BasketOfChopsticks (List<Chopstick> list) {
        this.list = list;
        freeN = list.size();
    }
    public BasketOfChopsticks (Chopstick[] arr) {
        this.list = new LinkedList<Chopstick>(Arrays.asList(arr));
        freeN = arr.length;
    }
    public synchronized
    Chopstick take() throws InterruptedException {
        while (freeN == 0) {
            wait();
        }
        Chopstick c;
        Iterator it = list.iterator();
        while (it.hasNext()) {
            c = (Chopstick) it.next();
            if (!c.getTaken()) {
                c.take();
                freeN--;
                return c;
            }
        }
        return null;
    }
    public synchronized void drop(Chopstick c) {
        c.drop();
        freeN++;
        notifyAll();
    }
}
