package leetcode;

/**
 * 
	题目描述： 最长上升子序列
	给定一个无序的整数数组，找到其中最长上升子序列的长度。

	示例:
	
	输入: [10,9,2,5,3,7,101,18]
	输出: 4 
	解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
	
	解题思路：通过递归解决的这个问题，每个值，只需要在其前边比其小的值基础上加1即可，然后和当前的结果比较做大值。
	
	题目链接：https://leetcode-cn.com/problems/longest-increasing-subsequence/description/
 * @author woniu
 *
 */
public class LongestIncreasingSubsequence {
	public int lengthOfLIS(int[] nums) {
		int len = nums.length;
		int[] dp = new int[nums.length];
		for(int i=0; i<len; i++) {
			dp[i] = 1;
		}
		int maxLen = 0;
		for(int i=0; i<len; i++) {
			for(int j=i-1; j>=0; j--) {
				if(nums[j] < nums[i]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
			
			if(maxLen < dp[i]) {
				maxLen = dp[i];
			}
		}
		
		return maxLen;
	}
	public static void main(String[] args) {
		int[] nums = {10,9,2,5,3,7,101,18};
		System.out.println(new LongestIncreasingSubsequence().lengthOfLIS(nums));
	}
}
