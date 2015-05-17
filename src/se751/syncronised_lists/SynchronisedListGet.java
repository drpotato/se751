package se751.syncronised_lists;

import se751.Benchmark;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by hugobateman on 17/05/15.
 */
public class SynchronisedListGet implements Runnable {

    ConcurrentLinkedQueue<Integer> list = new ConcurrentLinkedQueue<>();
    private ArrayList<Integer> elements = new ArrayList(BenchmarkLists.WORK_SIZE);
    int operationCount = 0;
    Integer integer;

    public SynchronisedListGet() {
        for (int i = 0; i < BenchmarkLists.WORK_SIZE; i++) {
            list.add(new Integer(i));
        }
        for (int i = 0; i < BenchmarkLists.WORK_SIZE; i++) {
            elements.add(new Integer(i));
        }
    }

    public void run() {
        while (operationCount < BenchmarkLists.WORK_SIZE) {
            if ((operationCount % 2) == 0) {
                list.add(elements.get(operationCount));
            }
            else {
                integer = list.poll();
            }
            operationCount++;
        }
    }


}
