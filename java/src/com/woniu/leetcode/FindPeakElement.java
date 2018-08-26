package leetcode;

/**
 * 
	题目描述：寻找峰值
	峰值元素是指其值大于左右相邻值的元素。
	
	给定一个输入数组 nums，其中 nums[i] ≠ nums[i+1]，找到峰值元素并返回其索引。
	
	数组可能包含多个峰值，在这种情况下，返回任何一个峰值所在位置即可。
	
	你可以假设 nums[-1] = nums[n] = -∞。
	
	示例 1:
	
	输入: nums = [1,2,3,1]
	输出: 2
	解释: 3 是峰值元素，你的函数应该返回其索引 2。
	示例 2:
	
	输入: nums = [1,2,1,3,5,6,4]
	输出: 1 或 5 
	解释: 你的函数可以返回索引 1，其峰值元素为 2；
	     或者返回索引 5， 其峰值元素为 6。
	说明:
	
	你的解法应该是 O(logN) 时间复杂度的。
	
	解题思路：依次遍历整个数组，找到满足条件的即可，题目难度不大。需注意的地方是，数组两端只需要判断大于一端即可。
	如；[2,3]，3是满足条件的位置，需要返回1；
	
	题目链接：https://leetcode-cn.com/problems/find-peak-element/description/
 * @author z00364813
 *
 */
public class FindPeakElement {
	
	public int findPeakElement(int[] nums) {
		if (nums == null || nums.length == 1) {
			return 0;
		}
		int end = nums.length - 1;
		
		if(nums[0] > nums[1]) {
			return 0;
		}
		
		if(nums[end] > nums[end - 1]) {
			return end;
		}
		
		int start = 1;
		end = end - 1;
		
		while (start <= end) {
			if (nums[start - 1] < nums[start] && nums[start] > nums[start + 1]) {
				return start;
			}
			
			if (nums[end - 1] < nums[end] && nums[end] > nums[end - 1]) {
				return end;
			}
			
			start++;
			end--;
		}
		return 0;
	}
	
	public static void main(String[] args) {
		int[] nums = {1,2,3,1};
		System.out.println(new FindPeakElement().findPeakElement(nums));
	}
}
