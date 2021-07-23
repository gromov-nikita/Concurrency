package philosoph;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;

public class BasketOfChopsticks {
    private Queue<Chopstick> list;
    public BasketOfChopsticks (List<Chopstick> list) {
        this.list = new LinkedList<>(list);
    }
    public BasketOfChopsticks (Chopstick[] arr) {
        this.list = new LinkedList<Chopstick>(Arrays.asList(arr));
    }
    public synchronized
    Chopstick take() throws InterruptedException {
        while (list.isEmpty()) {
            wait();
        }
        Chopstick c = list.poll();
        c.take();
        return c;
    }
    public synchronized void drop(Chopstick c) {
        c.drop();
        list.offer(c);
    }
}
