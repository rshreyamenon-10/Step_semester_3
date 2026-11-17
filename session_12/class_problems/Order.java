public class Order {

    // Static nested class for LineItem
    public static class LineItem {
        String productName;
        int quantity;
        double unitPrice;

        public LineItem(String productName, int quantity, double unitPrice) {
            this.productName = productName;
            this.quantity = quantity;
            this.unitPrice = unitPrice;
        }

        public double total() {
            return quantity * unitPrice;
        }
    }

    private LineItem[] items;
    private int count;

    public Order(int capacity) {
        items = new LineItem[capacity];
        count = 0;
    }

    public void addItem(String productName, int quantity, double unitPrice) {
        if (count < items.length) {
            items[count++] = new LineItem(productName, quantity, unitPrice);
        }
    }

    // Member inner class for Cursor
    public class Cursor {
        private int currentIndex = 0;

        public boolean hasNext() {
            return currentIndex < count;
        }

        public LineItem next() {
            if (!hasNext()) {
                return null;
            }
            return items[currentIndex++];
        }

        public void reset() {
            currentIndex = 0;
        }
    }

    public Cursor getCursor() {
        return new Cursor();
    }

    public static void main(String[] args) {
        Order order = new Order(3);
        order.addItem("Pen", 3, 10);
        order.addItem("Book", 2, 250);
        order.addItem("Bag", 1, 700);

        Order.Cursor cursor = order.getCursor();
        double orderTotal = 0;

        while (cursor.hasNext()) {
            Order.LineItem item = cursor.next();
            double itemTotal = item.total();
            orderTotal += itemTotal;
            System.out.println(item.productName + " " + (int) itemTotal);
        }

        System.out.println("Total " + (int) orderTotal);
        System.out.println("cursor: inner (reads the order's array)");
        System.out.println("line item: static nested (needs no order)");
    }
}
