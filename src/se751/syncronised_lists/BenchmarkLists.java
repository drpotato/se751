package se751.syncronised_lists;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by hugobateman on 17/05/15.
 */
public class BenchmarkLists {
    public static final int WORK_SIZE = 5000000;
    static ArrayList<Class> list_classes = new ArrayList<>();

    // Methinks ExecutorService does some optimization.
    public static void main(String[] args) throws Exception{
        list_classes.add(SynchronisedListAdd.class);
        list_classes.add(SynchronisedListGet.class);
        list_classes.add(SynchronisedListGetAndSet.class);


        for (Class c : list_classes) {

            Runnable runnable = (Runnable) c.newInstance();
            ExecutorService executorService = Executors.newFixedThreadPool(3);

            System.out.printf("Benchmarking %s...\n", c.getSimpleName());

            final long executerStartTime = System.currentTimeMillis();

            try {
                executorService.execute(runnable);
                executorService.shutdown();
                executorService.awaitTermination(1l, TimeUnit.MINUTES);
            } catch (Exception e) {
                e.printStackTrace();
            }

            final long executerEndTime = System.currentTimeMillis();
            System.out.printf("Executor Time taken for  -  %dms\n", (executerStartTime - executerEndTime));
            executorService.shutdown();

            runnable = (Runnable) c.newInstance();

            Thread t1 = new Thread(runnable);
            Thread t2 = new Thread(runnable);
            Thread t3 = new Thread(runnable);
//            Thread t4 = new Thread(runnable);
//            Thread t5 = new Thread(runnable);
//            Thread t6 = new Thread(runnable);
//            Thread t7 = new Thread(runnable);
//            Thread t8 = new Thread(runnable);

            final long threadStartTime = System.currentTimeMillis();

            t1.start();
            t2.start();
            t3.start();
//            t4.start();
//            t5.start();
//            t6.start();
//            t7.start();
//            t8.start();

            t1.join();
            t2.join();
            t3.join();
//            t4.join();
//            t5.join();
//            t6.join();
//            t7.join();
//            t8.join();

            final long threadEndTime = System.currentTimeMillis();
            System.out.printf("Thread Time taken -  %dms\n\n", (threadStartTime - threadEndTime));

        }
    }
}
