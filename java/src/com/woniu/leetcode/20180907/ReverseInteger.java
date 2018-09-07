package leetcode.date_20180907;

/**
 * 
 * 题目描述：反转整数
 * 给定一个 32 位有符号整数，将整数中的数字进行反转。
 * 
 * 示例 1:
 * 
 * 输入: 123 输出: 321 示例 2:
 * 
 * 输入: -123 输出: -321 示例 3:
 * 
 * 输入: 120 输出: 21 注意:
 * 
 * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231, 231 − 1]。根据这个假设，如果反转后的整数溢出，则返回 0。
 * 
 * 解题思路：题目比较简单，取余进位相加，x为取模计算。 
 * 需要注意的点，就是溢出这个场景，既然题目中明确提出了，这个case必须特殊处理。
 * 
 * 题目链接：https://leetcode-cn.com/problems/reverse-integer/description/
 * 
 * @author woniu
 *
 */
public class ReverseInteger {
	public int reverse(int x) {

		int sum = 0;
		while (x != 0) {
			int pop = x % 10;
			x = x / 10;
			// 对于溢出场景特殊处理，如果当前的sum已经大于Integer.MAX_VALUE / 10，则其后续计算必溢出。
			// 相等时，个位不能超过7，因为最大值为2147483647
			if (Integer.MAX_VALUE / 10 < sum || (Integer.MAX_VALUE / 10 == sum && pop > 7)) {
				return 0;
			}
			// 思路与最大值相似，最小值为-2147483648
			if (Integer.MIN_VALUE / 10 > sum || Integer.MIN_VALUE / 10 == sum && pop < -8) {
				return 0;
			}
			sum = sum * 10 + pop;
		}

		return sum;
	}

	public static void main(String[] args) {
		System.out.println(new ReverseInteger().reverse(1230));
		System.out.println(new ReverseInteger().reverse(-123));
		System.out.println(new ReverseInteger().reverse(2147483647));
		System.out.println(new ReverseInteger().reverse(-2147483412));
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Integer.MIN_VALUE);
	}
}
