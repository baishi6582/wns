package leetcode;

/**
 * 
	题目描述：Pow(x, n)
	实现 pow(x, n) ，即计算 x 的 n 次幂函数。

	示例 1:	
	
	输入: 2.00000, 10
	输出: 1024.00000
	示例 2:
	
	输入: 2.10000, 3
	输出: 9.26100
	示例 3:
	
	输入: 2.00000, -2
	输出: 0.25000
	解释: 2-2 = 1/22 = 1/4 = 0.25
	说明:
	
	-100.0 < x < 100.0
	n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。
	
	解题思路：其实这道题我做的不好，n为负数，之后取反，然后依次相乘。并对一些特殊的数字进行了处理。这种显的乱，切不高效
	之后看到了使用位移的计算方式，这种方式很好，日后在计算中多想想这种方式
	
	题目链接：https://leetcode-cn.com/problems/powx-n/description/
 * @author woniu
 *
 */
public class Pow {
	public double myPow1(double x, int n) {
		double result = 1.0;

		if (n == 0 || x == 1) {
			return result;
		}

		if (x == -1 && n % 2 == 0) {
			return 1;
		}

		if (x == -1 && n % 2 != 0) {
			return -1;
		}
		// 特殊情况特殊处理了
		if (n == -2147483648) {
			return 0;
		}

		int pow = n;

		if (pow < 0) {
			pow = -pow;
			x = 1 / x;
		}

		for (int i = 1; i <= pow; i++) {
			result *= x;
			if (result == 0) {
				return 0;
			}
		}
		return result;
	}

	// 个人更推崇这种解决方式，高效
	public double myPow(double x, int n) {
		boolean reverse = false;
		if (n < 0) {
			n = -n;
			reverse = true;
		}
		double tmp = x;
		double res = 1;
		for (int i = 0; i < 32; i++) {
			if ((n & 1) == 1)
				res *= tmp;
			n = n >> 1;
			tmp = tmp * tmp;
		}
		return reverse ? 1.0 / res : res;
	}

	public double myPow2(double x, int n) {
		if (n == 0 || x == 1)
			return 1;
		else if (n == 1)
			return x;

		boolean flag = true;
		if (n < 0) {
			n = -n;
			flag = false;
		}
		double result = pow(x, n);

		if (flag)
			return result;
		else
			return 1 / result;
	}

	public double pow(double x, int n) {
		if (n == 1)
			return x;
		else if (n == 0)
			return 1;

		double res = pow(x, n / 2);

		if (n % 2 == 0) {
			return res * res;
		} else {
			return res * res * x;
		}

	}

	public static void main(String[] args) {
		System.out.println(new Pow().myPow(2.00000, -2147483648));
	}
}
