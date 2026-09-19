import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class BusTicket {
    private final String passengerName;
    private final String destination;
    private boolean isCheckedIn;

    public BusTicket(String passengerName, String destination) {
        if (!isValidName(passengerName) || !isValidDestination(destination)) {
            throw new IllegalArgumentException("Invalid passenger name or destination.");
        }
        this.passengerName = passengerName;
        this.destination = destination;
        this.isCheckedIn = false;
    }

    private static boolean isValidName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return false;
        }
        return name.matches("^[a-zA-Z\\s]+$");
    }

    private static boolean isValidDestination(String destination) {
        if (destination == null || destination.trim().isEmpty()) {
            return false;
        }
        return destination.matches("^[a-zA-Z\\s]+$");
    }

    public void markCheckedIn() {
        if (this.isCheckedIn) {
            throw new IllegalStateException("Ticket has already been checked in.");
        }
        this.isCheckedIn = true;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public String getDestination() {
        return destination;
    }

    public boolean isCheckedIn() {
        return isCheckedIn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BusTicket ticket = (BusTicket) o;
        return Objects.equals(passengerName, ticket.passengerName) &&
               Objects.equals(destination, ticket.destination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(passengerName, destination);
    }

    public static void processBatch(String[][] rawBookings) {
        int validCount = 0;
        int rejectedCount = 0;
        int duplicateCount = 0;

        Set<String> acceptedPairs = new HashSet<>();

        if (rawBookings != null) {
            for (String[] booking : rawBookings) {
                if (booking == null || booking.length < 2) {
                    rejectedCount++;
                    continue;
                }

                String name = booking[0];
                String dest = booking[1];

                try {
                    BusTicket ticket = new BusTicket(name, dest);
                    String uniqueKey = name + "|" + dest;

                    if (acceptedPairs.contains(uniqueKey)) {
                        duplicateCount++;
                    } else {
                        acceptedPairs.add(uniqueKey);
                        validCount++;
                    }
                } catch (IllegalArgumentException e) {
                    rejectedCount++;
                }
            }
        }

        System.out.println("Valid: " + validCount + " | Rejected: " + rejectedCount + " | Duplicates skipped: " + duplicateCount);
    }
}