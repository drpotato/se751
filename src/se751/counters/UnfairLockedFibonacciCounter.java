package se751.counters;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Author: Chris Morgan
 * Project: se751
 */
public class UnfairLockedFibonacciCounter extends FibonacciCounter {

    private int count = 0;

    private int index = 1;
    private Lock lock = new ReentrantLock();
    public UnfairLockedFibonacciCounter(Integer workload) {
        super(workload);
    }

    void incrementIndex() {
        this.lock.lock();
        this.index++;
        this.lock.unlock();
    }

    void addCount(int num) {
        this.lock.lock();
        this.count += num;
        this.lock.unlock();
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
