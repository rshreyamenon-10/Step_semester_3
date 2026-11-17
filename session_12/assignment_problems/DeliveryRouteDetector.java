public class DeliveryRouteDetector {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static String detectLoop(Node head) {
        if (head == null || head.next == null) {
            return "Loop: no";
        }

        Node slow = head;
        Node fast = head;
        boolean hasLoop = false;

        // Phase 1: Detect loop using Floyd's Cycle-Finding Algorithm
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                hasLoop = true;
                break;
            }
        }

        if (!hasLoop) {
            return "Loop: no";
        }

        // Phase 2: Find the start of the loop
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        Node startNode = slow;

        // Phase 3: Find the length of the loop
        int length = 1;
        Node curr = startNode.next;
        while (curr != startNode) {
            length++;
            curr = curr.next;
        }

        return "Loop: yes, starts at stop " + startNode.data + ", length " + length;
    }

    public static void main(String[] args) {
        // Sample 1: stops = 10 -> 20 -> 30 -> 40 -> 50, pos = 2 (links back to 30)
        Node head1 = new Node(10);
        head1.next = new Node(20);
        Node node30 = new Node(30);
        head1.next.next = node30;
        node30.next = new Node(40);
        node30.next.next = new Node(50);
        node30.next.next.next = node30; // loops back to 30

        System.out.println("Expected Output: " + detectLoop(head1));

        // Sample 2: stops = 10 -> 20 -> 30, pos = -1
        Node head2 = new Node(10);
        head2.next = new Node(20);
        head2.next.next = new Node(30);

        System.out.println("Expected Output: " + detectLoop(head2));

        // Sample 3: stops = 7, pos = 0 (loops to itself)
        Node head3 = new Node(7);
        head3.next = head3;

        System.out.println("Expected Output: " + detectLoop(head3));
    }
}