import java.util.List;
import java.util.ArrayList;

public class MallAnalyzer {

    public static List<Integer> footfallReport(int[] visitors, int[][] queries) {
        int n = visitors.length;
        int[] prefixSum = new int[n + 1];
        
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + visitors[i];
        }

        List<Integer> result = new ArrayList<>();
        for (int[] query : queries) {
            int start = query[0];
            int end = query[1];
            int total = prefixSum[end + 1] - prefixSum[start];
            result.add(total);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] visitors = {12, 7, 3, 9, 15, 4, 8};
        int[][] queries = {
            {0, 2},
            {2, 5},
            {4, 6},
            {3, 3}
        };

        List<Integer> output = footfallReport(visitors, queries);
        System.out.println(output);
    }
}