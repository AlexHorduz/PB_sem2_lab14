package lab14.runnables;

import lab14.models.ProgressionCalculator;

public class CalculatorRunnable implements Runnable{
    private long result;
    private long n;
    private long NStart;
    private long NEnd;

    public CalculatorRunnable(long n, long NStart, long NEnd) {
        this.result = 0;
        this.n = n;
        this.NStart = NStart;
        this.NEnd = NEnd;
    }

    @Override
    public void run() {
        ProgressionCalculator calculator = new ProgressionCalculator();
        result = calculator.calculate(n, NStart, NEnd);
    }

    public long getResult() {
        return result;
    }
}
