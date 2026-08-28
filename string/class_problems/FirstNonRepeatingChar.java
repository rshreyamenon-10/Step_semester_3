public class FirstNonRepeatingChar {

    public static char findFirstNonRepeatingChar(String text) {
        int[] count = new int[256]; 

        for (int i = 0; i < text.length(); i++) {
            count[text.charAt(i)]++;
        }

        for (int i = 0; i < text.length(); i++) {
            if (count[text.charAt(i)] == 1) {
                return text.charAt(i);
            }
        }

        return '_';
    }

    public static void main(String[] args) {
        String input1 = "swiss";
        char result1 = findFirstNonRepeatingChar(input1);
        if (result1 != '_') {
            System.out.println("First Non-Repeating Character: '" + result1 + "'");
        } else {
            System.out.println("No Non-Repeating Character Found");
        }

        String input2 = "aabbcc";
        char result2 = findFirstNonRepeatingChar(input2);
        if (result2 != '_') {
            System.out.println("First Non-Repeating Character: '" + result2 + "'");
        } else {
            System.out.println("No Non-Repeating Character Found");
        }
    }
}