package leetcode.date_20180913;

/**
 * 
	题目描述：到达终点数字
	在一根无限长的数轴上，你站在0的位置。终点在target的位置。

	每次你可以选择向左或向右移动。第 n 次移动（从 1 开始），可以走 n 步。
	
	返回到达终点需要的最小移动次数。
	
	示例 1:
	
	输入: target = 3
	输出: 2
	解释:
	第一次移动，从 0 到 1 。
	第二次移动，从 1 到 3 。
	示例 2:
	
	输入: target = 2
	输出: 3
	解释:
	第一次移动，从 0 到 1 。
	第二次移动，从 1 到 -1 。
	第三次移动，从 -1 到 2 。
	注意:
	
	target是在[-10^9, 10^9]范围中的非零整数。


	解题思路：这道题没有找到规律，还是在网上找到了相关解法。数学题，一旦找不到规律就是死局。
	首先一次相加直到和大于目标，比较差值，
	如果是偶数，说明只需要将几个数又加号变为减号（里外里2倍，所以一定是偶数），
	若是奇数，就要想办法制造1，减去一个奇数加上下一个偶数即可，
	       如果当前n是个奇数，就需要加上下一个偶数，
	       如果当前是个偶数就需要减去下一个奇数，加上下一个偶数。
	       
	题目链接：https://leetcode-cn.com/problems/reach-a-number/description/
 * @author woniu
 *
 */
public class ReachANumber {
    public int reachNumber(int target) {
    	if(target < 0) {
    		target = -target;
    	}
    	
        int n = 1;
        int position = 0;
        while(position < target) {
        	position += n;
        	n++;
        }
        n--;
        if(position == target) {
        	return n;
        }
        
        int diff = position - target;
        
        if(diff % 2 == 0) {
        	return n;
        } else if((n + 1) % 2 == 1) {
        	return n + 1;
        } else {
        	return n + 2;
        }
        
    }
    
    public static void main(String[] args) {
		System.out.println(new ReachANumber().reachNumber(-5));
	}
}
