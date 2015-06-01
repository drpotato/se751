package se751.counters;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Author: Chris Morgan
 * Project: se751
 */
public class AtomicFibonacciCounter extends FibonacciCounter{

    AtomicInteger count = new AtomicInteger(0);
    AtomicInteger index = new AtomicInteger(1);

    public AtomicFibonacciCounter(Integer workload) {
        super(workload);
    }

    @Override
    void doWork() {
        this.count.getAndAdd(fibonacci(this.index.getAndIncrement()));
    }

    @Override
    boolean checkDone() {
        return this.index.get() == this.workload;
    }
}
