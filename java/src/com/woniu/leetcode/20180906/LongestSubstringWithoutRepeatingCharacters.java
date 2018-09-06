package leetcode.date_20180906;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * 题目描述：无重复字符的最长子串 
 * 给定一个字符串，找出不含有重复字符的最长子串的长度。
 * 
 * 示例 1:
 * 
 * 输入: "abcabcbb" 输出: 3 解释: 无重复字符的最长子串是 "abc"，其长度为 3。 示例 2:
 * 
 * 输入: "bbbbb" 输出: 1 解释: 无重复字符的最长子串是 "b"，其长度为 1。 示例 3:
 * 
 * 输入: "pwwkew" 输出: 3 解释: 无重复字符的最长子串是 "wke"，其长度为 3。 请注意，答案必须是一个子串，"pwke" 是一个子序列
 * 而不是子串。
 * 
 * 解题思路：我的办法其实有点笨了。通过创建一个StringBuilder来存储已经遍历过的字符，如果不包含新字符则添加，如果包含，则选择删除前边字符之前所有的信息
 * 虽然能得到答案，但是这个我忽略了一点，题目中需要的是长度，并非得到该字符串，导致逻辑有点繁琐了。
 * 
 * 题目链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/description/
 * 
 * @author woniu
 *
 */
public class LongestSubstringWithoutRepeatingCharacters {
	public int lengthOfLongestSubstring2(String s) {

		StringBuilder sb = new StringBuilder();
		int maxLength = 0;
		int tempLenth = 0;
		for (int i = 0; i < s.length(); i++) {
			if (!sb.toString().contains(String.valueOf(s.charAt(i)))) {
				sb.append(s.charAt(i));
				tempLenth++;
			} else {
				if (maxLength < tempLenth) {
					maxLength = tempLenth;
				}
				sb.append(s.charAt(i));
				String str = sb.toString();
				int begin = str.indexOf(s.charAt(i)) + 1;
				sb.delete(0, begin);
				tempLenth = tempLenth - begin + 1;
			}
		}
		if (maxLength < tempLenth) {
			maxLength = tempLenth;
		}

		return maxLength;

	}

	public int lengthOfLongestSubstring(String s) {

		Set<Character> set = new HashSet<Character>();
		int maxLength = 0;
		int n = s.length();
		int i = 0, j = 0;
		while (i < n && j < n) {
			if (!set.contains(s.charAt(j))) {
				set.add(s.charAt(j++));
				maxLength = Math.max(maxLength, j - i);
			} else {
				set.remove(s.charAt(i++));
			}
		}

		return maxLength;

	}

	public static void main(String[] args) {
		System.out.println(new LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring("abcabcbb"));
		System.out.println(new LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring("bbbbbb"));
		System.out.println(new LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring(""));
		System.out.println(new LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring("acecfrt"));
		System.out.println(new LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring("pwwkew"));
	}
}
