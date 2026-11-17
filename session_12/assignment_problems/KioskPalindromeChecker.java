public class KioskPalindromeChecker {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static boolean isPalindrome(Node head) {
        if (head == null || head.next == null) {
            return true;
        }

        Node slow = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        Node secondHalfHead = reverseList(slow.next);
        Node secondHalfCopy = secondHalfHead; 

        Node p1 = head;
        Node p2 = secondHalfHead;
        boolean result = true;
        while (p2 != null) {
            if (p1.data != p2.data) {
                result = false;
                break;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        slow.next = reverseList(secondHalfCopy);

        return result;
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
            System.out.print(curr.data + (curr.next != null ? " -> " : " -> null"));
            curr = curr.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Sample 1: tokens = 4 -> 7 -> 9 -> 7 -> 4
        Node head1 = new Node(4);
        head1.next = new Node(7);
        head1.next.next = new Node(9);
        head1.next.next.next = new Node(7);
        head1.next.next.next.next = new Node(4);

        boolean isPal1 = isPalindrome(head1);
        System.out.println("Expected Output: Palindrome: " + isPal1);
        System.out.print("                  List after check: ");
        printList(head1);

        // Sample 2: tokens = 4 -> 7 -> 7 -> 9
        Node head2 = new Node(4);
        head2.next = new Node(7);
        head2.next.next = new Node(7);
        head2.next.next.next = new Node(9);

        boolean isPal2 = isPalindrome(head2);
        System.out.println("Expected Output: Palindrome: " + isPal2);
        System.out.print("                  List after check: ");
        printList(head2);
    }
}
