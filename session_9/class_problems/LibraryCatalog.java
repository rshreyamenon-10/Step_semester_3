public class LibraryCatalog {

    public static String findBook(String[][] catalog, String targetIsbn) {
        int left = 0;
        int right = catalog.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            String isbn = catalog[mid][0];
            String title = catalog[mid][1];

            int comparison = isbn.compareTo(targetIsbn);

            if (comparison == 0) {
                return title;
            } else if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return "Not Found";
    }

    public static void main(String[] args) {
        String[][] catalog = {
            {"0001112223", "Introduction to Algebra"},
            {"0002223334", "Beginning Python"},
            {"0003334445", "Classic Mythology"},
            {"0004445556", "Data and Society"},
            {"0005556667", "European History"}
        };

        System.out.println(findBook(catalog, "0003334445")); // Output: Classic Mythology
        System.out.println(findBook(catalog, "0009998887")); // Output: Not Found
    }
}