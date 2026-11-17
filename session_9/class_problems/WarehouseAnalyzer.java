import java.util.HashMap;
import java.util.Map;

public class WarehouseAnalyzer {

    public static Map<String, Object> warehouseSummary(int[][] grid) {
        Map<String, Object> result = new HashMap<>();
        
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            result.put("total", 0);
            result.put("maxCoordinate", new int[] {-1, -1});
            return result;
        }

        int totalItems = 0;
        int maxCount = -1;
        int maxRow = 0;
        int maxCol = 0;

        int rows = grid.length;
        int cols = grid[0].length;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
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
        result.put("maxCoordinate", new int[] {maxRow, maxCol});
        return result;
    }

    public static void main(String[] args) {
        int[][] grid = {
            {4, 9, 2},
            {7, 1, 6},
            {3, 12, 5}
        };

        Map<String, Object> summary = warehouseSummary(grid);
        int[] coord = (int[]) summary.get("maxCoordinate");
        
        System.out.println("total = " + summary.get("total") + ", maxCoordinate = (" + coord[0] + ", " + coord[1] + ")");
        // Output: total = 49, maxCoordinate = (2, 1)
    }
}