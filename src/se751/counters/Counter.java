package se751.counters;

/**
 * Author: Chris Morgan
 * Project: se751
 */
public abstract class Counter implements Runnable {

    private int workload;

    public Counter(Integer workload) {
        this.workload = workload;
    }

    abstract void resetCount();
    abstract void increment();
    abstract int getCount();

    @Override
    public void run() {
        resetCount();
        while (getCount() < workload) {
            increment();
        }
    }
}
