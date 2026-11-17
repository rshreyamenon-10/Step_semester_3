public class RideApp {

    abstract static class Ride {
        double distance;
        String typeName;

        Ride(String typeName, double distance) {
            this.typeName = typeName;
            this.distance = distance;
        }

        abstract double calculateFare();

        public String getTypeName() {
            return typeName;
        }
    }

    static class Auto extends Ride {
        Auto(double distance) {
            super("Auto", distance);
        }

        @Override
        double calculateFare() {
            return 30 + 15 * distance;
        }
    }

    static class Cab extends Ride {
        double surgeFactor;

        Cab(double distance, double surgeFactor) {
            super("Cab", distance);
            this.surgeFactor = surgeFactor;
        }

        @Override
        double calculateFare() {
            return (50 + 20 * distance) * surgeFactor;
        }
    }

    static class Bike extends Ride {
        int waitingMinutes;

        Bike(double distance, int waitingMinutes) {
            super("Bike", distance);
            this.waitingMinutes = waitingMinutes;
        }

        @Override
        double calculateFare() {
            return 20 + 10 * distance + 2 * waitingMinutes;
        }
    }

    public static void main(String[] args) {
        Ride[] rides = {
            new Auto(4),
            new Cab(6, 1.5),
            new Bike(3, 5)
        };

        double grandTotal = 0;

        for (Ride ride : rides) {
            double fare = ride.calculateFare();
            grandTotal += fare;
            System.out.println(ride.getTypeName() + ": " + (int) fare);
        }

        System.out.println("Total: " + (int) grandTotal);
    }
}
