import java.util.HashMap;
import java.util.Map;

public class DataStructuresSolutions {

    public static int[] pairSumSorted(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int currentSum = nums[left] + nums[right];
            if (currentSum == target) {
                return new int[] { nums[left], nums[right] };
            } else if (currentSum < target) {
                left++;
            } else {
                right--;
            }
        }
        return null;
    }

    public static Map<String, Object> warehouseSummary(int[][] grid) {
        Map<String, Object> result = new HashMap<>();
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            result.put("total", 0);
            result.put("maxCoordinate", new int[] { -1, -1 });
            return result;
        }

        int totalItems = 0;
        int maxCount = -1;
        int maxRow = 0;
        int maxCol = 0;

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                int val = grid[r][c];
                totalItems += val;
                if (val > maxCount) {
                    maxCount = val;
                    maxRow = r;
                    maxCol = c;
                }
            }
        }

        result.put("total", totalItems);
        result.put("maxCoordinate", new int[] { maxRow, maxCol });
        return result;
    }

    public static String findBook(String[][] catalog, String targetIsbn) {
        int left = 0;
        int right = catalog.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparison = catalog[mid][0].compareTo(targetIsbn);

            if (comparison == 0) {
                return catalog[mid][1];
            } else if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return "Not Found";
    }

    public static int maxSumSubarray(int[] sales, int k) {
        if (sales == null || sales.length == 0 || k <= 0 || k > sales.length) {
            return 0;
        }

        int windowSum = 0;
        for (int i = 0; i < k; i++) {
            windowSum += sales[i];
        }

        int maxSum = windowSum;
        for (int i = k; i < sales.length; i++) {
            windowSum += sales[i] - sales[i - k];
            if (windowSum > maxSum) {
                maxSum = windowSum;
            }
        }

        return maxSum;
    }

    public static void main(String[] args) {
        int[] nums = { -4, -1, 0, 3, 5, 9 };
        int[] pairResult = pairSumSorted(nums, 4);
        System.out.println("Pair Sum: " + (pairResult != null ? "[" + pairResult[0] + ", " + pairResult[1] + "]" : "Not Found"));

        int[][] grid = {
            {4, 9, 2},
            {7, 1, 6},
            {3, 12, 5}
        };
        Map<String, Object> warehouseRes = warehouseSummary(grid);
        int[] coord = (int[]) warehouseRes.get("maxCoordinate");
        System.out.println("Warehouse Total: " + warehouseRes.get("total") + ", Max Coordinate: (" + coord[0] + ", " + coord[1] + ")");

        String[][] catalog = {
            {"0001112223", "Introduction to Algebra"},
            {"0002223334", "Beginning Python"},
            {"0003334445", "Classic Mythology"},
            {"0004445556", "Data and Society"},
            {"0005556667", "European History"}
        };
        System.out.println("Book Title: " + findBook(catalog, "0003334445"));

        int[] sales = { 2, 1, 5, 1, 3, 2 };
        System.out.println("Max Sum Subarray: " + maxSumSubarray(sales, 3));
    }
}