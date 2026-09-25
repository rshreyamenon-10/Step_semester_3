public class RaceEntry {

    private String bibNumber;
    private double entryFee;
    private double balanceDue;

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

    public String getBibNumber() {
        return bibNumber;
    }

    public double getEntryFee() {
        return entryFee;
    }

    public static String registerBatch(String[] bibNumbers, double entryFee) {
        if (bibNumbers == null) {
            return "Registered: 0 | Rejected: 0";
        }

        int registered = 0;
        int rejected = 0;

        for (String bib : bibNumbers) {
            try {
                new RaceEntry(bib, entryFee);
                registered++;
            } catch (Exception e) {
                rejected++;
            }
        }

        return "Registered: " + registered + " | Rejected: " + rejected;
    }
}

class RunnerEntry extends RaceEntry {

    private String category;

    public RunnerEntry(String bibNumber, double entryFee, String category) {
        super(bibNumber, entryFee);
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
}