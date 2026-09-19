public class FareSplitter {
    private String tripId;
    private double totalFare;
    private int passengerCount;

    public FareSplitter(String tripId, double totalFare, int passengerCount) {
        if (totalFare < 0 || passengerCount <= 0) {
            throw new IllegalArgumentException("Invalid fare or passenger count");
        }
        this.tripId = tripId;
        this.totalFare = totalFare;
        this.passengerCount = passengerCount;
    }

    public FareSplitter(String tripId, double totalFare) {
        this(tripId, totalFare, 2);
    }

    public FareSplitter(String tripId) {
        this(tripId, 0.0, 2);
    }

    public double[] fareBreakdown() {
        double[] shares = new double[passengerCount];
        
        double baseShare = Math.floor((totalFare / passengerCount) * 100.0) / 100.0;
        
        for (int i = 0; i < passengerCount; i++) {
            shares[i] = baseShare;
        }

        double allocatedSum = baseShare * passengerCount;
        double remainder = Math.round((totalFare - allocatedSum) * 100.0) / 100.0;

        shares[passengerCount - 1] = Math.round((shares[passengerCount - 1] + remainder) * 100.0) / 100.0;

        return shares;
    }

    public boolean isConfirmationOverdue(int confirmed, int expected) {
        return confirmed < expected;
    }
}