public class LinkedListReorder {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static void reorderList(Node head) {
        if (head == null || head.next == null) {
            return;
        }

        // Step 1: Find the middle of the list using slow and fast pointers
        Node slow = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Step 2: Split the list into two halves and reverse the second half
        Node secondHalfHead = slow.next;
        slow.next = null; // Break the list into two
        secondHalfHead = reverseList(secondHalfHead);

        // Step 3: Merge the two halves alternately
        Node firstHalfHead = head;
        while (secondHalfHead != null) {
            Node temp1 = firstHalfHead.next;
            Node temp2 = secondHalfHead.next;

            firstHalfHead.next = secondHalfHead;
            secondHalfHead.next = temp1;

            firstHalfHead = temp1;
            secondHalfHead = temp2;
        }
    }

    private static Node reverseList(Node head) {
        Node prev = null;
        Node curr = head;
        while (curr != null) {
            Node nextNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextNode;
        }
        return prev;
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
        // Sample Input 1: 1 -> 2 -> 3 -> 4
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);

        reorderList(head1);
        printList(head1);

        // Sample Input 2: 1 -> 2 -> 3 -> 4 -> 5
        Node head2 = new Node(1);
        head2.next = new Node(2);
        head2.next.next = new Node(3);
        head2.next.next.next = new Node(4);
        head2.next.next.next.next = new Node(5);

        reorderList(head2);
        printList(head2);
    }
}
