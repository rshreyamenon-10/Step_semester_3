public class FoodOrder {
    private final String studentName;
    private final String dishName;
    private boolean isDelivered;

    public FoodOrder(String studentName, String dishName) {
        if (!isValid(studentName) || !isValid(dishName)) {
            throw new IllegalArgumentException("Invalid student name or dish name.");
        }
        this.studentName = studentName;
        this.dishName = dishName;
        this.isDelivered = false;
    }

    private static boolean isValid(String str) {
        return str != null && !str.trim().isEmpty();
    }

    public void markDelivered() {
        if (this.isDelivered) {
            System.out.println("Warning: Order for " + studentName + " (" + dishName + ") was already marked delivered!");
        } else {
            this.isDelivered = true;
            System.out.println("Order for " + studentName + " (" + dishName + ") marked as delivered.");
        }
    }

    public String getStudentName() {
        return studentName;
    }

    public String getDishName() {
        return dishName;
    }

    public boolean isDelivered() {
        return isDelivered;
    }

    public static void processBatch(String[][] rawOrders) {
        int validCount = 0;
        int rejectedCount = 0;

        if (rawOrders != null) {
            for (String[] order : rawOrders) {
                if (order == null || order.length < 2) {
                    rejectedCount++;
                    continue;
                }

                String studentName = order[0];
                String dishName = order[1];

                try {
                    new FoodOrder(studentName, dishName);
                    validCount++;
                } catch (IllegalArgumentException e) {
                    rejectedCount++;
                }
            }
        }

        System.out.println("Valid: " + validCount + " | Rejected: " + rejectedCount);
    }
}