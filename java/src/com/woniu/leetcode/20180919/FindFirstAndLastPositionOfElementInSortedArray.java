package leetcode.date_20180919;

import java.util.Arrays;
/**
 *
	题目描述：在排序数组中查找元素的第一个和最后一个位置
	给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。

	你的算法时间复杂度必须是 O(log n) 级别。
	
	如果数组中不存在目标值，返回 [-1, -1]。
	
	示例 1:
	
	输入: nums = [5,7,7,8,8,10], target = 8
	输出: [3,4]
	示例 2:
	
	输入: nums = [5,7,7,8,8,10], target = 6
	输出: [-1,-1]
	
	解题思路：分治，递归遍历查找，相等时，从左右分别查找相等的数，否则去一端查找
	
	题目链接:https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/description/
 * @author woniu
 *
 */
public class FindFirstAndLastPositionOfElementInSortedArray {
    public int[] searchRange(int[] nums, int target) {
        
    	return searchRange(nums, 0, nums.length - 1, target);
    }
    
    public int[] searchRange(int[] nums, int start, int last, int target) {
        
    	if(last - start < 0) {
    		return new int[] {-1, -1};
    	}
    	int mid = start + (last - start + 1) / 2;
    	
    	if(nums[mid] == target) {
    		int i= mid - 1, j = mid + 1;
    		int begin = mid, end = mid;
			while(i>=start && nums[i] == target) {
				begin = i;
				i--;
			}
			while(j<=last && nums[j] == target) {
				end = j;
				j++;
			}
    		return new int[] {begin, end};
    	} else if(nums[mid] > target) {
    		return searchRange(nums, start, mid - 1, target);
    	} else {
    		return searchRange(nums, mid + 1, last, target);
    	}
    }
    
    public static void main(String[] args) {
		System.out.println(Arrays.toString(new FindFirstAndLastPositionOfElementInSortedArray().searchRange(new int[] {1, 2, 2}, 2)));;
	}
    
}
