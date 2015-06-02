package se751;

/**
 * Author: Chris Morgan
 * Project: se751
 */
public abstract class BenchmarkTaskAdapter implements Runnable {

    protected int workload;

    public BenchmarkTaskAdapter(int workload) {
        this.workload = workload;
    }

    protected abstract boolean doWork();

    @Override
    public void run() {
        // Do-While loop is ugly, but in this case it actually works quite well. Without it, the body of the while loop
        // would be empty.
        boolean moreWork;
        do {
            moreWork = doWork();
        } while (moreWork);
    }
}
