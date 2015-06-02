package se751.counters;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * Author: Chris Morgan
 * Project: se751
 */
public class AtomicFibonacciArray extends FibonacciCounter{

    AtomicInteger index = new AtomicInteger(0);
    AtomicIntegerArray results;

    public AtomicFibonacciArray(Integer workload) {
        super(workload);
        results = new AtomicIntegerArray(new int[workload]);
    }

    @Override
    void doWork() {
        int index = this.index.getAndIncrement();
        if (index < workload){
            this.results.set(index, fibonacci(index));
        }
    }

    @Override
    boolean checkDone() {
        return this.index.get() >= this.workload;
    }
}
