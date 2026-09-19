public final class BoardingPenaltyCalculator {
    private final double minimumPenaltyPercent;

    public BoardingPenaltyCalculator(double minimumPenaltyPercent) {
        this.minimumPenaltyPercent = minimumPenaltyPercent;
    }

    public final double calculatePenalty(double ticketFare, int minutesLate) {
        if (ticketFare < 0 || minutesLate < 0) {
            throw new IllegalArgumentException("Ticket fare and minutes late must not be negative.");
        }

        if (minutesLate == 0) {
            return 0.0;
        }

        double Tier1Minutes = Math.min(minutesLate, 5);
        double Tier2Minutes = Math.max(0, Math.min(minutesLate - 5, 10));
        double Tier3Minutes = Math.max(0, minutesLate - 15);

        double tier1Penalty = Tier1Minutes * 0.005 * ticketFare;
        double tier2Penalty = Tier2Minutes * 0.010 * ticketFare;
        double tier3Penalty = Tier3Minutes * 0.020 * ticketFare;

        double calculatedPenalty = tier1Penalty + tier2Penalty + tier3Penalty;
        double minimumPenaltyFloor = (this.minimumPenaltyPercent / 100.0) * ticketFare;

        return Math.max(calculatedPenalty, minimumPenaltyFloor);
    }
}public final class BoardingPenaltyCalculator {
    private final double minimumPenaltyPercent;

    public BoardingPenaltyCalculator(double minimumPenaltyPercent) {
        this.minimumPenaltyPercent = minimumPenaltyPercent;
    }

    public final double calculatePenalty(double ticketFare, int minutesLate) {
        if (ticketFare < 0 || minutesLate < 0) {
            throw new IllegalArgumentException("Ticket fare and minutes late must not be negative.");
        }

        if (minutesLate == 0) {
            return 0.0;
        }

        double Tier1Minutes = Math.min(minutesLate, 5);
        double Tier2Minutes = Math.max(0, Math.min(minutesLate - 5, 10));
        double Tier3Minutes = Math.max(0, minutesLate - 15);

        double tier1Penalty = Tier1Minutes * 0.005 * ticketFare;
        double tier2Penalty = Tier2Minutes * 0.010 * ticketFare;
        double tier3Penalty = Tier3Minutes * 0.020 * ticketFare;

        double calculatedPenalty = tier1Penalty + tier2Penalty + tier3Penalty;
        double minimumPenaltyFloor = (this.minimumPenaltyPercent / 100.0) * ticketFare;

        return Math.max(calculatedPenalty, minimumPenaltyFloor);
    }
}