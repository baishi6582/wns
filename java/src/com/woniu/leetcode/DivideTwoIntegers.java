package leetcode;

/**
 * 
	题目描述：两数相除
	给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。

	返回被除数 dividend 除以除数 divisor 得到的商。
	
	示例 1:
	
	输入: dividend = 10, divisor = 3
	输出: 3
	示例 2:
	
	输入: dividend = 7, divisor = -3
	输出: -2
	说明:
	
	被除数和除数均为 32 位有符号整数。
	除数不为 0。
	假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231,  231 − 1]。本题中，如果除法结果溢出，则返回 231 − 1。
	
	解题思路：题目中说明了。不允许用乘法、除法，之后考虑用减法，看下被除数中有多少除数。很遗憾，各种超时。
	之后，去网上搜索解题思路，大概都是通过位移这种来减少计算次数，提升效率的。
	
	题目链接：https://leetcode-cn.com/problems/divide-two-integers/description/
 * @author woniu
 *
 */
public class DivideTwoIntegers {
	public int divide(int dividend, int divisor) {
		if(Integer.MIN_VALUE == dividend && divisor == -1) {
			return Integer.MAX_VALUE;
		}
		if(Integer.MIN_VALUE == dividend && divisor == 1) {
			return Integer.MIN_VALUE;
		}
		
		long dvd = Math.abs((long)dividend);
		long dvs = Math.abs((long)divisor);
		int result = 0;
		while(dvd >= dvs) {
			long tmp = dvs;
			int count = 1;//这里用来记录内循环的次数，即每次找距离被除数最近的那个数的次数，也是通过位移来做
			//这里找到一个距离被除数最近的是除数倍数的数，这样就可以快速消除一个大数了，提升了计算效率，依次这样重复，即可得到最好结果
			while(dvd >= (tmp << 1)) {
				tmp <<= 1;
				count <<= 1;
			}
			dvd -= tmp;
			result += count;
		}
		
		//通过一个异或来确定两个数的符号
		if((divisor < 0) ^ (dividend < 0)  ) {
			result = -result;
		}
		
        return result;
    }
	
	public static void main(String[] args) {
		System.out.println(new DivideTwoIntegers().divide(1, 1));
	}
}
 
