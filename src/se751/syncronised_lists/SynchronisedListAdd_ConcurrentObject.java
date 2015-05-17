package se751.syncronised_lists;

import se751.Benchmark;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.ArrayList;

/**
 * Created by hugobateman on 16/05/15.
 */
public class SynchronisedListAdd_ConcurrentObject implements Runnable {

    private ConcurrentLinkedQueue<Integer> list = new ConcurrentLinkedQueue<>();
    private ArrayList<Integer> elements = new ArrayList();
    int addCount = 0;

    public SynchronisedListAdd_ConcurrentObject() {
        for (int i = 0; i < BenchmarkLists.WORK_SIZE; i++) {
            elements.add(new Integer(i));
        }
    }

    public void run() {
        while (addCount < BenchmarkLists.WORK_SIZE) {
            list.add(elements.get(addCount));
            addCount++;
        }
    }
}
