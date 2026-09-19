public class DeliveryAccount {
    private String studentId;
    private double orderValue;

    private static final double DEFAULT_ORDER_VALUE;

    static {
        DEFAULT_ORDER_VALUE = 300.0;
    }

    public DeliveryAccount(String studentId, double orderValue) {
        this.studentId = studentId;
        this.orderValue = orderValue;
    }

    public DeliveryAccount(String studentId) {
        this(studentId, DEFAULT_ORDER_VALUE);
    }

    public String getStudentId() {
        return studentId;
    }

    public double getOrderValue() {
        return orderValue;
    }

    public final double calculateSurgeFee(int delayMinutes) {
        if (delayMinutes <= 0) {
            return 0.0;
        }

        double tier1Minutes = Math.min(delayMinutes, 5);
        double tier2Minutes = Math.max(0, Math.min(delayMinutes - 5, 10));
        double tier3Minutes = Math.max(0, delayMinutes - 15);

        double fee = (tier1Minutes * 0.005 + tier2Minutes * 0.010 + tier3Minutes * 0.020) * orderValue;
        double minimumFloor = 0.01 * orderValue;

        return Math.max(fee, minimumFloor);
    }

    public void processAccount(DeliveryAccount account, double amount, int delayMinutes) {
        if (account == null) {
            return;
        }

        double surgeFee = account.calculateSurgeFee(delayMinutes);

        if (account instanceof Premium) {
            Premium premiumAccount = (Premium) account;
            premiumAccount.settlePremiumAccount(amount, surgeFee);
        } else {
            account.settleRegularAccount(amount, surgeFee);
        }
    }

    protected void settleRegularAccount(double amount, double surgeFee) {
    }

    public static void processBatch(DeliveryAccount[] accounts, double[] amounts, int[] delayMinutesArray) {
        int processedCount = 0;
        int nullSkippedCount = 0;
        int premiumCount = 0;
        int regularCount = 0;
        double grandTotalSurgeFees = 0.0;

        if (accounts == null) {
            System.out.println("0 processed | 0 null skipped | 0 premium | 0 regular | grand total surge fees = Rs 0.0");
            return;
        }

        int amountsLength = (amounts != null) ? amounts.length : 0;
        int delayMinutesLength = (delayMinutesArray != null) ? delayMinutesArray.length : 0;

        int minLength = Math.min(accounts.length, Math.min(amountsLength, delayMinutesLength));

        for (int i = 0; i < minLength; i++) {
            DeliveryAccount account = accounts[i];

            if (account == null) {
                nullSkippedCount++;
                continue;
            }

            processedCount++;
            double amount = amounts[i];
            int delayMinutes = delayMinutesArray[i];

            double surgeFee = account.calculateSurgeFee(delayMinutes);
            grandTotalSurgeFees += surgeFee;

            if (account instanceof Premium) {
                premiumCount++;
                ((Premium) account).settlePremiumAccount(amount, surgeFee);
            } else {
                regularCount++;
                account.settleRegularAccount(amount, surgeFee);
            }
        }

        if (accounts.length > minLength) {
            for (int i = minLength; i < accounts.length; i++) {
                if (accounts[i] == null) {
                    nullSkippedCount++;
                }
            }
        }

        System.out.printf("%d processed | %d null skipped | %d premium | %d regular | grand total surge fees = Rs %.1f%n",
                processedCount, nullSkippedCount, premiumCount, regularCount, grandTotalSurgeFees);
    }
}

class Premium extends DeliveryAccount {
    public Premium(String studentId, double orderValue) {
        super(studentId, orderValue);
    }

    public Premium(String studentId) {
        super(studentId);
    }

    public void settlePremiumAccount(double amount, double surgeFee) {
    }
}