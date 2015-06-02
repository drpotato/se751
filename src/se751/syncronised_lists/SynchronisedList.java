package se751.syncronised_lists;

/**
 * Author: Chris Morgan
 * Project: se751
 */
public abstract class SynchronisedList implements Runnable {

    int workload;

    public SynchronisedList(Integer workload) {
        this.workload = workload;
    }

    protected abstract boolean doWork();

    @Override
    public void run() {
        while (this.doWork()) {}
    }
}
