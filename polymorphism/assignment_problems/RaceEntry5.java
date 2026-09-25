public class RaceEntry5 {

    private static int nextCounter = 1001;
    private static int totalEntriesCreated = 0;

    private final String entryCode;
    private String bibNumber;
    private double entryFee;
    private double balanceDue;
    private String lastPaymentMode;

    public RaceEntry(String bibNumber, double entryFee) {
        if (bibNumber == null || bibNumber.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid bib number");
        }
        this.entryCode = "ENT-" + nextCounter++;
        totalEntriesCreated++;
        this.bibNumber = bibNumber;
        this.entryFee = entryFee;
        this.balanceDue = entryFee;
    }

    public String getEntryCode() {
        return entryCode;
    }

    public String getBibNumber() {
        return bibNumber;
    }

    public double getEntryFee() {
        return entryFee;
    }

    public double getBalanceDue() {
        return balanceDue;
    }

    public String getLastPaymentMode() {
        return lastPaymentMode;
    }

    public void pay(double amount) {
        if (amount > 0) {
            this.balanceDue -= amount;
        }
    }

    public void pay(double amount, String mode) {
        pay(amount);
        this.lastPaymentMode = mode;
    }

    public static boolean isValidDiscountCode(String code) {
        if (code == null || code.length() != 5) {
            return false;
        }
        if (code.charAt(0) != 'M') {
            return false;
        }
        if (!Character.isDigit(code.charAt(1)) || 
            !Character.isDigit(code.charAt(2)) || 
            !Character.isDigit(code.charAt(3))) {
            return false;
        }
        return Character.isUpperCase(code.charAt(4));
    }

    public static int getBibCounter() {
        return totalEntriesCreated;
    }

    public static String settleNight(RaceEntry[] entries) {
        if (entries == null) {
            return "0 processed | 0 null skipped | 0 relay | 0 individual";
        }

        int processedCount = 0;
        int nullCount = 0;
        int relayCount = 0;
        int individualCount = 0;

        for (RaceEntry entry : entries) {
            if (entry == null) {
                nullCount++;
                continue;
            }

            processedCount++;
            if (entry instanceof RelayTeamEntry) {
                relayCount++;
            } else {
                individualCount++;
            }
        }

        return processedCount + " processed | " + nullCount + " null skipped | " + relayCount + " relay | " + individualCount + " individual";
    }
}

class RelayTeamEntry extends RaceEntry {

    private int teamSize;

    public RelayTeamEntry(String bibNumber, double entryFee, int teamSize) {
        super(bibNumber, entryFee);
        this.teamSize = teamSize;
    }

    public int getTeamSize() {
        return teamSize;
    }
}