public class LinkedListIntersection {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static Node findIntersection(Node headA, Node headB) {
        if (headA == null || headB == null) {
            return null;
        }

        int lenA = getLength(headA);
        int lenB = getLength(headB);

        Node currA = headA;
        Node currB = headB;

        if (lenA > lenB) {
            int diff = lenA - lenB;
            for (int i = 0; i < diff; i++) {
                currA = currA.next;
            }
        } else {
            int diff = lenB - lenA;
            for (int i = 0; i < diff; i++) {
                currB = currB.next;
            }
        }

        while (currA != null && currB != null) {
            if (currA == currB) {
                return currA;
            }
            currA = currA.next;
            currB = currB.next;
        }

        return null;
    }

    private static int getLength(Node head) {
        int length = 0;
        Node curr = head;
        while (curr != null) {
            length++;
            curr = curr.next;
        }
        return length;
    }

    public static void main(String[] args) {
        // Sample Input 1: Intersecting lists
        Node shared = new Node(8);
        shared.next = new Node(4);
        shared.next.next = new Node(5);

        Node headA1 = new Node(4);
        headA1.next = new Node(1);
        headA1.next.next = shared;

        Node headB1 = new Node(5);
        headB1.next = new Node(6);
        headB1.next.next = new Node(1);
        headB1.next.next.next = shared;

        Node intersection1 = findIntersection(headA1, headB1);
        if (intersection1 != null) {
            System.out.println("Node with value " + intersection1.data);
        } else {
            System.out.println("null");
        }

        // Sample Input 2: Non-intersecting lists
        Node headA2 = new Node(2);
        headA2.next = new Node(6);
        headA2.next.next = new Node(4);

        Node headB2 = new Node(1);
        headB2.next = new Node(5);

        Node intersection2 = findIntersection(headA2, headB2);
        if (intersection2 != null) {
            System.out.println("Node with value " + intersection2.data);
        } else {
            System.out.println("null");
        }
    }
}