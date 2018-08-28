package leetcode;
/**
 * 
	題目描述：阶乘后的零
	给定一个整数 n，返回 n! 结果尾数中零的数量。

	示例 1:
	
	输入: 3
	输出: 0
	解释: 3! = 6, 尾数中没有零。
	示例 2:
	
	输入: 5
	输出: 1
	解释: 5! = 120, 尾数中有 1 个零.
	说明: 你算法的时间复杂度应为 O(log n) 。
	
	解题思路：对于结果尾数几个0，其实与5有直接关系，相乘后尾数出现0的情况为2*5，即偶数*5，由于偶数出现的概率远远大于5出现的概率，
	因此，该问题实际上等同于求5出现的概率。个人理解的规律应该为5个5会多出来一个5，就是6个，比如说25， 5、10、15、20、25，其实25为5*5
	所以说，n/5得到实际存在几个5，之后再算多出来的几个5就行了
	
	题目链接：https://leetcode-cn.com/problems/factorial-trailing-zeroes/description/
 * @author woniu
 *
 */
public class FactorialTrailingZeroes {
	public int trailingZeroes(int n) {
		int zeroCnt = 0;
		while (n > 0) {
			zeroCnt += n / 5;
			n = n / 5;
		}
		return zeroCnt;
	}

	public static void main(String[] args) {
		System.out.println(new FactorialTrailingZeroes().trailingZeroes(1000));
	}
}
