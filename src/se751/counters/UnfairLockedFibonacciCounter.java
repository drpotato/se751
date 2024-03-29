package se751.counters;

import se751.BenchmarkTaskAdapter;
import se751.Fibonacci;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Author: Chris Morgan
 * Project: se751
 */
public class UnfairLockedFibonacciCounter extends BenchmarkTaskAdapter {

    private int count = 0;

    private int index = 1;
    private Lock lock = new ReentrantLock();
    public UnfairLockedFibonacciCounter(Integer workload) {
        super(workload);
    }

    int getAndIncrementIndex() {
        this.lock.lock();
        int tempIndex = index;
        this.index++;
        this.lock.unlock();
        return tempIndex;
    }

    void addCount(int num) {
        this.lock.lock();
        this.count += num;
        this.lock.unlock();
    }

    @Override
    protected boolean doWork() {
        int index = getAndIncrementIndex();
        if (index < this.workload) {
            this.addCount(Fibonacci.calculate(index));
            return true;
        }
        return false;
    }

}
