import java.util.Stack;
import java.util.Arrays;

public class CanteenPriceStreak {

    public static int[] priceStreak(int[] price) {
        int[] streak = new int[price.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < price.length; i++) {
            while (!stack.isEmpty() && price[stack.peek()] <= price[i]) {
                stack.pop();
            }

            if (stack.isEmpty()) {
                streak[i] = i + 1;
            } else {
                streak[i] = i - stack.peek();
            }

            stack.push(i);
        }

        return streak;
    }

    public static void main(String[] args) {
        int[] prices1 = {100, 80, 60, 70, 60, 75, 85};
        System.out.println("Sample 1: " + Arrays.toString(priceStreak(prices1)));

        int[] prices2 = {10, 20, 30};
        System.out.println("Sample 2: " + Arrays.toString(priceStreak(prices2)));
    }
}
