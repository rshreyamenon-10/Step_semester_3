public class ProductPriceFinder {

    public static void countProductsInRange(int[] prices, int low, int high) {
        int lowerBoundIndex = findLowerBound(prices, low);
        int upperBoundIndex = findUpperBound(prices, high);
        int count = upperBoundIndex - lowerBoundIndex;

        System.out.println("Lower bound index " + lowerBoundIndex);
        System.out.println("Upper bound index " + upperBoundIndex);
        System.out.println("Count " + count);
    }

    private static int findLowerBound(int[] prices, int target) {
        int left = 0;
        int right = prices.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (prices[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private static int findUpperBound(int[] prices, int target) {
        int left = 0;
        int right = prices.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (prices[mid] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        int[] prices = {100, 150, 150, 200, 300, 450};
        int low = 150;
        int high = 300;
        countProductsInRange(prices, low, high);
    }
}
