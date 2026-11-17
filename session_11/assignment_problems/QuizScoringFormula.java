import java.util.Stack;

public class QuizScoringFormula {

    public static int evaluate(String expr) {
        if (expr == null || expr.trim().isEmpty()) {
            return 0;
        }

        Stack<Integer> values = new Stack<>();
        Stack<Character> ops = new Stack<>();
        int i = 0;

        while (i < expr.length()) {
            char c = expr.charAt(i);

            // Skip whitespace
            if (c == ' ') {
                i++;
                continue;
            }

            // Handle numbers (potentially multi-digit)
            if (Character.isDigit(c)) {
                int val = 0;
                while (i < expr.length() && Character.isDigit(expr.charAt(i))) {
                    val = val * 10 + (expr.charAt(i) - '0');
                    i++;
                }
                values.push(val);
                continue;
            }

            // Handle opening parenthesis
            if (c == '(') {
                ops.push(c);
            }
            // Handle closing parenthesis
            else if (c == ')') {
                while (!ops.isEmpty() && ops.peek() != '(') {
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                }
                if (!ops.isEmpty() && ops.peek() == '(') {
                    ops.pop(); // remove '('
                }
            }
            // Handle operators (+, -, *, /)
            else if (c == '+' || c == '-' || c == '*' || c == '/') {
                while (!ops.isEmpty() && hasPrecedence(c, ops.peek())) {
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                }
                ops.push(c);
            }
            i++;
        }

        // Apply remaining operators in the stack
        while (!ops.isEmpty()) {
            values.push(applyOp(ops.pop(), values.pop(), values.pop()));
        }

        return values.pop();
    }

    // Returns true if op2 has higher or equal precedence than op1
    private static boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')') {
            return false;
        }
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) {
            return false;
        }
        return true;
    }

    // A utility method to apply an operator
    private static int applyOp(char op, int b, int a) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) {
                    throw new UnsupportedOperationException("Cannot divide by zero");
                }
                return a / b;
        }
        return 0;
    }

    public static void main(String[] args) {
        String expr1 = "2 * (3 + 4) - 10 / 5";
        System.out.println("Expected Output: " + evaluate(expr1));

        String expr2 = "(8 - 2) * (1 + 2) / 4";
        System.out.println("Expected Output: " + evaluate(expr2) + " (18 / 4 with integer division)");
    }
}
