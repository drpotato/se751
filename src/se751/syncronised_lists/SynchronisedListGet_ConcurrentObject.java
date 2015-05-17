package se751.syncronised_lists;

import se751.Benchmark;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by hugobateman on 18/05/15.
 */
public class SynchronisedListGet_ConcurrentObject implements Runnable{

    ConcurrentLinkedQueue<Integer> list = new ConcurrentLinkedQueue<>();
    Integer integer;



    public SynchronisedListGet_ConcurrentObject() {
        for (int i = 0; i < Benchmark.WORK_SIZE; i++) {
            list.add(new Integer(i));
        }
    }

    public void run() {
        while (!list.isEmpty()) {
            integer = list.poll();
        }
    }

}
