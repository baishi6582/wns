package leetcode.date_20180923;

/**
 * 
	题目描述： 搜索旋转排序数组 II
	假设按照升序排序的数组在预先未知的某个点上进行了旋转。

	( 例如，数组 [0,0,1,2,2,5,6] 可能变为 [2,5,6,0,0,1,2] )。
	
	编写一个函数来判断给定的目标值是否存在于数组中。若存在返回 true，否则返回 false。
	
	示例 1:
	
	输入: nums = [2,5,6,0,0,1,2], target = 0
	输出: true
	示例 2:
	
	输入: nums = [2,5,6,0,0,1,2], target = 3
	输出: false
	
	解题思路：见下文
	
	题目链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array-ii/description/
 * @author woniu
 *
 */
public class SearchInRotatedSortedArrayII {
	/**
	 * 
	 * 该方法的是充分利用了题目中的条件，升序排序的数组在预先未知的某个点上进行了旋转
	 * 其中某个点，也就是说，至少是有一半的数组是有序的，然后充分利用有序这边的数组进行判断。
	 * @param nums
	 * @param target
	 * @return
	 */
	 public boolean search(int[] nums, int target) {
		 if(nums == null || nums.length == 0) {
			 return false;
		 }
		 
	    int start = 0, end = nums.length - 1;
	    while(start < end) {
	    	int mid = start + (end - start) / 2;
	    	if(nums[mid] == target) {
	    		return true;
	    	}
	    	
	    	if(nums[mid] < nums[end]) {//此题以判断后半数组是否有序来推断前边的数组的顺序，当nums[mid]<nums[end]时，表明，右边是有序的
	    		if(nums[mid] <target && nums[end] >= target) {//这里判断target是否在有半节数组中
	    			start = mid + 1;
	    		} else {//不在后边，则去前边找
	    			end = mid - 1;
	    		}
	    		
	    	} else if(nums[mid] > nums[end]) {//如果nums[mid]>nums[end]，则表明，后半数组无序
	    		if(nums[mid] > target && nums[start] <= target) {//那就是前边是有序的，则判断是否有可能出现在前边
	    			end = mid - 1;
	    		} else {//不在前边，则去后边寻找
	    			start = mid + 1;
	    		}
	    	} else {//判断不出来，则最后尾数向前移一位，因为它不可能是最终的结果了nums[mid] != target 但是 nums[mid] == nums[end]
	    		end --;
	    	}
	    }
    	return nums[start] == target ? true : false;
    }
	
	 
	 //方法1，通过二分迭代计算
    public boolean search1(int[] nums, int target) {
        
    	return search(nums, 0, nums.length - 1, target);
    }
    
    public boolean search(int[] nums, int start, int end, int target) {
        
    	if(start > end) {
    		return false;
    	}
    	
    	int mid = start + (end - start) / 2;
    	
    	if(nums[mid] == target) {
    		return true;
    	}
    	
    	if((nums[mid] > target)) {
    		if((nums[start] > target && nums[start] <= nums[mid])) {
    			return search(nums, mid + 1, end, target);
    		} 
    		return search(nums, start, mid - 1, target);
    		
    	} else {
    		if(nums[end] == nums[mid] && nums[start] == nums[mid]) {//应对特例[1,3,1,1,1], target为3，此时，无法判断具体向哪个方向移动。因为都是1
    			for(int i=start; i<=end; i++) {
    				if(nums[i] == target) {
    					return true;
    				}
    			}
    			return false;
    		}
    		if(nums[end] < target && nums[start] > nums[mid]) {
    			return search(nums, start, mid - 1, target);
    		}
    		return search(nums, mid + 1, end, target);
    	}
    	
    }
    
    
    
    
    public static void main(String[] args) {
		System.out.println(new SearchInRotatedSortedArrayII().search(new int[] {3, 1}, 1));
	}
}
