package leetcode.date_20180914;

/**
 * 
	题目描述：字符串相乘
	给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。

	示例 1:
	
	输入: num1 = "2", num2 = "3"
	输出: "6"
	示例 2:
	
	输入: num1 = "123", num2 = "456"
	输出: "56088"
	说明：
	
	num1 和 num2 的长度小于110。
	num1 和 num2 只包含数字 0-9。
	num1 和 num2 均不以零开头，除非是数字 0 本身。
	不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
	
	解题思路：每次取出一位与另一个数字进行计算，计算过程保持着进位即可，需要特别注意的就是结果中在该位已经有值了，则需要一并相加
	两种方法，思路一致，只是用数组的方式执行效率会更高。
	
	题目链接：https://leetcode-cn.com/problems/multiply-strings/description/
 * @author woniu
 *
 */
public class MultiplyStrings {

	public String multiply(String num1, String num2) {
		if ("0".equals(num1) || "0".equals(num2)) {
			return "0";
		}
		int len1 = num1.length(), len2 = num2.length();
		StringBuilder sb = new StringBuilder();
		int[] nums = new int[len1 + len2];

		for (int i = len1 - 1; i >= 0; i--) {

			int n1 = num1.charAt(i) - '0';
			int a = 0, b = 0, c = 0;
			int index = len1 - 1 - i; //用于记录当前在哪个位置开始取数，类似于保持着进制
			for (int j = len2 - 1; j >= 0; j--) {
				int n2 = num2.charAt(j) - '0';
				c = nums[index];//取出该位上已经存在的数字
				a = (n1 * n2 + b + c) % 10;//算出当前结果
				b = (n1 * n2 + b + c) / 10;//进位
				nums[index] = a;
				index++;

			}
			nums[index] = b;
		}

		boolean isFirst = true;
		for (int i = nums.length - 1; i >= 0; i--) {
			if (isFirst && nums[i] == 0) {
				continue;
			}
			isFirst = false;
			sb.append(nums[i]);
		}

		return sb.toString();
	}

	public String multiply1(String num1, String num2) {

		if ("0".equals(num1) || "0".equals(num2)) {
			return "0";
		}
		StringBuilder sb = new StringBuilder();
		int len1 = num1.length(), len2 = num2.length();
		for (int i = len1 - 1; i >= 0; i--) {

			int n1 = Integer.parseInt("" + num1.charAt(i));
			int a = 0, b = 0, c = 0;
			int index = len1 - 1 - i;
			for (int j = len2 - 1; j >= 0; j--) {
				int n2 = Integer.parseInt("" + num2.charAt(j));
				if (index <= sb.length() - 1) {
					c = Integer.parseInt("" + sb.toString().charAt(index));
				}
				a = (n1 * n2 + b + c) % 10;
				b = (n1 * n2 + b + c) / 10;
				sb.replace(index, index + 1, a + "");
				c = 0;
				index++;

			}
			if (b > 0) {
				sb.append(b);
			}
		}

		return sb.reverse().toString();
	}

	public static void main(String[] args) {
		String num1 = "31";// "123";
		String num2 = "121";
		System.out.println(new MultiplyStrings().multiply(num1, num2));
	}
}
