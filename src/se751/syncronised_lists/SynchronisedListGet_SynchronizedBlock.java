package se751.syncronised_lists;

import se751.Benchmark;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by hugobateman on 18/05/15.
 */
public class SynchronisedListGet_SynchronizedBlock implements Runnable{

    private Queue<Integer> list = new LinkedList<>();
    Integer integer;
    Object lock = new Object();



    public SynchronisedListGet_SynchronizedBlock() {
        for (int i = 0; i < Benchmark.WORK_SIZE; i++) {
            list.add(new Integer(i));
        }
    }

    public void run() {
        while (!list.isEmpty()) {
            synchronized (lock) {
                integer = list.poll();
            }
        }
    }
}
