package se751.syncronised_lists;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by hugobateman on 16/05/15.
 */
public class SynchronisedListAdd_ConcurrentObject implements Runnable {

    private Integer workload;

    private ConcurrentLinkedQueue<Integer> list = new ConcurrentLinkedQueue<>();
    private ArrayList<Integer> elements = new ArrayList<Integer>();
    int addCount = 0;

    public SynchronisedListAdd_ConcurrentObject(Integer workload) {
        this.workload = workload;
        for (int i = 0; i < this.workload; i++) {
            elements.add(i);
        }
    }

    public void run() {
        while (addCount < this.workload) {
            list.add(elements.get(addCount));
            addCount++;
        }
    }
}
