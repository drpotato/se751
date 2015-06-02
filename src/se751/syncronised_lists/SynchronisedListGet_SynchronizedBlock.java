package se751.syncronised_lists;

import se751.BenchmarkTaskAdapter;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by hugobateman on 18/05/15.
 */
public class SynchronisedListGet_SynchronizedBlock extends BenchmarkTaskAdapter {

    private Queue<Integer> list = new LinkedList<>();
    private Integer integer;
    private final Object lock = new Object();

    public SynchronisedListGet_SynchronizedBlock(Integer workload) {
        super(workload);

        for (int i = 0; i < workload; i++) {
            list.add(i);
        }
    }

    @Override
    protected boolean doWork() {
        if (!this.list.isEmpty()) {
            synchronized (lock) {
                integer = list.poll();
            }
            return true;
        }
        return false;
    }
}
