package leetcode.date_20180909;

/**
 * 
 * 题目描述：有效的括号字符串
 * 
 * 给定一个只包含三种字符的字符串：（ ，） 和 *，写一个函数来检验这个字符串是否为有效字符串。有效字符串具有如下规则：
 * 
 * 任何左括号 ( 必须有相应的右括号 )。 
 * 任何右括号 ) 必须有相应的左括号 ( 。
 * 左括号 ( 必须在对应的右括号之前 )。 
 * * 可以被视为单个右括号 ) ，或单个左括号 ( ，或一个空字符串。 
 * 一个空字符串也被视为有效字符串。 
 * 
 * 示例 1:
 * 
 * 输入: "()" 输出: True 示例 2:
 * 
 * 输入: "(*)" 输出: True 示例 3:
 * 
 * 输入: "(*))" 输出: True
 * 
 * 解题思路：
 * 1、遇见)，首先需要去掉一个(来匹配，如果没有(，则通过*来匹配，如果都没有，则证明该字符串不是有效的。
 * 2、之后，需要处理的是多余的(和*，这个时候，就需要记录其位置信息，所以，最初是要记录(和*出现的位置， 如果(位置在*之后，则表明不是有效字符串。
 * 3、如果最后仍有多余的(，则表明其不是有效字符串。
 * 
 * 题目链接：https://leetcode-cn.com/problems/valid-parenthesis-string/description/
 * 
 * @author woniu
 *
 */
public class ValidParenthesisString {
	public boolean checkValidString(String s) {
		int len = s.length();
		int[] c1 = new int[len];
		int[] c2 = new int[len];
		int count1 = 0, count2 = 0;

		for (int i = 0; i < len; i++) {
			char c = s.charAt(i);
			if (c == '(') {
				c1[count1++] = i;
			} else if (c == '*') {
				c2[count2++] = i;
			} else {
				if (count1 != 0) {
					count1--;
				} else if (count2 != 0) {
					count2--;
				} else {
					return false;
				}
			}
		}

		if (count2 >= count1) {
			for (; count1 > 0; count1--, count2--) {
				if (c2[count2 - 1] < c1[count1 - 1]) {
					return false;
				}
			}
		}

		if (count1 != 0) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println(new ValidParenthesisString().checkValidString("(")); // false
		System.out.println(new ValidParenthesisString().checkValidString("(*))")); // true
		System.out.println(
				new ValidParenthesisString().checkValidString("*(*)())()((((((()))**)(((((*((*(()())(((())(())*((")); // false
		System.out.println(new ValidParenthesisString().checkValidString(
				"(((((*(()((((*((**(((()()*)()()()*((((**)())*)*)))))))(())(()))())((*()()(((()((()*(())*(()**)()(())")); // false
	}
}
