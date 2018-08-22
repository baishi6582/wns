package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
/**
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * 满足要求的三元组集合为：
 * [
 *	 [-1, 0, 1],
 *	 [-1, -1, 2]
 * ]
 * @author woniu
 *
 */
class ThreeNumAdd {
    public List<List<Integer>> threeSum(int[] nums) {
      
		List<List<Integer>> resultList = new ArrayList<List<Integer>>();
        /**
         * 解决思路：
         * 1、通常的解决办法是通过三层循环来解决此问题，这个方法确实能解决，但是执行效率不高。
         * 2、其后，想到了使用两层循环进行a和b的查找，c通过二分查找的方式来确定，虽然有了明显的效率提升，但是，仍然会超时
         * 3、为当下的解决思路，先派逊，然后通过一层循环确定a的值，然后之后通过从最大和最小值的方式来确定b和c的值，效率得到了进一步的提升。
         */
    	Arrays.sort(nums);
    	int i = 0 , last = nums.length-1;
    	while(i < last) {
    		int a = nums[i], j = i+1, k = last;
    		while(j<k) {
    			int b = nums[j], c = nums[k];
    			int sum = a + b + c;
    			if(sum == 0) {
    				List<Integer> result = new ArrayList<Integer>();
    				result.add(a);
    				result.add(b);
    				result.add(c);
    				Collections.sort(result);//排序，防止重复
    				if(!resultList.contains(result)) {
    					resultList.add(result);
    				}
    				while(nums[k] == c && j<k) k--;
    			}
    			if(sum < 0) {
    				while(nums[j] == b && j<k) j++;
    			}
    			if(sum > 0) {
    				while(nums[k] == c && j<k) k--;
    			}
    			
    		}
    		while(nums[i] == a && i<last) i++;
    	}
        return resultList;
    }

}
