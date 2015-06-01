package se751;

import se751.counters.AtomicFibonacciCounter;
import se751.counters.ProceduralFibonacciCounter;
import se751.counters.SynchronisedFibonacciCounter;
import se751.counters.UnfairLockedFibonacciCounter;

import java.util.Vector;

public class Benchmark {

    static final int REPEATS = 100;
    public static final int WORK_SIZE = 50000;


    public static void main(String[] args) throws Exception {

        System.out.println("Benchmarking ProceduralFibonacciCounter");

        long totalTime = 0;
        for (int i = 0; i < REPEATS; i++) {
            final long startTime = System.currentTimeMillis();
            ProceduralFibonacciCounter.count(WORK_SIZE);
            final long endTime = System.currentTimeMillis();
            totalTime += endTime - startTime;
        }

        System.out.printf("Single Thread - %dms\n", totalTime / REPEATS);

        Runnable runnable;
        RunnableFactory runnableFactory = new RunnableFactory();
        Vector<Thread> threads;
        int processorCount = Runtime.getRuntime().availableProcessors();

        // Add the runnables we want to tests.
        runnableFactory.mapRunnable(AtomicFibonacciCounter.class);
        runnableFactory.mapRunnable(SynchronisedFibonacciCounter.class);
        runnableFactory.mapRunnable(UnfairLockedFibonacciCounter.class);
//        runnableFactory.mapRunnable(AtomicCounter.class);
//        runnableFactory.mapRunnable(SynchronisedCounter.class);
//        runnableFactory.mapRunnable(UnfairLockedCounter.class);
//        runnableFactory.mapRunnable(SynchronisedListGet_ConcurrentObject.class);
//        runnableFactory.mapRunnable(SynchronisedListGet_SynchronizedBlock.class);
//        runnableFactory.mapRunnable(SynchronisedListAdd_ConcurrentObject.class);
//        runnableFactory.mapRunnable(SynchronisedListAdd_SynchronizedBlock.class);
//        runnableFactory.mapRunnable(SynchronisedListAddAndGet_ConcurrentObject.class);
//        runnableFactory.mapRunnable(SynchronisedListAddAndGet_SynchronizedBlock.class);

        // Rather than have multiple for loops benchmarking each case, we loop through the types of runnable we've
        // implemented and benchmark it.
        for (String runnableType : runnableFactory.getRunnableTypes()) {

            System.out.println("Benchmarking " + runnableType);

            // We want to benchmark the synchronisation for different numbers of threads.
            for (int threadCount = 1; threadCount <= (processorCount * 2); threadCount++) {

                totalTime = 0;
                System.out.printf("%d threads", threadCount);

                for (int j = 0; j < REPEATS; j++) {
                    // Reset the runnable and threads and prepare them for the benchmarking.
                    runnable = runnableFactory.newRunnable(runnableType, WORK_SIZE);
                    threads = new Vector<>();
                    for (int k = 0; k < threadCount; k++) {
                        threads.add(new Thread(runnable));
                    }

                    // Start timing and kick of threads.
                    final long startTime = System.currentTimeMillis();
                    for (int k = 0; k < threadCount; k++) {
                        threads.get(k).start();
                    }

                    // Wait for all the threads to finish then take the time.
                    for (int k = 0; k < threadCount; k++) {
                        threads.get(k).join();
                    }
                    final long endTime = System.currentTimeMillis();

                    totalTime += (endTime - startTime);
                }

                System.out.printf(" - %dms\n", totalTime / REPEATS);
            }
        }
    }
}
