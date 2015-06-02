package jesse.Matrixmult_Atomic_Int;

import java.util.Random;
import java.util.Scanner;
import java.io.FileWriter;
import java.util.logging.Level;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.CountDownLatch;

import java.util.logging.Logger;

public class MatSynch {

    public static void main(String[] args) {
        int[][] A;
        int[][] B;
        int columnA = 0;
        int rowA = 0;
        int columnB = 0;
        int rowB = 0;
        
        
        Scanner scannerIn = new Scanner(System.in);
        System.out.println("Please enter the dimensions of your matrix in the following format: '#columnOfA #rowsOfA #colomnsB");

        do {
            columnA = scannerIn.nextInt();
            rowA = scannerIn.nextInt();
            columnB = scannerIn.nextInt();
            System.out.println();
        } while (!validSize(columnA));

        rowB = columnA;

        
        A = new int[rowA][columnA];
        B = new int[rowB][columnB];


        randomMatrixGenerator(A);
        randomMatrixGenerator(B);

        System.out.println("Do you want to print the Matrices? (y/n)");
        String userResponse = scannerIn.next();
        if (userResponse.equalsIgnoreCase("y")) {
            System.out.println("A:");System.out.println();
            print2Screen(A);
            System.out.println("B:");
            print2Screen(B);
            System.out.println();
        }

       
        long startTime = System.nanoTime();
        int[][] newMatrix3 = concMatrixMult(A, B);
        long finishTime = System.nanoTime();
        System.out.println("total calculation time : " + (finishTime - startTime)/1000000);
  
        String fileName = "test1.txt";
        try {
            print2File(newMatrix3, fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int[][] concMatrixMult(int[][] A, int[][] B) {

        int[][] newMatrix = new int[A.length][B[0].length];

        MatMultTask.Synch synch = new MatMultTask.Synch(newMatrix.length);
   
        
        Thread[] employees = new Thread[5];
        Runnable work = new MatMultTask(synch, A, B, newMatrix);
        for (int i = 0; i < employees.length; i++) {
            employees[i] = new Thread(work, "Thread-"+i);
            
        }
        //
        for (int i = 0; i < employees.length; i++) {
            Thread employee = employees[i];
            employee.start();
        }
        for (int i = 0; i < employees.length; i++) {
            Thread employee = employees[i];
            try {
                employee.join();
            } catch (InterruptedException ex) {
                //fill later
            }
        }
        return newMatrix;
    }
    
    private static void randomMatrixGenerator(int[][] mat) {
        Random rand = new Random();

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                mat[i][j] = rand.nextInt(100) + 1;
            }
        }

    }

    

    private static void print2Screen(int[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    private static void print2File(int[][] mat, String fileName) throws IOException {
        PrintWriter output = new PrintWriter(new FileWriter(fileName));
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                output.print(mat[i][j] + " ");
            }
            output.println();
        }
        output.close();

    }
    

    public static boolean validSize(int dim) {
        if (dim <= 0 || dim > 5000) {
            System.err.println("Values must be betweend 0 and 5000");
            return false;
        }
        return true;

    }
}