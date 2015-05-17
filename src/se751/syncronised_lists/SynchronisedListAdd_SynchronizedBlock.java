package se751.syncronised_lists;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by hugobateman on 18/05/15.
 */
public class SynchronisedListAdd_SynchronizedBlock implements Runnable {

    private Queue<Integer> list = new LinkedList<>();
    private ArrayList<Integer> elements = new ArrayList();
    Object lock = new Object();

    public SynchronisedListAdd_SynchronizedBlock() {
        for (int i = 0; i < BenchmarkLists.WORK_SIZE+1; i++) {
            elements.add(new Integer(i));
        }
    }

    public void run() {
        for (int i = 0; i < elements.size(); i++) {
            synchronized (lock) {
                list.add(elements.get(i));
            }
        }
    }
}
