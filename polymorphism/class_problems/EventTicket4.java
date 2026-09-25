public class EventTicket4 {

    private double basePrice;

    public EventTicket(double basePrice) {
        this.basePrice = basePrice;
    }

    public String printTicket() {
        return "Standard | Balance: " + basePrice;
    }

    public static String batchPrint(EventTicket[] tickets) {
        StringBuilder sb = new StringBuilder();

        for (EventTicket ticket : tickets) {
            sb.append(ticket.printTicket());

            if (ticket instanceof WorkshopTicket) {
                WorkshopTicket wt = (WorkshopTicket) ticket;
                sb.append(" [Track via downcast: ").append(wt.getTrack()).append("]");
            }

            sb.append(" | ");
        }

        return sb.toString();
    }
}

class WorkshopTicket extends EventTicket {

    private String track;

    public WorkshopTicket(double basePrice, String track) {
        super(basePrice);
        this.track = track;
    }

    public String getTrack() {
        return track;
    }

    @Override
    public String printTicket() {
        return "Workshop | Track: " + track + " | Balance: " + getBasePrice();
    }

    private double getBasePrice() {
        return super.printTicket().replace("Standard | Balance: ", "").isEmpty() ? 0.0 : Double.parseDouble(super.printTicket().replace("Standard | Balance: ", ""));
    }
}