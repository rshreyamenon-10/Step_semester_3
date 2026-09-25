import java.util.StringBuilder;

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

    public String announce() {
        return "Race Entry | Bib: " + bibNumber + " | Balance: " + balanceDue;
    }

    public static String announceAll(RaceEntry[] entries) {
        if (entries == null || entries.length == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        for (RaceEntry entry : entries) {
            if (entry == null) {
                continue;
            }

            // Polymorphic call to announce() for all entries
            sb.append(entry.announce());

            // Safe downcast specifically for RelayTeamEntry to extract team size
            if (entry instanceof RelayTeamEntry) {
                RelayTeamEntry relay = (RelayTeamEntry) entry;
                sb.append(" [Team size via downcast: ").append(relay.getTeamSize()).append("]");
            }

            sb.append(" | ");
        }

        return sb.toString();
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

    @Override
    public String announce() {
        return "Runner Entry | Bib: " + getBibNumber() + " | Category: " + category + " | Balance: " + getBalanceDue();
    }
}

class EliteRunnerEntry extends RunnerEntry {

    private double sponsorBonus;

    public EliteRunnerEntry(String bibNumber, double entryFee, String category, double sponsorBonus) {
        super(bibNumber, entryFee, category);
        this.sponsorBonus = sponsorBonus;
    }

    public double getSponsorBonus() {
        return sponsorBonus;
    }

    @Override
    public String announce() {
        return "Elite Runner | Bib: " + getBibNumber() + " | Category: " + getCategory() + " | Sponsor Bonus: " + sponsorBonus + " | Balance: " + getBalanceDue();
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

    @Override
    public String announce() {
        return "Relay Team | Bib: " + getBibNumber() + " | Team Size: " + teamSize + " | Balance: " + getBalanceDue();
    }
}