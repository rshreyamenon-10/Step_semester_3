import java.util.Arrays;

public class EventTicket3 {

    private String attendeeId;
    private double basePrice;
    private double balanceDue;
    private double[] lateFeeHistory = new double[0];

    public EventTicket(double basePrice) {
        this.basePrice = basePrice;
        this.balanceDue = basePrice;
    }

    public EventTicket(String attendeeId, double basePrice) {
        if (attendeeId == null || attendeeId.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid attendeeId");
        }
        this.attendeeId = attendeeId;
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
        if (amount > 0) {
            this.balanceDue += amount;
            lateFeeHistory = Arrays.copyOf(lateFeeHistory, lateFeeHistory.length + 1);
            lateFeeHistory[lateFeeHistory.length - 1] = amount;
        }
    }

    public double[] getLateFeeHistory() {
        return Arrays.copyOf(lateFeeHistory, lateFeeHistory.length);
    }
}

class WorkshopTicket extends EventTicket {

    public WorkshopTicket(double basePrice) {
        super(basePrice);
    }

    public WorkshopTicket(String attendeeId, double basePrice) {
        super(attendeeId, basePrice);
    }

    @Override
    protected void applyLateFee(double amount) {
        super.applyLateFee(amount * 2);
    }
}