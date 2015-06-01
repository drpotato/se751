package se751.counters;

/**
 * Author: Chris Morgan
 * Project: se751
 */
public abstract class FibonacciCounter implements Runnable {

    int workload;

    public FibonacciCounter(Integer workload) {
        this.workload = workload;
    }

    abstract void doWork();
    abstract boolean checkDone();

    @Override
    public void run() {
        while (!checkDone()) {
            doWork();
        }
    }

    public static int fibonacci(int num) {
        if(num <= 1){
            return num;
        }
        int fib = 1;
        int fibPrev = 1;
        for(int i = 2; i < num; ++i){
            int temp = fib;
            fib += fibPrev;
            fibPrev = temp;
        }
        return fib;
    }

    public int[] rangeFromWorkload(int workload) {
        int start = 1;
        int stop = workload + 1;
        int[] result = new int[stop - start];

        for(int i = 0; i < stop - start; i++) {
            result[i] = start + i;
        }
        return result;
    }

}
