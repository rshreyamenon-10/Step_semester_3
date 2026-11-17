public class AnswerScriptReverser {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static Node reverseInGroups(Node head, int k) {
        if (head == null || k <= 1) {
            return head;
        }

        Node dummy = new Node(0);
        dummy.next = head;
        Node groupPrev = dummy;

        while (true) {
            // Check if there are at least k nodes left to reverse
            Node kth = groupPrev;
            for (int i = 0; i < k; i++) {
                kth = kth.next;
                if (kth == null) {
                    return dummy.next;
                }
            }

            Node groupNext = kth.next;
            Node prev = groupNext;
            Node curr = groupPrev.next;

            // Reverse k nodes
            while (curr != groupNext) {
                Node nextNode = curr.next;
                curr.next = prev;
                prev = curr;
                curr = nextNode;
            }

            Node temp = groupPrev.next;
            groupPrev.next = kth;
            groupPrev = temp;
        }
    }

    public static void printList(Node head) {
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.data + (curr.next != null ? " -> " : " -> null"));
            curr = curr.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Sample 1: scripts = 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8, k = 3
        Node head1 = new Node(1);
        Node curr = head1;
        for (int i = 2; i <= 8; i++) {
            curr.next = new Node(i);
            curr = curr.next;
        }

        head1 = reverseInGroups(head1, 3);
        printList(head1);

        // Sample 2: scripts = 11 -> 12, k = 3
        Node head2 = new Node(11);
        head2.next = new Node(12);

        head2 = reverseInGroups(head2, 3);
        printList(head2);
    }
}