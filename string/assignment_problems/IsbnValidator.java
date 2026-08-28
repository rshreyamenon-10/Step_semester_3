public class IsbnValidator {

    public static String normalizeCode(String raw) {
        String trimmed = raw.trim();
        return trimmed.substring(0, 3).toUpperCase() + trimmed.substring(3);
    }

    public static String validateAndFormat(String code) {

        if (code.length() != 13) {
            return "Invalid: wrong length";
        }

        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(code.charAt(i))) {
                return "Invalid: non-letter publisher code";
            }
        }

        for (int i = 3; i < 13; i++) {
            if (!Character.isDigit(code.charAt(i))) {
                return "Invalid: non-digit body";
            }
        }

        String pub = code.substring(0, 3);
        String year = code.substring(3, 7);
        String catalog = code.substring(7);

        return "[" + pub + "] YEAR: " + year + " | CATALOG: " + catalog;
    }

    public static void main(String[] args) {
        String rawInput = " pen2026004251 ";
        
        String normalized = normalizeCode(rawInput);
        String output = validateAndFormat(normalized);

        System.out.println(output);
    }
}