package leetcode.date_20180920;
/**
 * 
	题目描述：子数组的最小值之和
	给定一个整数数组 A，找到 min(B) 的总和，其中 B 的范围为 A 的每个（连续）子数组。

	由于答案可能很大，因此返回答案模 10^9 + 7。
	
	 
	
	示例：
	
	输入：[3,1,2,4]
	输出：17
	解释：
	子数组为 [3]，[1]，[2]，[4]，[3,1]，[1,2]，[2,4]，[3,1,2]，[1,2,4]，[3,1,2,4]。 
	最小值为 3，1，2，4，1，1，2，1，1，1，和为 17。
	
	解题思路：见下文
	
	题目链接：https://leetcode-cn.com/problems/sum-of-subarray-minimums/description/
	
 * @author woniu
 *
 */
public class SumOfSubarrayMinimums {
	
	/**
	 * 这个方法我其实并没有理解最终的公式是怎么来的。
	 * 主要的方式是求出左边大于nums[i]的位置，以及右边大于nums[i]的位置，最终nums[i]需要在结果中出现的次数为
	 * (i-left) * (right-i) * nums[i]
	 * @param nums
	 * @return
	 */
	 public int sumSubarrayMins(int[] nums) {
	        
	    	long result = 0;
	    	long mod = 1000000007;
	    	for(int i=0; i<nums.length; i++) {
	    		int left = i - 1;
	    		for(; left>=0 && nums[i] < nums[left]; left--);
	    		int right = i + 1;
	    		for(;right < nums.length && nums[i] <= nums[right]; right++);
	    		result += (i - left) * (right - i) * nums[i];
	    	}
	    	System.out.println(result);
	    	return (int) (result % mod);
	    }
	
	//超时
    public int sumSubarrayMins1(int[] nums) {
        
    	long result = 0;
    	long mod = 1000000007;
    	for(int i=0; i<nums.length; i++) {
    		result += nums[i];
    		int min = nums[i];
    		for(int j=i-1; j>=0; j--) {
    			if(nums[j] < min) {
    				min = nums[j];
    			}
    			result += min;
    		}
    	}
    	System.out.println(result);
    	return (int) (result % mod);
    }
    
    public static void main(String[] args) {
		System.out.println(new SumOfSubarrayMinimums().sumSubarrayMins(new int[] {85}));
	}
}
