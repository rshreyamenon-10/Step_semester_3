public class LibraryBookSorter {

    static class Node {
        String title;
        int dueDay;
        Node next;

        Node(String title, int dueDay) {
            this.title = title;
            this.dueDay = dueDay;
            this.next = null;
        }
    }

    public static Node sortByDueDay(Node head) {
        // Base case: if head is null or only has one node, it's already sorted
        if (head == null || head.next == null) {
            return head;
        }

        // Step 1: Split the list into two halves using slow and fast pointers
        Node middle = getMiddle(head);
        Node nextOfMiddle = middle.next;
        middle.next = null; // Break the list

        // Step 2: Recursively sort both halves
        Node left = sortByDueDay(head);
        Node right = sortByDueDay(nextOfMiddle);

        // Step 3: Merge the sorted halves
        return sortedMerge(left, right);
    }

    private static Node getMiddle(Node head) {
        if (head == null) {
            return head;
        }

        Node slow = head;
        Node fast = head;

        // Using fast.next != null && fast.next.next != null to split at the correct middle
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    private static Node sortedMerge(Node a, Node b) {
        Node dummy = new Node("", 0);
        Node tail = dummy;

        while (a != null && b != null) {
            // Using '<=' to take from the left half on ties to preserve stability (drop order)
            if (a.dueDay <= b.dueDay) {
                tail.next = a;
                a = a.next;
            } else {
                tail.next = b;
                b = b.next;
            }
            tail = tail.next;
        }

        // Attach remaining nodes
        if (a != null) {
            tail.next = a;
        } else {
            tail.next = b;
        }

        return dummy.next;
    }

    public static void printList(Node head) {
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.title + ":" + curr.dueDay + (curr.next != null ? " -> " : " -> null"));
            curr = curr.next;
        }
        if (head == null) {
            System.out.println("null");
        } else {
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // Sample 1: returns = Java:15 -> DSA:3 -> OS:9 -> DBMS:3 -> CN:12 -> AI:1
        Node head1 = new Node("Java", 15);
        head1.next = new Node("DSA", 3);
        head1.next.next = new Node("OS", 9);
        head1.next.next.next = new Node("DBMS", 3);
        head1.next.next.next.next = new Node("CN", 12);
        head1.next.next.next.next.next = new Node("AI", 1);

        System.out.print("Expected Output: ");
        Node sorted1 = sortByDueDay(head1);
        printList(sorted1);

        // Sample 2: returns = (empty)
        Node head2 = null;
        System.out.print("Expected Output: ");
        Node sorted2 = sortByDueDay(head2);
        printList(sorted2);
    }
}