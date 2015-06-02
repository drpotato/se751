package jesse.Matrixmult_Synchronized;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;



public class MatMultTask implements Runnable {

    private int[][] matA;
    private int[][] matB;
    private int[][] matProduct;
    //
    private Synch tracker;

    public MatMultTask(Synch tracker, int[][] A, int[][] B, int[][] C) {
        if (tracker == null) {
            // todo
        }
        this.tracker = tracker;
        this.matA = A;
        this.matB = B;
        this.matProduct = C;
    }

    @Override
    public void run() {
        while (true) {
            int row;
            synchronized (tracker) {
                if (tracker.alreadyProcessed()) {
                    break;
                }
                row = tracker.findNextRow();
            }
            System.out.println(Thread.currentThread().getName() + " is proecessing " + row);
            for (int j = 0; j < matB[0].length; j++) {
                for (int k = 0; k < matA[0].length; k++) {
                    matProduct[row][j] += matA[row][k] * matB[k][j];
                }
            }
        }
    }
    public static class Synch {

    	private int nextRow = 0;
        private final int currentRow;
        

        public Synch(int currentRow) {
            this.currentRow = currentRow;
        }
        
        public synchronized boolean alreadyProcessed() {
            return nextRow == currentRow;
        }

        public synchronized int findNextRow() {
        	
            if (alreadyProcessed()) {
                throw new IllegalStateException("Already fully processed");
            }
            return nextRow++;
        }

        
    }
}

    