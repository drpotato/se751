package com.company;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Benchmark {

    public static final int WORK_SIZE = 100000000;
    private static String[] passwords = new String[WORK_SIZE];
    private static String[] encryptedPasswords = new String[WORK_SIZE];

    public static void main(String[] args) throws Exception {

        Runnable runnable;
        ExecutorService executorService;
        int processorCount = Runtime.getRuntime().availableProcessors();

        // Rather than have multiple for loops benchmarking each case, we loop through the types of runnable we've
        // implemented and benchmark it.
        for (RunnableType runnableType : RunnableType.values()) {

            System.out.println("Benchmarking " + runnableType.name());

            for (int threadCount = 1; threadCount <= (processorCount * 2); threadCount++) {

                // Reset the runnable and executorService for the benchmark.
                runnable = RunnableFactory.newRunnable(runnableType);
                executorService = Executors.newFixedThreadPool(threadCount);

                // Start timing and kick of threads.
                final long startTime = System.currentTimeMillis();
                for (int j = 0; j < threadCount; j++) {
                    executorService.execute(runnable);
                }

                // Shutdown the threads and wait for them to finish.
                executorService.shutdown();
                executorService.awaitTermination(1l, TimeUnit.MINUTES);
                final long endTime = System.currentTimeMillis();
                System.out.printf("%d threads -  %dms\n", threadCount, (endTime - startTime));
            }
        }
    }
}
