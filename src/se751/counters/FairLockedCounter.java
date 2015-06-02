package se751.counters;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Author: Chris Morgan
 * Project: se751
 */
public class FairLockedCounter extends Counter {

    private static int count = 0;
    private Lock lock = new ReentrantLock(true);

    public FairLockedCounter(Integer workload) {
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
