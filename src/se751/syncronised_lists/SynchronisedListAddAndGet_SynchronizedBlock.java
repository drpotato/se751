package se751.syncronised_lists;

import se751.BenchmarkTaskAdapter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by hugobateman on 18/05/15.
 */
public class SynchronisedListAddAndGet_SynchronizedBlock extends BenchmarkTaskAdapter {

    private Queue<Integer> list = new LinkedList<>();
    private List<Integer> elements;
    AtomicInteger operationCount = new AtomicInteger(0);
    Integer integer;
    final Object lock = new Object();

    public SynchronisedListAddAndGet_SynchronizedBlock(Integer workload) {
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
        if (operationCount < this.workload) {
            synchronized (lock) {

                if ((operationCount % 2) == 0) {
                    list.add(elements.get(operationCount));
                } else {
                    integer = list.poll();
                }
            }
            return true;
        }
        return false;
    }

}
