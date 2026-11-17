import java.util.Arrays;

public class SnackBudgetAnalyzer {

    public static int[] longestStreak(int[] costs, int budget) {
        int n = costs.length;
        int left = 0;
        int currentSum = 0;
        int maxLength = 0;
        int bestStartIndex = -1;

        for (int right = 0; right < n; right++) {
            currentSum += costs[right];

            while (currentSum > budget && left <= right) {
                currentSum -= costs[left];
                left++;
            }

            int currentLength = right - left + 1;
            if (currentLength > maxLength) {
                maxLength = currentLength;
                bestStartIndex = left;
            }
        }

        if (maxLength == 0) {
            return new int[] {0, -1};
        }

        return new int[] {maxLength, bestStartIndex};
    }

    public static void main(String[] args) {
        int[] costs = {4, 2, 1, 7, 3, 1, 2, 1, 5};
        int budget = 8;

        int[] result = longestStreak(costs, budget);
        System.out.println(Arrays.toString(result));
    }
}