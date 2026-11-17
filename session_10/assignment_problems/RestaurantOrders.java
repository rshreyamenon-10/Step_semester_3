public class RestaurantOrders {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static Node removeKthFromEnd(Node head, int k) {
        Node dummy = new Node(0);
        dummy.next = head;
        Node lead = dummy;
        Node trail = dummy;

        for (int i = 0; i <= k; i++) {
            lead = lead.next;
        }

        while (lead != null) {
            lead = lead.next;
            trail = trail.next;
        }

        trail.next = trail.next.next;

        return dummy.next;
    }

    public static void printList(Node head) {
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.data + (curr.next != null ? " -> " : ""));
            curr = curr.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Test Sample 1
        Node head1 = new Node(10);
        head1.next = new Node(20);
        head1.next.next = new Node(30);
        head1.next.next.next = new Node(40);
        head1.next.next.next.next = new Node(50);

        head1 = removeKthFromEnd(head1, 2);
        printList(head1);

        // Test Sample 2
        Node head2 = new Node(10);
        head2.next = new Node(20);
        head2.next.next = new Node(30);
        head2.next.next.next = new Node(40);
        head2.next.next.next.next = new Node(50);

        head2 = removeKthFromEnd(head2, 5);
        printList(head2);
    }
}