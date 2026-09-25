import java.util.Arrays;

public class EventTicket {

    private double basePrice;
    private double balanceDue;
    private double[] lateFeeHistory = new double[10]; // Constraint: Up to 10 late fees
    private int lateFeeCount = 0;

    public EventTicket(double basePrice) {
        this.basePrice = basePrice;
        this.balanceDue = basePrice;
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
        // Returns a defensive copy sized exactly to the number of fees applied
        return Arrays.copyOf(lateFeeHistory, lateFeeCount);
    }
}

class WorkshopTicket extends EventTicket {

    public WorkshopTicket(double basePrice) {
        super(basePrice);
    }

    @Override
    protected void applyLateFee(double amount) {
        super.applyLateFee(amount * 2);
    }
}