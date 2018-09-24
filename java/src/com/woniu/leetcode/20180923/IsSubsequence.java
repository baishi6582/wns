package leetcode.date_20180923;
/**
 * 
	题目描述：判断子序列
	
	给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
	
	你可以认为 s 和 t 中仅包含英文小写字母。字符串 t 可能会很长（长度 ~= 500,000），而 s 是个短字符串（长度 <=100）。
	
	字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
	
	示例 1:
	s = "abc", t = "ahbgdc"
	
	返回 true.
	
	示例 2:
	s = "axc", t = "ahbgdc"
	
	返回 false.
	
	解题思路：见下文吧。暴力破解或者indexOf
	
	题目链接：https://leetcode-cn.com/problems/is-subsequence/description/
 * @author woniu
 *
 */
public class IsSubsequence {
	/**
	 * 这是本道题值得学习的地方，用现有的indexOf方法解决该问题
	 * @param s
	 * @param t
	 * @return
	 */
	public boolean isSubsequence(String s, String t) {
		int index = -1;
		for (char c : s.toCharArray()) {
			index = t.indexOf(c, index + 1);
			if (index == -1) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * 此解法，可以AC，难度不大，不多说了
	 * 
	 * @param s
	 * @param t
	 * @return
	 */
	public boolean isSubsequence1(String s, String t) {

		int i = 0, j = 0;
		for (; i < s.length(); i++) {
			boolean isFind = false;
			for (; j < t.length(); j++) {
				if (s.charAt(i) == t.charAt(j)) {
					isFind = true;
					j++;
					break;
				}
			}
			if (!isFind) {
				return false;
			}
		}

		if (i == s.length()) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		System.out.println(new IsSubsequence().isSubsequence("abc", "ahbgdc"));
	}
}
