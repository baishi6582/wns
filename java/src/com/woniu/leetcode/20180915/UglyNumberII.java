package leetcode.date_20180915;

import java.util.HashSet;

/**
 * 
	题目描述；丑数 II
	编写一个程序，找出第 n 个丑数。

	丑数就是只包含质因数 2, 3, 5 的正整数。
	
	示例:
	
	输入: n = 10
	输出: 12
	解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
	说明:  
	
	1 是丑数。
	n 不超过1690。
	
	解题思路：
	一，首先肯定是想到了用暴力破解的方式，每个数都寻找一次，然后，发现超时，之后又优化了通过一个set进行过滤，但是仍然超时。
	二，无奈，自己没有发现更优的办法，然后，从网上找到了相关的解题思路，链接已负载相关解法上，解题思路很简单，就是每次取以2、3、5相关积的最小值。
	然后，依次计算，这样避免了过多的无效计算。
	
	题目链接:https://leetcode-cn.com/problems/ugly-number-ii/description/
 * @author woniu
 *
 */
public class UglyNumberII {
	
	//参考链接：https://www.jianshu.com/p/a7242aa11dff
	 public int nthUglyNumber(int n) {
		 	int[] nums = new int[n];
		 	nums[0] = 1;
		 	int p2 = 0, p3 = 0, p5 = 0;
		 	int count = 1;
		 	while(count < n) {
		 		nums[count]	= Math.min(Math.min(nums[p2] * 2, nums[p3] * 3), nums[p5] * 5);
		 		
		 		if(nums[p2] * 2 == nums[count]) {
		 			p2 ++;
		 		}
		 		if(nums[p3] * 3 == nums[count]) {
		 			p3 ++;
		 		}
		 		if(nums[p5] * 5 == nums[count]) {
		 			p5 ++;
		 		}
		 		count ++;
		 	}
		 	
	    	return nums[count - 1];
	    }
	
    public int nthUglyNumber1(int n) {
        
    	int count = 1;
    	
    	HashSet<Integer> set = new HashSet<Integer>();
    	set.add(1);
    	int num= 2;
    	for(; n != count; num++) {
    		
    		if(num % 2 == 0 && set.contains(num / 2)) {
    			count ++;
    			set.add(num);
    			continue;
    		}
    		
    		if(num % 3 == 0 && set.contains(num / 3)) {
    			count ++;
    			set.add(num);
    			continue;
    		}
    		
    		if(num % 5 == 0 && set.contains(num / 5)) {
    			count ++;
    			set.add(num);
    			continue;
    		}
    	}
    	return num - 1;
    }
    
    public static void main(String[] args) {
		System.out.println(new UglyNumberII().nthUglyNumber(1690));
	}
}
