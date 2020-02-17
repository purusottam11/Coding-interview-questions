import com.sun.security.auth.UnixNumericGroupPrincipal;
import org.omg.CORBA.MARSHAL;
import sun.java2d.cmm.CMSManager;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.*;
import java.util.List;

public class LeetCode {
    public double findMedianSortedArrays(int[] A, int[] B) {
        int i = 0, j = 0, k = 0;
        int arr[] = new int[A.length + B.length];
        while (i < A.length && j < B.length) {
            if (A[i] < B[j])
                arr[k++] = A[i++];
            else {
                arr[k++] = B[j++];
            }
        }
        while (i < A.length) {
            arr[k++] = A[i++];
        }
        while (j < B.length) {
            arr[k++] = B[j++];
        }
        if (arr.length % 2 != 0) {
            return arr[arr.length / 2];
        } else {
            double res = (double) (arr[arr.length / 2] + arr[arr.length / 2 - 1]) / 2;
            return res;
        }
    }

    public int maxArea(int[] height) {
        int res = 0;
        if (height == null || height.length == 0) {
            return res;
        }
        int i = 0;
        int j = height.length - 1;
        while (i < j) {
            int temp = Math.min(height[i], height[j]) * (j - i);
            if (temp > res) {
                res = temp;
            }
            if (height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
        }
        return res;
    }

    public int trap(int[] height) {
        int maxArrayLeft[] = new int[height.length];
        int maxArrayRight[] = new int[height.length];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < height.length; i++) {
            if (height[i] > max) {
                max = height[i];
            }
            maxArrayLeft[i] = max;
        }

        max = Integer.MIN_VALUE;
        for (int i = height.length - 1; i >= 0; i--) {
            if (height[i] > max) {
                max = height[i];
            }
            maxArrayRight[i] = max;
        }
        int sum = 0;
        for (int i = 0; i < height.length; i++) {
            int temp = Math.min(maxArrayLeft[i], maxArrayRight[i]) - height[i];
            if (temp > 0) {
                sum += temp;
            }
        }
        return sum;
    }

