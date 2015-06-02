package se751.syncronised_lists;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by hugobateman on 16/05/15.
 */
public class SynchronisedListAdd_ConcurrentObject extends SynchronisedList {

    ConcurrentLinkedQueue<Integer> list = new ConcurrentLinkedQueue<>();
    private ArrayList<Integer> elements;

    AtomicInteger operationCount = new AtomicInteger(0);

    public SynchronisedListAdd_ConcurrentObject(Integer workload) {
        super(workload);

        this.workload = workload;
        this.elements = new ArrayList<>(workload);

        for (int i = 0; i < workload; i++) {
            list.add(i);
        }
        for (int i = 0; i < workload; i++) {
            elements.add(i);
        }
    }

    @Override
    protected boolean doWork() {

        int operationCount = this.operationCount.getAndIncrement();

        if (operationCount < this.workload) {
            list.add(elements.get(operationCount));
            return true;
        }
        return false;
    }

}
