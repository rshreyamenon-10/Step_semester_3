import java.util.HashMap;
import java.util.Map;

public class TransactionAnalyzer {

    public static int countPeriods(int[] transactions, int k) {
        int count = 0;
        int currentSum = 0;
        Map<Integer, Integer> prefixSumCounts = new HashMap<>();
        
        prefixSumCounts.put(0, 1);

        for (int num : transactions) {
            currentSum += num;
            
            int target = currentSum - k;
            if (prefixSumCounts.containsKey(target)) {
                count += prefixSumCounts.get(target);
            }
            
            prefixSumCounts.put(currentSum, prefixSumCounts.getOrDefault(currentSum, 0) + 1);
        }

        return count;
    }

    public static void main(String[] args) {
        int[] transactions = {3, 4, -7, 1, 3, 3, 1, -4};
        int k = 7;

        int result = countPeriods(transactions, k);
        System.out.println(result);
    }
}