    public int maxSubArray(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if ((nums[i] + nums[i - 1]) > nums[i]) {
                nums[i] = nums[i] + nums[i - 1];
            }
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
        }
        return max;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> set = new HashSet<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            int j = i + 1;
            int k = nums.length - 1;
            int sum = 0 - nums[i];
            while (j < k) {
                if ((nums[j] + nums[k]) == sum) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(nums[k]);
                    set.add(list);
                    j++;
                } else if ((nums[j] + nums[k]) > sum) {
                    k--;
                } else {
                    j++;
                }
            }
        }
        return new ArrayList<>(set);
    }

    public int[] productExceptSelf(int[] nums) {
        int arr[] = new int[nums.length];
        int brr[] = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            arr[i] = nums[i];
            brr[i] = nums[i];
        }
        for (int i = 1; i < nums.length; i++) {
            arr[i] = arr[i] * arr[i - 1];
        }
        for (int i = nums.length - 2; i >= 0; i--) {
            brr[i] = brr[i] * brr[i + 1];
        }
        nums[0] = brr[1];
        nums[nums.length - 1] = arr[nums.length - 2];
        for (int i = 1; i < nums.length - 1; i++) {
            nums[i] = arr[i - 1] * brr[i + 1];
        }
        return nums;
    }

    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length; i += 2) {
            sum += nums[i];
        }
        return sum;
    }

    public int maxProfit(int[] prices) {
        int min = Integer.MAX_VALUE;
        int profit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < min) {
                min = prices[i];
            } else {
                profit = Math.max(profit, prices[i] - min);
            }
        }
        return profit;
    }


    public int majorityElement(int[] nums) {
        int count = 0;
        int candidate = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (count == 0) {
                candidate = nums[i];
                count++;
            } else {
                if (nums[i] == candidate) {
                    count++;
                } else {
                    count--;
                }
            }
        }
        count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == candidate) {
                count++;
            }
        }
        if (count > nums.length / 2) {
            return candidate;
        }
        return -1;
    }

    public int maxProfit2(int[] prices) {
        int profit = 0;
        if (prices == null || prices.length == 0) {
            return profit;
        }
        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i + 1] > prices[i]) {
                profit += (prices[i + 1] - prices[i]);
            }
        }
        return profit;
    }

    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] == 9) {
                digits[i] = 0;
            } else {
                digits[i]++;
                return digits;
            }
        }

        int arr[] = new int[digits.length + 1];
        arr[0] = 1;
        return arr;
    }

    public void moveZeroes(int[] nums) {
        int c = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[c++] = nums[i];
            }
        }
        for (int i = c; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    public int maximalRectangle(char[][] matrix) {

        if (matrix.length == 0) return 0;
        int maxarea = 0;
        int[][] dp = new int[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') {

                    // compute the maximum width and update dp with it
                    dp[i][j] = j == 0 ? 1 : dp[i][j - 1] + 1;

                    int width = dp[i][j];

                    // compute the maximum area rectangle with a lower right corner at [i, j]
                    for (int k = i; k >= 0; k--) {
                        width = Math.min(width, dp[k][j]);
                        maxarea = Math.max(maxarea, width * (i - k + 1));
                    }
                }
            }
        }
        return maxarea;
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int c = n + m - 1;
        int i = m - 1;
        int j = n - 1;

        while (i == 0 && j == 0) {
            if (nums1[i] > nums2[j]) {
                nums1[c--] = nums1[i];
                i--;
            } else {
                nums1[c--] = nums1[j];
                j--;
            }
        }
        for (int k = c; k >= 0; k--) {
            nums1[c--] = nums1[k];
        }

        for (int k = c; k >= 0; k--) {
            nums1[c--] = nums2[k];
        }
    }

    public int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) {
            return intervals;
        }
        List<int[]> list = new ArrayList<>();
        Arrays.sort(intervals, (arr1, arr2) -> Integer.compare(arr1[0], arr2[0]));
        int currentInterval[] = intervals[0];
        list.add(currentInterval);
        for (int arr[] : intervals) {
            int currentIntervaBegin = currentInterval[0];
            int currentIntervalEnds = currentInterval[1];
            int newIntervalBegin = arr[0];
            int newIntervalEnd = arr[1];

            if (newIntervalBegin <= currentIntervalEnds) {
                currentInterval[1] = Math.max(currentIntervalEnds, newIntervalEnd);
            } else {
                list.add(currentInterval);
                currentInterval = arr;
            }
        }
        return list.toArray(new int[list.size()][]);
    }


    public int threeSumClosest(int[] nums, int target) {
        int minValue = Integer.MAX_VALUE, res = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            int j = i + 1, k = nums.length - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (Math.abs(sum - target) < minValue) {
                    minValue = Math.abs(sum - target);
                    res = sum;
                }
                if (sum > target) {
                    k--;
                } else {
                    j++;
                }
            }
        }
        return res;
    }

    public static void rotate(int[][] matrix) {
        int arr[][] = new int[matrix.length][matrix[0].length];
        for (int j = 0; j < matrix[0].length; j++) {
            int c = 0;
            for (int i = matrix.length - 1; i >= 0; i--) {
                arr[j][c++] = matrix[i][j];
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = arr[i][j];
            }
        }
    }

    public void nextPermutation(int[] nums) {
        boolean flag = false;
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i - 1] < nums[i]) {
                Arrays.sort(nums, i, nums.length);
                for (int j = i; j < nums.length; j++) {
                    if (nums[j] > nums[i - 1]) {
                        int temp = nums[j];
                        nums[j] = nums[i - 1];
                        nums[i - 1] = temp;
                        flag = true;
                        break;
                    }
                }
                break;
            }
        }
        if (flag == false) {
            Arrays.sort(nums);
        }
    }

    public int firstMissingPositive(int[] nums) {
        boolean arr[] = new boolean[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0 && nums[i] < arr.length) {
                arr[nums[i]] = true;
            }
        }
        for (int i = 1; i < arr.length; i++) {
            if (!arr[i]) {
                return i;
            }
        }
        return arr.length;
    }

    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        Arrays.sort(nums);
        int max = 1;
        int c = 1;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] != nums[i + 1]) {
                if (nums[i + 1] == (nums[i] + 1)) {
                    c++;
                    max = Math.max(max, c);
                } else {
                    c = 1;
                }
            }
        }
        return max;
    }

    public int removeDuplicates(int[] nums) {
        int c = 1;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] != nums[i + 1]) {
                nums[c++] = nums[i + 1];
            }
        }
        return c;
    }

    public static void main(String[] args) {
        int arr[] = {2, 3, 1};

    }

}
