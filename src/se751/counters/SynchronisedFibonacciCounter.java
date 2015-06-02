package se751.counters;

import se751.BenchmarkTaskAdapter;
import se751.Fibonacci;

/**
 * Author: Chris Morgan
 * Project: se751
 */
public class SynchronisedFibonacciCounter extends BenchmarkTaskAdapter {

    int count = 0;
    int index = 1;

    public SynchronisedFibonacciCounter(Integer workload) {
        super(workload);
    }

    synchronized int getAndIncrementIndex() {
        int tempIndex = this.index;
        this.index++;
        return tempIndex;
    }

    synchronized void addCount(int num) {
        this.count += num;
    }

    @Override
    protected boolean doWork() {
        int index = this.getAndIncrementIndex();
        if (index < this.workload) {
            this.addCount(Fibonacci.calculate(index));
            return true;
        }

        return false;
    }
}
