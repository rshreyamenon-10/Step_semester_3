public class DeliverySlot {
    private static final String DEFAULT_TIME_SLOT = "ASAP";

    private final String orderId;
    private final String timeSlot;

    public DeliverySlot(String orderId, String timeSlot) {
        this.orderId = orderId;
        this.timeSlot = timeSlot;
    }

    public DeliverySlot(String orderId) {
        this(orderId, DEFAULT_TIME_SLOT);
    }

    public boolean isPeakHour() {
        if (timeSlot == null) {
            return false;
        }
        return timeSlot.equals("12:00-13:00") ||
               timeSlot.equals("13:00-14:00") ||
               timeSlot.equals("19:00-20:00") ||
               timeSlot.equals("20:00-21:00");
    }

    public String getOrderId() {
        return orderId;
    }

    public String getTimeSlot() {
        return timeSlot;
    }
}