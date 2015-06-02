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

    abstract protected boolean doWork();

    @Override
    public void run() {
        boolean result;
        do {
            result = doWork();
        } while (result);
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

}
