package restaurant;

import restaurant.menu.Meal;
import restaurant.staff.BusBoy;
import restaurant.staff.Chef;
import restaurant.staff.WaitPerson;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Restaurant {
    private Meal meal;
    private ExecutorService exec = Executors.newCachedThreadPool();
    private WaitPerson waitPerson = new WaitPerson(this);
    private Chef chef = new Chef(this);
    private BusBoy busBoy = new BusBoy(this);
    public Meal getMeal() {
        return meal;
    }
    public ExecutorService getExec() {
        return exec;
    }
    public WaitPerson getWaitPerson() {
        return waitPerson;
    }
    public Chef getChef() {
        return chef;
    }
    public BusBoy getBusBoy() {
        return busBoy;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }
    public void setExec(ExecutorService exec) {
        this.exec = exec;
    }
    public void setWaitPerson(WaitPerson waitPerson) {
        this.waitPerson = waitPerson;
    }
    public void setChef(Chef chef) {
        this.chef = chef;
    }
    public void setBusBoy(BusBoy busBoy) {
        this.busBoy = busBoy;
    }
    public Restaurant() {
        exec.execute(chef);
        exec.execute(waitPerson);
        exec.execute(busBoy);
    }
    public static void main(String[] args) {
        new Restaurant();
    }
}
