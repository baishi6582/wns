package leetcode.date_20180909;
	
/**
 * 
	题目描述：最大子序和
	给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

	示例:
	
	输入: [-2,1,-3,4,-1,2,1,-5,4],
	输出: 6
	解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
	
	解题思路：动态规划的题目，自然运用了动态规划来解这道题。
	首先初始dp，之后，如果当前数字大于连续的结果，则存储当前结果，如果连续子序列更大，则存储连续结果。
	
	题目链接：https://leetcode-cn.com/problems/maximum-subarray/description/
 * @author z00364813
 *
 */
public class MaximumSubarray {
    public int maxSubArray(int[] nums) {
     
    	int len = nums.length;
    	int[] dp = new int[len];
    	dp[0] = nums[0];
    	for(int i=1; i<len; i++) {
    		dp[i] = nums[i] > dp[i-1] + nums[i] ? nums[i] : dp[i-1] + nums[i];
    	}
    	
    	int max = Integer.MIN_VALUE;
    	for(int n : dp) {
    		max = Math.max(max, n);
    	}
    	
    	return max;
    }
    
    public static void main(String[] args) {
    	int[] nums = {-1}; //{-2,1,-3,4,-1,2,1,-5,4};
		System.out.println(new MaximumSubarray().maxSubArray(nums));
	}
}
