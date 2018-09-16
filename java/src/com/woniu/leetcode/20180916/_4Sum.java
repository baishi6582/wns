package leetcode.date_20180916;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
	题目描述：四数之和
	给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。

	注意：
	
	答案中不可以包含重复的四元组。
	
	示例：
	
	给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
	
	满足要求的四元组集合为：
	[
	  [-1,  0, 0, 1],
	  [-2, -1, 1, 2],
	  [-2,  0, 0, 2]
	]
	
	解题思路：首先，对数组进行排序，
	然后，确定a和b, 再接着c从左到右，d从右到左，依次寻找。
	
	题目链接：https://leetcode-cn.com/problems/4sum/description/
 * @author woniu
 *
 */
public class _4Sum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
        int a = 0, b = 0;
        int len = nums.length;
        int start = 0, end = len - 1;
        for(int i=0; i<len; i++) {
        	a = nums[i];
        	for(int j=i+1; j<len-1; j++) {
        		b = nums[j];
        		start = j+1;
        		end = len - 1;
        		while(start < end) {
        			//小于目标，则小值增大
        			while(start < end && a + b + nums[start] + nums[end] < target) {
            			start ++;
            		}
            		//大于目标，则大值变小
            		while(start < end && a + b + nums[start] + nums[end] > target) {
            			end --;
            		}
            		//等于目标
            		if(start < end && a + b + nums[start] + nums[end] == target) {
            			List<Integer> list = new ArrayList<Integer>();
            			list.add(a);
            			list.add(b);
            			list.add(nums[start]);
            			list.add(nums[end]);
            			if(!result.contains(list)) {//去重
            				result.add(list);
            			}
            			int c = nums[start];
            			while(++start < end && nums[start] == c);//对c去重
            			
            			int d = nums[end];
            			while(start < --end && nums[end] == d);//对d去重
            		} 
        		}
        		
        	}
        }
        
        return result;
    }
    
    public static void main(String[] args) {
    	int[] nums = {1, 0, -1, 0, -2, 2};
    	int target = 0;
		System.out.println(new _4Sum().fourSum(nums, target));
		
		
		System.out.println(new _4Sum().fourSum(new int[] {0, 0, 0, 0}, 0));
		
		System.out.println(new _4Sum().fourSum(new int[] {-3,-2,-1,0,0,1,2,3}, 0));
	
		System.out.println(new _4Sum().fourSum(new int[] {7,7,-1,-5,2,-2,8,-8,-10,0,1,-4,-1,4,-6,5,4}, -21));
    }
}
