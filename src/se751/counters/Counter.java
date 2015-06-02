package se751.counters;

import se751.BenchmarkTaskAdapter;

/**
 * Author: Chris Morgan
 * Project: se751
 */
public abstract class Counter extends BenchmarkTaskAdapter {

    public Counter(Integer workload) {
        super(workload);
    }

    abstract protected void increment();
    abstract protected int getCount();

    @Override
    protected boolean doWork() {
        if (this.getCount() < this.workload) {
            this.increment();
            return true;
        }
        return false;
    }
}
