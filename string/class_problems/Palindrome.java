import java.util.Scanner;

public class Palindrome {

    // Iterative Method
    static boolean isPalindromeIterative(String text) {
        int left = 0;
        int right = text.length() - 1;

        while (left < right) {
            if (text.charAt(left) != text.charAt(right))
                return false;
            left++;
            right--;
        }
        return true;
    }

    // Recursive Method
    static boolean isPalindromeRecursive(String text) {
        return check(text, 0, text.length() - 1);
    }

    static boolean check(String text, int left, int right) {
        if (left >= right)
            return true;

        if (text.charAt(left) != text.charAt(right))
            return false;

        return check(text, left + 1, right - 1);
    }

    // Array Reversal Method
    static boolean isPalindromeArrayReversal(String text) {
        char[] original = text.toCharArray();
        char[] reversed = text.toCharArray();

        for (int i = 0, j = reversed.length - 1; i < j; i++, j--) {
            char temp = reversed[i];
            reversed[i] = reversed[j];
            reversed[j] = temp;
        }

        for (int i = 0; i < original.length; i++) {
            if (original[i] != reversed[i])
                return false;
        }

        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a text: ");
        String text = sc.nextLine();

        boolean iterative = isPalindromeIterative(text);
        boolean recursive = isPalindromeRecursive(text);
        boolean arrayReversal = isPalindromeArrayReversal(text);

        System.out.println("\nResults:");
        System.out.println("Iterative Check: " + iterative);
        System.out.println("Recursive Check: " + recursive);
        System.out.println("Array Reversal Check: " + arrayReversal);

        sc.close();
    }
}