package se751.syncronised_lists;

import se751.BenchmarkTaskAdapter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by hugobateman on 18/05/15.
 */
public class SynchronisedListAdd_SynchronizedBlock extends BenchmarkTaskAdapter {

    private Queue<Integer> list = new LinkedList<>();
    private ArrayList<Integer> elements;
    private final Object lock = new Object();
    private AtomicInteger operationCount = new AtomicInteger(0);

    public void run() {
        for (int i = 0; i < elements.size(); i++) {
            synchronized (lock) {
                list.add(elements.get(i));
            }
        }
    }

    public SynchronisedListAdd_SynchronizedBlock(Integer workload) {
        super(workload);

        elements = new ArrayList<>(workload);

        for (int i = 0; i < workload; i++) {
            list.add(i);
            elements.add(i);
        }
    }

    @Override
    protected boolean doWork() {
        int operationCount = this.operationCount.getAndIncrement();
        if (this.operationCount.get() < this.workload) {
            synchronized (lock) {
                list.add(elements.get(operationCount));
            }
            return true;
        }
        return false;
    }

}
