package se751.counters;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Author: Chris Morgan
 * Project: se751
 */
public class AtomicFibonacciCounter extends FibonacciCounter {

    AtomicInteger count = new AtomicInteger(0);
    AtomicInteger index = new AtomicInteger(1);

    public AtomicFibonacciCounter(Integer workload) {
        super(workload);
    }

    @Override
    protected boolean doWork() {
        int index = this.index.getAndIncrement();
        if (index < this.workload) {
            this.count.getAndAdd(fibonacci(index));
            return true;
        }
        return false;
    }

}
