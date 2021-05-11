package lab14.services;

import lab14.runnables.CalculatorRunnable;

import java.util.ArrayList;
import java.util.List;

public class Service {
    private long n;
    private long N;
    private long correctResult;


    public Service(long n, long N) {
        this.n = n;
        this.N = N;
        this.correctResult = N*(N + 1) / 2 * n;
    }

    public void computeInSingleThread() throws Exception {
        CalculatorRunnable calcRunnable = new CalculatorRunnable(n, 1, N);
        Thread t = new Thread(calcRunnable);


        long start = System.nanoTime();
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            System.err.println("Couldn't compute in single Thread");
            e.printStackTrace();
        }
        long end = System.nanoTime();


        if (calcRunnable.getResult() != correctResult) {
            throw new Exception("Result computed is not correct");
        }
        System.out.println("Computed in single thread for " + (end - start) / 1_000_000 + " ms");

    }

    public void computeInMultipleThreads(long k) throws Exception {
        long sum = 0;
        List<CalculatorRunnable> runnableList = new ArrayList<>();
        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            CalculatorRunnable c = new CalculatorRunnable(n, N * i / k + 1, N * (i + 1) / k);
            runnableList.add(c);
            threadList.add(new Thread(c));
        }

        long start = System.nanoTime();
        for (Thread c : threadList) {
            c.start();
        }
        for (int i = 0; i < k; i++) {
            try {
                threadList.get(i).join();
                sum += runnableList.get(i).getResult();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long end = System.nanoTime();

        if (sum != correctResult) {
            throw new Exception("Result computed is not correct");
        }
        System.out.println("Computed in multiple threads with k = " + k + " for " + (end - start) / 1_000_000 + " ms");
    }
}
