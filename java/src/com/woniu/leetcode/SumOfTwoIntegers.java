package leetcode;

/**
 * 
	题目描述：两整数之和
	不使用运算符 + 和 - ​​​​​​​，计算两整数 ​​​​​​​a 、b ​​​​​​​之和。

	示例 1:
	
	输入: a = 1, b = 2
	输出: 3
	示例 2:
	
	输入: a = -2, b = 3
	输出: 1
	
	解题思路：见方法1和2注释
	
	题目链接：https://leetcode-cn.com/problems/sqrtx/description/
 *
 * @author z00364813
 *
 */
public class SumOfTwoIntegers {
	
	//这个思路很巧妙，通过位移的方式实现的，计算效率很高，
	//先通过异或计算得到不进位的和，之后再算出进位，之后再算两者的和，依次计算，直到进位为0时，便是结果。
	public int getSum(int a, int b) {

		int res = 0;
		// 不进位的加和
		int xor = a ^ b;

		// 进位和
		int forward = (a & b) << 1;
		if (forward != 0) {
			res = getSum(xor, forward);// 循环处理 进位和+不进位加和
		} else {
			res = xor;
		}
		return res;
	}
	
	//这种方法虽然效率不会有方法1好，但至少是一种思路，而且是一个很简洁的思路。同时并未直接做相加或者相减
	public int getSum2(int a, int b) {

		while (b != 0) {
			if (b > 0) {
				a++;
				b--;
			} else {
				a--;
				b++;
			}
		}
		return a;
	}

	public static void main(String[] args) {
		new SumOfTwoIntegers().getSum(-2, 4);
	}
}
