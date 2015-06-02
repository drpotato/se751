package se751;

/**
 * Author: Chris Morgan
 * Project: se751
 */
public class Fibonacci {
    public static int calculate(int num) {
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
