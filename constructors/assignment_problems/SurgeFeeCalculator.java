public final class SurgeFeeCalculator {
    private final double minimumSurgePercent;

    public SurgeFeeCalculator(double minimumSurgePercent) {
        this.minimumSurgePercent = minimumSurgePercent;
    }

    public final double calculateSurgeFee(double orderValue, int delayMinutes) {
        if (orderValue < 0 || delayMinutes < 0) {
            throw new IllegalArgumentException("Order value and delay minutes must not be negative.");
        }

        if (delayMinutes == 0) {
            return 0.0;
        }

        double tier1Minutes = Math.min(delayMinutes, 5);
        double tier2Minutes = Math.max(0, Math.min(delayMinutes - 5, 10));
        double tier3Minutes = Math.max(0, delayMinutes - 15);

        double tier1Fee = tier1Minutes * 0.005 * orderValue;
        double tier2Fee = tier2Minutes * 0.010 * orderValue;
        double tier3Fee = tier3Minutes * 0.020 * orderValue;

        double calculatedFee = tier1Fee + tier2Fee + tier3Fee;
        double minimumSurgeFloor = (this.minimumSurgePercent / 100.0) * orderValue;

        return Math.max(calculatedFee, minimumSurgeFloor);
    }
}