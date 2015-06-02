package se751.syncronised_lists;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by hugobateman on 17/05/15.
 */
public class SynchronisedListAddAndGet_ConcurrentObject extends SynchronisedList {

    ConcurrentLinkedQueue<Integer> list = new ConcurrentLinkedQueue<>();
    private ArrayList<Integer> elements;

    AtomicInteger operationCount = new AtomicInteger(0);
    Integer integer;

    public SynchronisedListAddAndGet_ConcurrentObject(Integer workload) {
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
            if ((operationCount % 2) == 0) {
                list.add(elements.get(operationCount));
            } else {
                integer = list.poll();
            }
            return true;
        }
        return false;
    }

}
