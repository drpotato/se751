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

    synchronized void incrementIndex() {
        this.index++;
    }

    synchronized void addCount(int num) {
        this.count += num;
    }

    @Override
    void doWork() {
        int index = this.index;
        incrementIndex();
        this.addCount(this.fibonacci(index));
    }

    @Override
    boolean checkDone() {
        return this.index >= this.workload + 1;
    }
}
