package se751.counters;

/**
 * Author: Chris Morgan
 * Project: se751
 */
public class SynchronizedCounter extends Counter {

    private int count = 0;

    public SynchronizedCounter(Integer workload) {
        super(workload);
    }

    @Override
    int getCount() {
        return count;
    }

    synchronized void increment() {
        count++;
    }
}
