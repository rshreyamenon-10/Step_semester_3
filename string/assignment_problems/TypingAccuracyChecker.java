public class TypingAccuracyChecker {

    public static void checkTypingAccuracy(String original, String typed) {
        int matches = 0;
        int mismatchPos = -1;

        // Loop through each character
        for (int i = 0; i < original.length(); i++) {
            if (original.charAt(i) == typed.charAt(i)) {
                matches++;
            } else if (mismatchPos == -1) {
                mismatchPos = i + 1; 
            }
        }

        double accuracy = (matches * 100.0) / original.length();
        System.out.print("Matched: " + matches + "/" + original.length() + " | Accuracy: ");
        System.out.printf("%.2f%%", accuracy);


        if (mismatchPos != -1) {
            int index = mismatchPos - 1;
            System.out.println(" First Mismatch at position : " + mismatchPos + 
                               " ('" + original.charAt(index) + "' vs '" + typed.charAt(index) + "')");
        } else {
            System.out.println(" No Mismatches");
        }
    }

    public static void main(String[] args) {
        checkTypingAccuracy("hello world", "hello worlt");
        checkTypingAccuracy("coding", "coding");
    }
}