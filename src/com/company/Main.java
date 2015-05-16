package com.company;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static final int WORK_SIZE = 100000000;
    private static String[] passwords = new String[WORK_SIZE];
    private static String[] encryptedPasswords = new String[WORK_SIZE];

    public static void main(String[] args) throws Exception {

        for (int threadCount = 1; threadCount < 9; threadCount++) {
            AtomicCounter atomicCounter = new AtomicCounter();
            ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
            System.out.println("Number of threads: " + Integer.toString(threadCount));
            final long startTime = System.currentTimeMillis();
            for (int j = 0; j < threadCount; j++) {
                executorService.execute(atomicCounter);
            }

            executorService.shutdown();
            executorService.awaitTermination(1l, TimeUnit.MINUTES);
            final long endTime = System.currentTimeMillis();
            System.out.println("Total execution time: " + (endTime - startTime));
        }

//        Random random = new Random(0l); // Seed random generator to get predictable execution.
//
//        StringBuilder stringBuilder = new StringBuilder();
//
//        for (int i = 0; i < WORK_SIZE; i++) {
//            stringBuilder.setLength(0);
//            for (int j = 0; j < 128; j++) {
//                stringBuilder.append((char)(random.nextInt(26) + 'a'));
//            }
//            passwords[i] = stringBuilder.toString();
////            System.out.println(passwords[i]);
//        }
//
//        String password = "mypassword";
//
//        final long startTime = System.currentTimeMillis();
//
//        for (int i = 0; i < WORK_SIZE; i++) {
//            encryptedPasswords[i] = AESEncrypt.encrypt(passwords[i]);
//        }
//        final long endTime = System.currentTimeMillis();
//        System.out.println("Total execution time: " + (endTime - startTime) );
    }

}
