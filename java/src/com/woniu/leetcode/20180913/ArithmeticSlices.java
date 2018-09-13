package leetcode.date_20180913;

/**
 * 
	题目描述：等差数列划分
	如果一个数列至少有三个元素，并且任意两个相邻元素之差相同，则称该数列为等差数列。

	例如，以下数列为等差数列:
	
	1, 3, 5, 7, 9
	7, 7, 7, 7
	3, -1, -5, -9
	以下数列不是等差数列。
	
	1, 1, 2, 5, 7
	 
	
	数组 A 包含 N 个数，且索引从0开始。数组 A 的一个子数组划分为数组 (P, Q)，P 与 Q 是整数且满足 0<=P<Q<N 。
	
	如果满足以下条件，则称子数组(P, Q)为等差数组：
	
	元素 A[P], A[p + 1], ..., A[Q - 1], A[Q] 是等差的。并且 P + 1 < Q 。
	
	函数要返回数组 A 中所有为等差数组的子数组个数。
	
	 
	
	示例:
	
	A = [1, 2, 3, 4]
	
	返回: 3, A 中有三个子等差数组: [1, 2, 3], [2, 3, 4] 以及自身 [1, 2, 3, 4]。
	
	解题思路：数学题，都是要基于找到的公式的或者说规律的，否则这个题就解不出来了。
	大概说一下思路：1,2,3,4 ，首先，1,2,3为一个等差数组，然后多了一个4，如果4-3的差与3-2的差相等，等于多了两个等差数列。
	如果不等，则重新开始计算。
	
	题目链接：https://leetcode-cn.com/problems/arithmetic-slices/description/
 * @author woniu
 *
 */
public class ArithmeticSlices {
    public int numberOfArithmeticSlices(int[] nums) {
        
    	int count = 0;
    	int n = 0;
    	int len = nums.length;
    	for(int i=2; i<len; i++) {
    		if((nums[i] - nums[i - 1]) == (nums[i - 1] - nums[i - 2])) {
    			n ++;
    			count += n;
    		} else {//新的一个序列开始了
    			n = 0;
    		}
    	}
    	
    	return count;
    }
    
    public static void main(String[] args) {
		int[] nums = {1, 2, 3, 4, 5, 6, 8, 10};
		System.out.println(new ArithmeticSlices().numberOfArithmeticSlices(nums));
	}
}
