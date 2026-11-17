public class PolynomialCalculator {

    static class Node {
        int coeff;
        int exp;
        Node next;

        Node(int coeff, int exp) {
            this.coeff = coeff;
            this.exp = exp;
            this.next = null;
        }
    }

    public static Node addPolynomials(Node p, Node q) {
        Node dummy = new Node(0, 0);
        Node tail = dummy;

        Node currP = p;
        Node currQ = q;

        while (currP != null && currQ != null) {
            if (currP.exp > currQ.exp) {
                tail.next = new Node(currP.coeff, currP.exp);
                currP = currP.next;
                tail = tail.next;
            } else if (currP.exp < currQ.exp) {
                tail.next = new Node(currQ.coeff, currQ.exp);
                currQ = currQ.next;
                tail = tail.next;
            } else {
                int sumCoeff = currP.coeff + currQ.coeff;
                if (sumCoeff != 0) {
                    tail.next = new Node(sumCoeff, currP.exp);
                    tail = tail.next;
                }
                currP = currP.next;
                currQ = currQ.next;
            }
        }

        while (currP != null) {
            tail.next = new Node(currP.coeff, currP.exp);
            currP = currP.next;
            tail = tail.next;
        }

        while (currQ != null) {
            tail.next = new Node(currQ.coeff, currQ.exp);
            currQ = currQ.next;
            tail = tail.next;
        }

        return dummy.next;
    }

    public static void printPolynomial(Node head) {
        if (head == null) {
            System.out.println("0");
            return;
        }

        StringBuilder sb = new StringBuilder();
        Node curr = head;
        boolean first = true;

        while (curr != null) {
            if (curr.coeff == 0) {
                curr = curr.next;
                continue;
            }

            if (!first) {
                if (curr.coeff > 0) {
                    sb.append(" + ");
                } else {
                    sb.append(" - ");
                }
            } else {
                if (curr.coeff < 0) {
                    sb.append("-");
                }
            }

            int absCoeff = Math.abs(curr.coeff);
            if (curr.exp == 0) {
                sb.append(absCoeff);
            } else if (curr.exp == 1) {
                if (absCoeff != 1) {
                    sb.append(absCoeff);
                }
                sb.append("x");
            } else {
                if (absCoeff != 1) {
                    sb.append(absCoeff);
                }
                sb.append("x^").append(curr.exp);
            }

            first = false;
            curr = curr.next;
        }

        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        // Sample 1
        Node p1 = new Node(5, 3);
        p1.next = new Node(4, 2);
        p1.next.next = new Node(2, 0);

        Node q1 = new Node(5, 2);
        q1.next = new Node(-3, 1);
        q1.next.next = new Node(1, 0);

        Node result1 = addPolynomials(p1, q1);
        printPolynomial(result1);

        // Sample 2
        Node p2 = new Node(3, 2);
        p2.next = new Node(1, 0);

        Node q2 = new Node(-3, 2);
        q2.next = new Node(4, 1);

        Node result2 = addPolynomials(p2, q2);
        printPolynomial(result2);
    }
}
```[cite: 15]