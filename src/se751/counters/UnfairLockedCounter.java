package se751.counters;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Author: Chris Morgan
 * Project: se751
 */
public class UnfairLockedCounter extends Counter {

    private static int count = 0;
    private Lock lock = new ReentrantLock();

    public UnfairLockedCounter(Integer workload) {
        super(workload);
    }

    @Override
    protected int getCount() {
        return count;
    }

    @Override
    protected void increment() {
        lock.lock();
        count++;
        lock.unlock();

    }

}
