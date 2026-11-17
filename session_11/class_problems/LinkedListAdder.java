public class LinkedListAdder {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static Node addNumbers(Node a, Node b) {
        Node dummy = new Node(0);
        Node tail = dummy;
        Node currA = a;
        Node currB = b;
        int carry = 0;

        while (currA != null || currB != null || carry != 0) {
            int sum = carry;

            if (currA != null) {
                sum += currA.data;
                currA = currA.next;
            }

            if (currB != null) {
                sum += currB.data;
                currB = currB.next;
            }

            carry = sum / 10;
            tail.next = new Node(sum % 10);
            tail = tail.next;
        }

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
        // Sample Input 1: a = 2 -> 4 -> 3, b = 5 -> 6 -> 4 (342 + 465 = 807)
        Node a1 = new Node(2);
        a1.next = new Node(4);
        a1.next.next = new Node(3);

        Node b1 = new Node(5);
        b1.next = new Node(6);
        b1.next.next = new Node(4);

        Node result1 = addNumbers(a1, b1);
        printList(result1);

        // Sample Input 2: a = 9 -> 9, b = 1 (99 + 1 = 100)
        Node a2 = new Node(9);
        a2.next = new Node(9);

        Node b2 = new Node(1);

        Node result2 = addNumbers(a2, b2);
        printList(result2);
    }
}
