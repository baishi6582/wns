package leetcode.date_20180915;

/**
 * 
	题目描述：2的幂
	给定一个整数，编写一个函数来判断它是否是 2 的幂次方。

	示例 1:
	
	输入: 1
	输出: true
	解释: 20 = 1
	示例 2:
	
	输入: 16
	输出: true
	解释: 24 = 16
	示例 3:
	
	输入: 218
	输出: false
	
	解题思路：见下文描述
	
	题目链接：https://leetcode-cn.com/problems/power-of-two/description/
 * @author woniu
 *
 */
public class PowerOfTwo {
	/**
	 * 最初以为自己能想到位移比较聪明。发现呵呵了。题目比较简单，不多描述。
	 * @param n
	 * @return
	 */
    public boolean isPowerOfTwo(int n) {
     
    	long i=1;
    	
    	while(i != n) {
    		i <<= 1;
    		if(i > n) {
    			return false;
    		}
    	}
    	
    	return true;
    }
    //这才是正真的一行代码解决，绝不用两行的代表。两字：佩服
    public boolean isPowerOfTwo1(int n) {
        return ((n & (n - 1)) == 0 && n > 0);
    }
    
    public static void main(String[] args) {
		System.out.println(new PowerOfTwo().isPowerOfTwo(1073741825));
	}
}
