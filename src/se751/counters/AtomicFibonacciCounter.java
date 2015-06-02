package se751.counters;

import se751.BenchmarkTaskAdapter;
import se751.Fibonacci;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Author: Chris Morgan
 * Project: se751
 */
public class AtomicFibonacciCounter extends BenchmarkTaskAdapter {

    AtomicInteger count = new AtomicInteger(0);
    AtomicInteger index = new AtomicInteger(1);

    public AtomicFibonacciCounter(Integer workload) {
        super(workload);
    }

    @Override
    protected boolean doWork() {
        int index = this.index.getAndIncrement();
        if (index < this.workload) {
            this.count.getAndAdd(Fibonacci.calculate(index));
            return true;
        }
        return false;
    }

}
