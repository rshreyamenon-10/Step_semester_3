import java.util.Arrays;

public class PairSumFinder {

    public static Object[] pairSumSorted(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int currentSum = nums[left] + nums[right];

            if (currentSum == target) {
                return new Integer[] { nums[left], nums[right] };
            } else if (currentSum < target) {
                left++;              } else {
                right--;             }
        }

        return new String[] { "Not Found" };
    }

    public static void main(String[] args) {
        int[] nums1 = { -4, -1, 0, 3, 5, 9 };
        int target1 = 4;
        System.out.println(Arrays.toString((Object[]) pairSumSorted(nums1, target1))); 
    
        int[] nums2 = { 1, 2, 3 };
        int target2 = 100;
        System.out.println(Arrays.toString((Object[]) pairSumSorted(nums2, target2))); 
    }
}