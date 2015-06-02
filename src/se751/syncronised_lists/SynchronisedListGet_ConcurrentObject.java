package se751.syncronised_lists;

import se751.BenchmarkTaskAdapter;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by hugobateman on 18/05/15.
 */
public class SynchronisedListGet_ConcurrentObject extends BenchmarkTaskAdapter {

    ConcurrentLinkedQueue<Integer> list = new ConcurrentLinkedQueue<>();
    Integer integer;


    public void run() {
        while (!list.isEmpty()) {
            integer = list.poll();
        }
    }

    public SynchronisedListGet_ConcurrentObject(Integer workload) {
        super(workload);

        this.workload = workload;

        for (int i = 0; i < workload; i++) {
            list.add(i);
        }
    }

    @Override
    protected boolean doWork() {

        if (!list.isEmpty()) {
            integer = list.poll();
            return true;
        }
        return false;
    }

}
