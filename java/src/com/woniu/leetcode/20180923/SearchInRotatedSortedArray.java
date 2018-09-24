package leetcode.date_20180923;
/**
 * 
 * 
	题目描述：搜索旋转排序数组
	假设按照升序排序的数组在预先未知的某个点上进行了旋转。

	( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
	
	搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
	
	你可以假设数组中不存在重复的元素。
	
	你的算法时间复杂度必须是 O(log n) 级别。
	
	示例 1:
	
	输入: nums = [4,5,6,7,0,1,2], target = 0
	输出: 4
	示例 2:
		
	输入: nums = [4,5,6,7,0,1,2], target = 3
	输出: -1
	
	解题思路：题目要求了时间复杂度 O(log n)，所以此题必然不能通过依次遍历来查找了。那样时间复杂度为n.
	通过二分查找。发现，并不是有序的，此时，断然不能排序了。否则时间复杂度又超了。
	只能通过条件限制了。因为题目中说明了某个点的旋转，所以，大部分是有序的。只要找到对应点即可，见下文if语句
	
	题目链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array/description/
 * @author woniu
 *
 */
public class SearchInRotatedSortedArray {
    public int search(int[] nums, int target) {
    	
    	return search(nums, 0, nums.length - 1, target);
    }
    
    public int search(int[] nums, int start, int end, int target) {
        
    	if(start > end) {
    		return -1;
    	}
    	
    	int mid = (start + end) / 2;
    	if(nums[mid] == target) {
    		return mid;
    	}
    	//这里判断了三个条件，分别是
    	//1.左端正常的顺序，nums[start] <= target && nums[mid] > target
    	//2.左端存在跳变，如5,1,3， target=5, 此时需要保证nums[mid] < target && nums[end] < target && nums[mid] < nums[end]，这个条件保证右端有序，但是并不存在需要寻找的值
    	//3.如5,1,2,3,4， target为1， nums[start] > target && nums[mid] > target && nums[start] > nums[mid]
    	if((nums[start] <= target && nums[mid] > target) || (nums[start] > target && nums[mid] > target && nums[start] > nums[mid])  || (nums[mid] < target && nums[end] < target && nums[mid] < nums[end])) {
    		return search(nums, start, mid - 1, target);
    	} else {
    		return search(nums, mid + 1, end, target);
    	}
    	
    }
    
    public static void main(String[] args) {
		System.out.println(new SearchInRotatedSortedArray().search(new int[] {5, 1, 3}, 1));
		
		System.out.println(new SearchInRotatedSortedArray().search(new int[] {4,5,6,7,8,1,2,3}, 13));
		
		System.out.println(new SearchInRotatedSortedArray().search(new int[] {5,1,2,3,4}, 1));
	}
}
