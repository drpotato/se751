package se751;

/**
 * Author: Chris Morgan
 * Project: se751
 */
public class SynchronizedCounter implements Runnable {

    private int count = 0;

    private synchronized void increment() {
        count++;
    }

    @Override
    public void run() {
        while (count < Benchmark.WORK_SIZE) {
         increment();
        }
    }
}
