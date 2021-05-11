package lab14;

import lab14.services.Service;

public class Main {

    public static void main(String[] args) {
        long N = 2_000_000_000L;
        long n = 3;
        Service service = new Service(n, N);

        try {
            service.computeInSingleThread();
            for (int i = 1; i <= 32; i *= 2) {
                service.computeInMultipleThreads(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
