package se751.counters;

/**
 * Author: Chris Morgan
 * Project: se751
 */
public class SynchronisedFibonacciCounter extends FibonacciCounter {

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
            this.addCount(fibonacci(index));
            return true;
        }

        return false;
    }
}
