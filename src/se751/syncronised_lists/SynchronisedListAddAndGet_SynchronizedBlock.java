package se751.syncronised_lists;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by hugobateman on 18/05/15.
 */
public class SynchronisedListAddAndGet_SynchronizedBlock implements Runnable {

    private Queue<Integer> list = new LinkedList<>();
    private ArrayList<Integer> elements = new ArrayList(BenchmarkLists.WORK_SIZE);
    Integer integer;
    Object lock = new Object();

    public SynchronisedListAddAndGet_SynchronizedBlock() {
        for (int i = 0; i < BenchmarkLists.WORK_SIZE; i++) {
            list.add(new Integer(i));
        }
        for (int i = 0; i < BenchmarkLists.WORK_SIZE; i++) {
            elements.add(new Integer(i));
        }
    }

    public void run() {
        for (int i = 0; i < BenchmarkLists.WORK_SIZE; i++) {
            synchronized (lock) {
                if ((i % 2) == 0) {
                    list.add(elements.get(i));
                } else {
                    integer = list.poll();
                }
            }
        }
    }
}
