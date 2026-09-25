public class EventTicket5 {

    private static int nextIdNum = 1001;
    private static int totalTicketsIssued = 0;

    private final String ticketId;
    private double basePrice;
    private double balanceDue;
    private String lastPaymentMode;

    public EventTicket(double basePrice) {
        this.ticketId = "TCK-" + nextIdNum++;
        totalTicketsIssued++;
        this.basePrice = basePrice;
        this.balanceDue = basePrice;
    }

    public String getTicketId() {
        return ticketId;
    }

    public double getBasePrice() {
        return basePrice;
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

    public static boolean isValidPromoCode(String code) {
        if (code == null || code.length() != 5) {
            return false;
        }
        if (code.charAt(0) != 'F') {
            return false;
        }
        if (!Character.isDigit(code.charAt(1)) || 
            !Character.isDigit(code.charAt(2)) || 
            !Character.isDigit(code.charAt(3))) {
            return false;
        }
        return Character.isUpperCase(code.charAt(4));
    }

    public static int getTicketsIssued() {
        return totalTicketsIssued;
    }

    public static String processNightlySettlement(EventTicket[] tickets) {
        if (tickets == null) {
            return "Processed: 0 | Group Tickets: 0";
        }

        int totalCount = 0;
        int groupCount = 0;

        for (EventTicket ticket : tickets) {
            if (ticket == null) {
                continue;
            }

            totalCount++;
            if (ticket instanceof GroupTicket) {
                groupCount++;
            }
        }

        return "Processed: " + totalCount + " | Group Tickets: " + groupCount;
    }
}

class GroupTicket extends EventTicket {

    private int groupSize;

    public GroupTicket(double basePrice, int groupSize) {
        super(basePrice);
        this.groupSize = groupSize;
    }

    public int getGroupSize() {
        return groupSize;
    }
}