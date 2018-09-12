package leetcode.date_20180912;

/**
 * 
	题目描述:回文素数
	求出大于或等于 N 的最小回文素数。

	回顾一下，如果一个数大于 1，且其因数只有 1 和它自身，那么这个数是素数。
	
	例如，2，3，5，7，11 以及 13 是素数。
	
	回顾一下，如果一个数从左往右读与从右往左读是一样的，那么这个数是回文数。
	
	例如，12321 是回文数。
	
	 
	
	示例 1：
	
	输入：6
	输出：7
	示例 2：
	
	输入：8
	输出：11
	示例 3：
	
	输入：13
	输出：101
	
	解题思路：此题并没有AC，总是超时，网上暂时没有看到有更优的办法，暂时先放一下。
	
	题目链接：https://leetcode-cn.com/problems/prime-palindrome/description/
	
 * @author woniu
 *
 */
public class PrimePalindrome {
    public int primePalindrome(int N) {
     
    	if(N == 1 || N == 2) {
    		return 2;
    	}
    	
    	if(N % 2 == 0) {
    		N ++;
    	}
    	
    	while(!isPalindrome(N) || !isPrime(N)) {
    		N += 2;
    	}
    	
    	return N;
    }
    //判断是否为素数
    public boolean isPrime(int num) {
    	for(int i=3; i<=Math.sqrt(num); i+=2) {
    		if(num % i == 0) {
    			return false;
    		}
    	}
    	return true;
    }
    
    //判断是否为回文数
    public boolean isPalindrome(int num) {
    	
    	int n = 0;
    	int tmp = num;
    	while(num != 0) {
    		n = n * 10 + num % 10; 
    		num = num / 10;
    	}
    	
    	if(tmp == n) {
    		return true;
    	}
    	
    	return false;
    }
    
    public boolean isPalindrome2(int num) {
    	
    	String str = String.valueOf(num);
    	int len = str.length();
    	for(int i=0; i<=len/2; i++) {
    		if(str.charAt(i) != str.charAt(len - i - 1)) {
    			return false;
    		}
    	}
    	
    	return true;
    }
    
    public static void main(String[] args) {
    	System.out.println(new PrimePalindrome().primePalindrome(1));
    	System.out.println(new PrimePalindrome().primePalindrome(2));
    	System.out.println(new PrimePalindrome().primePalindrome(3));
		System.out.println(new PrimePalindrome().primePalindrome(6));
		System.out.println(new PrimePalindrome().primePalindrome(8));
		System.out.println(new PrimePalindrome().primePalindrome(13));
		long start = System.currentTimeMillis();
		System.out.println(new PrimePalindrome().primePalindrome(9989900));
		System.out.println(System.currentTimeMillis() - start);
		System.out.println("-----------------------------");
		start = System.currentTimeMillis();
		System.out.println(new PrimePalindrome().primePalindrome(61023998));
		System.out.println(System.currentTimeMillis() - start);
		System.out.println(new PrimePalindrome().primePalindrome(933320757));
	}
}
