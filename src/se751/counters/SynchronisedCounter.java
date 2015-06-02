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
    protected int getCount() {
        return count;
    }

    synchronized protected void increment() {
        count++;
    }
}
