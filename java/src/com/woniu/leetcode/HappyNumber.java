package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 
	题目描述：快乐数
	
	编写一个算法来判断一个数是不是“快乐数”。

	一个“快乐数”定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，然后重复这个过程直到这个数变为 1，也可能是无限循环但始终变不到 1。如果可以变为 1，那么这个数就是快乐数。
	
	示例: 
	
	输入: 19
	输出: true
	解释: 
	12 + 92 = 82
	82 + 22 = 68
	62 + 82 = 100
	12 + 02 + 02 = 1
	
	解题思路：取余相加
	
	题目链接：https://leetcode-cn.com/problems/happy-number/description/
 * @author woniu
 *
 */
public class HappyNumber {
	
	public boolean isHappy(int n) {
		Set<Integer> set = new HashSet<Integer>();
		set.add(n);
		int remainder = 0;
		int sum = 0;
		while(n != 0) {
			remainder = n % 10;
			n = n / 10;
			sum += remainder * remainder;
			if (n == 0 ) {
				if(sum == 1) {
					return true;
				}
				if(set.contains(sum)) {
					return false;
				}
				set.add(sum);
				n = sum;
				sum = 0;
			}
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		System.out.println(new HappyNumber().isHappy(5));
	}
}
