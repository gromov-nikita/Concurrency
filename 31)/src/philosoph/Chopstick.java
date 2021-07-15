package philosoph;

public class Chopstick {
    private boolean taken = false;
    public boolean getTaken() {
        return taken;
    }
    public synchronized
    void take() throws InterruptedException {
        taken = true;
    }
    public synchronized void drop() {
        taken = false;
        //notifyAll();
    }
}
