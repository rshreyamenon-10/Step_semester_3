public class BusTicketAccount {
    private String bookingId;
    private double ticketFare;

    private static final double DEFAULT_FARE;

    static {
        DEFAULT_FARE = 500.0;
    }

    public BusTicketAccount(String bookingId, double ticketFare) {
        this.bookingId = bookingId;
        this.ticketFare = ticketFare;
    }

    public BusTicketAccount(String bookingId) {
        this(bookingId, DEFAULT_FARE);
    }

    public String getBookingId() {
        return bookingId;
    }

    public double getTicketFare() {
        return ticketFare;
    }

    public final double calculatePenalty(int minutesLate) {
        if (minutesLate <= 0) {
            return 0.0;
        }

        double tier1Minutes = Math.min(minutesLate, 5);
        double tier2Minutes = Math.max(0, Math.min(minutesLate - 5, 10));
        double tier3Minutes = Math.max(0, minutesLate - 15);

        double penalty = (tier1Minutes * 0.005 + tier2Minutes * 0.010 + tier3Minutes * 0.020) * ticketFare;
        double minimumFloor = 0.01 * ticketFare;

        return Math.max(penalty, minimumFloor);
    }

    public void processAccount(BusTicketAccount account, double amount, int minutesLate) {
        if (account == null) {
            return;
        }

        double penalty = account.calculatePenalty(minutesLate);

        if (account instanceof Sleeper) {
            Sleeper sleeperAccount = (Sleeper) account;
            sleeperAccount.settleSleeperAccount(amount, penalty);
        } else {
            account.settleRegularAccount(amount, penalty);
        }
    }

    protected void settleRegularAccount(double amount, double penalty) {
    }

    public static void processBatch(BusTicketAccount[] accounts, double[] amounts, int[] minutesLateArray) {
        int processedCount = 0;
        int nullSkippedCount = 0;
        int sleeperCount = 0;
        int regularCount = 0;
        double grandTotalPenalties = 0.0;

        if (accounts == null) {
            System.out.println("0 processed | 0 null skipped | 0 sleeper | 0 regular | grand total penalties = Rs 0.0");
            return;
        }

        int amountsLength = (amounts != null) ? amounts.length : 0;
        int minutesLateLength = (minutesLateArray != null) ? minutesLateArray.length : 0;

        int minLength = Math.min(accounts.length, Math.min(amountsLength, minutesLateLength));

        for (int i = 0; i < minLength; i++) {
            BusTicketAccount account = accounts[i];

            if (account == null) {
                nullSkippedCount++;
                continue;
            }

            processedCount++;
            double amount = amounts[i];
            int minutesLate = minutesLateArray[i];

            double penalty = account.calculatePenalty(minutesLate);
            grandTotalPenalties += penalty;

            if (account instanceof Sleeper) {
                sleeperCount++;
                ((Sleeper) account).settleSleeperAccount(amount, penalty);
            } else {
                regularCount++;
                account.settleRegularAccount(amount, penalty);
            }
        }

        if (accounts.length > minLength) {
            for (int i = minLength; i < accounts.length; i++) {
                if (accounts[i] == null) {
                    nullSkippedCount++;
                }
            }
        }

        System.out.printf("%d processed | %d null skipped | %d sleeper | %d regular | grand total penalties = Rs %.1f%n",
                processedCount, nullSkippedCount, sleeperCount, regularCount, grandTotalPenalties);
    }
}

class Sleeper extends BusTicketAccount {
    public Sleeper(String bookingId, double ticketFare) {
        super(bookingId, ticketFare);
    }

    public Sleeper(String bookingId) {
        super(bookingId);
    }

    public void settleSleeperAccount(double amount, double penalty) {
    }
}