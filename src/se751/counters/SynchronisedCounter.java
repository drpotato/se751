package se751.counters;

/**
 * Author: Chris Morgan
 * Project: se751
 */
public class SynchronisedCounter extends Counter {

    private static int count = 0;

    public SynchronisedCounter(Integer workload) {
        super(workload);
    }

    @Override
    int getCount() {
        return count;
    }

    @Override
    void resetCount() {
        count = 0;
    }

    synchronized void increment() {
        count++;
    }
}
