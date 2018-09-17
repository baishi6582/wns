package leetcode.date_20180917;

/**
 * 
	题目描述：最长公共前缀
	
	编写一个函数来查找字符串数组中的最长公共前缀。
	
	如果不存在公共前缀，返回空字符串 ""。
	
	示例 1:
	
	输入: ["flower","flow","flight"]
	输出: "fl"
	示例 2:
	
	输入: ["dog","racecar","car"]
	输出: ""
	解释: 输入不存在公共前缀。
	说明:
	
	所有输入只包含小写字母 a-z 。
	
	解题思路：题目比较简单，不多说，依次遍历查找相同字符即可。
	
	题目链接：https://leetcode-cn.com/problems/longest-common-prefix/description/
 * @author woniu
 *
 */
public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
    	if(strs.length == 0	) {
    		return "";
    	}
    	StringBuilder sb = new StringBuilder();
	     for(int i=0; i<strs[0].length(); i++) {//这是出错，导致没有一遍AC，由于length写成了strs.length，细节决定成败，下次谨记
	    	 char c = strs[0].charAt(i);
	    	 
	    	 for(int j=1; j<strs.length; j++) {
	    		 if(strs[j].length() <= i || strs[j].charAt(i) != c) {
	    			 return sb.toString();
	    		 }
	    	 }
	    	 sb.append(c);
	    	 
	     }
	     
	     return sb.toString();
    }
    
    public static void main(String[] args) {
		String[] strs = {"dog","racecar","car"};
		System.out.println(new LongestCommonPrefix().longestCommonPrefix(new String[]{"dog","racecar","car"}));
		System.out.println(new LongestCommonPrefix().longestCommonPrefix(new String[]{"flower","flow","flight"}));
		System.out.println(new LongestCommonPrefix().longestCommonPrefix(new String[]{""}));
		
		System.out.println(new LongestCommonPrefix().longestCommonPrefix(new String[]{"abc", ""}));
		
		System.out.println(new LongestCommonPrefix().longestCommonPrefix(new String[]{"abc"}));
	}
}
