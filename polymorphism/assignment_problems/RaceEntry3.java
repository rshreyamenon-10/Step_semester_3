import java.util.Arrays;

public class RaceEntry3 {

    private String bibNumber;
    private double entryFee;
    private double balanceDue;
    private double[] lateFeeHistory = new double[10]; // Constraint: Up to 10 late fees per entry
    private int lateFeeCount = 0;

    public RaceEntry(String bibNumber, double entryFee) {
        if (bibNumber == null || bibNumber.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid bib number");
        }
        this.bibNumber = bibNumber;
        this.entryFee = entryFee;
        this.balanceDue = entryFee;
    }

    public void pay(double amount) {
        if (amount > 0) {
            this.balanceDue -= amount;
        }
    }

    public double getBalanceDue() {
        return balanceDue;
    }

    protected void applyLateFee(double amount) {
        if (amount > 0 && lateFeeCount < 10) {
            this.balanceDue += amount;
            this.lateFeeHistory[lateFeeCount] = amount;
            this.lateFeeCount++;
        }
    }

    public double[] getLateFeeHistory() {
        // Defensive copy sized exactly to the number of fees applied
        return Arrays.copyOf(lateFeeHistory, lateFeeCount);
    }
}

class RunnerEntry extends RaceEntry {

    private String category;

    public RunnerEntry(String bibNumber, double entryFee, String category) {
        super(bibNumber, entryFee);
        this.category = category;
    }

    @Override
    protected void applyLateFee(double amount) {
        super.applyLateFee(amount * 2);
    }
}