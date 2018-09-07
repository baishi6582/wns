package leetcode.date_20180907;

/**
 * 
	题目描述：回文数
	判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。

	示例 1:
	
	输入: 121
	输出: true
	示例 2:
	
	输入: -121
	输出: false
	解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
	示例 3:
	
	输入: 10
	输出: false
	解释: 从右向左读, 为 01 。因此它不是一个回文数。
	
	解题思路：题目很简单，负数默认不是回文数，特殊处理，其他的求出其翻转数，然后比较是否相等即可。
	
	题目链接：https://leetcode-cn.com/problems/palindrome-number/description/
 * @author woniu
 *
 */
public class PalindromeNumber {
    public boolean isPalindrome(int x) {
     
    	//负数 不是回文数
    	if(x < 0) {
    		return false;
    	}
    	
    	int palindrome = 0;
    	int temp = x;
    	while(temp != 0) {
    		palindrome = palindrome * 10 + temp % 10;
    		temp = temp / 10;
    	}
    	
    	if(palindrome == x) {
    		return true;
    	}
    	
    	return false;
    }
    
    public static void main(String[] args) {
		System.out.println(new PalindromeNumber().isPalindrome(0));
		System.out.println(new PalindromeNumber().isPalindrome(-1));
		System.out.println(new PalindromeNumber().isPalindrome(-12));
		System.out.println(new PalindromeNumber().isPalindrome(1));
		System.out.println(new PalindromeNumber().isPalindrome(10));
		System.out.println(new PalindromeNumber().isPalindrome(121));
	}
}
