package lab14.models;

public class ProgressionCalculator {
    public long calculate(long n, long Nstart, long Nend) {
        long sum = 0;
        for (long i = Nstart; i <= Nend; i++) {
            sum += i * n;
        }
        return sum;
    }
}
