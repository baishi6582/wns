package leetcode.date_20180905;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 	题目描述：两数之和
 	
	给定一个整数数组和一个目标值，找出数组中和为目标值的两个数。
	
	你可以假设每个输入只对应一种答案，且同样的元素不能被重复利用。
	
	示例:
	
	给定 nums = [2, 7, 11, 15], target = 9
	
	因为 nums[0] + nums[1] = 2 + 7 = 9
	所以返回 [0, 1]
	
	解题思路：方法不是太好，首先，排序，然后从两头查找，查找到对应数字后，再从原数组中找到对应位置
	其中，为了防止存在相同元素，如[3, 3] ,target 为6这种场景，都找到下标相同，则设置了一个result[0]是否已访问的标识isFirst。
	总体来说过于复杂了。
	
	题目链接：https://leetcode-cn.com/problems/two-sum/description/
 * @author z00364813
 *
 */
public class TwoSum {
    public int[] twoSum2(int[] nums, int target) {
    	int[] temp = Arrays.copyOf(nums, nums.length);
        int[] result = new int[2];
    	Arrays.sort(nums);
    	int start = 0;
    	int end = nums.length -1;
    	boolean isFirst = true;
    	while(start < end) {
    		if(nums[start] + nums[end] == target) {
    			for(int i=0; i<temp.length; i++) {
    				if(temp[i] == nums[start] && isFirst) {
    					result[0] = i;
    					isFirst = false;
    					continue;
    				}
    				if(temp[i] == nums[end]) {
    					result[1] = i;
    				}
    			}
    			break;
    		}
    		
    		while(nums[start] + nums[end] > target && start < end) {
    			end --;
    		}
    		while(nums[start] + nums[end] < target && start < end) {
    			start ++;
    		}
    	}
    	return result;
    }
    
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
    	Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    	for(int i=0; i<nums.length; i++) {
    		if(!map.containsKey(target - nums[i])) {
    			map.put(nums[i], i);
    		} else {
    			result[0] = map.get(target - nums[i]);
    			result[1] = i;
    		}
    	}
    	return result;
    }
    
    public static void main(String[] args) {
		int[] nums = {2,3,3};//{3,2,4};
		int[] result = new TwoSum().twoSum(nums, 6);
		System.out.println(result[0]  + " " + result[1]);
	}
}
