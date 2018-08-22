package leetcode;

/**
 
      题目描述：

       给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为1000。

	示例 1：
	
	输入: "babad"
	输出: "bab"
	注意: "aba"也是一个有效答案。
	示例 2：
	
	输入: "cbbd"
	输出: "bb"
	
	
	思路：
	以此以所在的字符为中心向两侧遍历查找，这样就能找到最长的回文字符串。这里需要注意的是奇偶之分。
	如：beed和bab这两种是不通的。
	
 * @author woniu
 *
 */
class PalindromicString {

	private int startIndex, maxLen;
	public String longestPalindrome(String s) {
		if(s.length() < 2) {
			return s;
		}
		for(int i=0; i<s.length(); i++) {
			getpalindromicString(s, i, i);//bab
			getpalindromicString(s, i, i+1);//beed
		}

		return s.substring(startIndex, startIndex + maxLen);
	}
	
	public void getpalindromicString(String s, int j, int k) {
		while(j>=0 && k<s.length() && s.charAt(j) == s.charAt(k)) {
			j--;
			k++;
		}
		//由于k已经加过，k已经减过了
		if(maxLen < k - j - 1) {
			maxLen = k - j - 1;
			startIndex = j + 1;
		}
	}

//	public static void main(String[] args) {
//		System.out.println(new Solution().longestPalindrome("abcdededc"));
//		System.out.println(new Solution().longestPalindrome("a"));
//		System.out.println(new Solution().longestPalindrome(""));
//		System.out.println(new Solution().longestPalindrome("abcded"));
//		System.out.println(new Solution().longestPalindrome("abcdede"));
//		System.out.println(new Solution().longestPalindrome("ac"));
//		System.out.println(new Solution().longestPalindrome("bb"));
//		System.out.println(new Solution().longestPalindrome("cccccccc"));
//		System.out.println(new Solution().longestPalindrome("babad"));
//	}
}
