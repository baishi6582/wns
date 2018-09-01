package leetcode;

/**
 * 
 * 
	问题描述：x 的平方根	
	实现 int sqrt(int x) 函数。

	计算并返回 x 的平方根，其中 x 是非负整数。
	
	由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
	
	示例 1:
	
	输入: 4
	输出: 2
	示例 2:
	
	输入: 8
	输出: 2
	说明: 8 的平方根是 2.82842..., 
	     由于返回类型是整数，小数部分将被舍去。
	     
	解题思路：是从1开头开始，一直比对，直到某个数的平方距离X最近为止。	
	
	题目链接：https://leetcode-cn.com/problems/sqrtx/description/
 * @author z00364813
 *
 */
public class Sqrt {
	public int mySqrt(int x) {
        
		if(x == 1) {
			return 1;
		}
		
		for(int i=1; i<=x; i++) {
			if(((long)i * i) > x) {
				return i - 1;
			}
			
		}
		
		return 0;
    }
	
	public static void main(String[] args) {
		System.out.println(new Sqrt().mySqrt(3));
	}
}
