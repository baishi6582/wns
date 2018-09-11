package leetcode.date_20180911;

/**
 * 
	题目描述：连续整数求和
	给定一个正整数 N，试求有多少组连续正整数满足所有数字之和为 N?

	示例 1:
	
	输入: 5
	输出: 2
	解释: 5 = 5 = 2 + 3，共有两组连续整数([5],[2,3])求和后为 5。
	示例 2:
	
	输入: 9
	输出: 3
	解释: 9 = 9 = 4 + 5 = 2 + 3 + 4
	示例 3:
	
	输入: 15
	输出: 4
	解释: 15 = 15 = 8 + 7 = 4 + 5 + 6 = 1 + 2 + 3 + 4 + 5
	说明: 1 <= N <= 10 ^ 9
	
	解题思路：见下文
	
	题目链接：https://leetcode-cn.com/problems/consecutive-numbers-sum/description/
	
	注明：此题高德一面算法题
 * @author woniu
 *
 */
public class ConsecutiveNumbersSum {
	/**
	 * 时间复杂度O(N)
	 * 该方法通过滑动窗口来实现的。简单的说最初两个连续的值，如果和小于N时，右端的值要不断增大，
	 * 当满足条件sum == N 时，count++
	 * 左端的值都需要平移一位，无轮当前是相等还是已经大于了N
	 * @param N
	 * @return
	 */
    public int consecutiveNumbersSum1(int N) {
     
    	int count = 1;
    	int start = 1, end = 2;
    	int sum = start + end;
    	while(start < (N+1)/2 && end <= (N+1)/2) {
    		 
    		while (sum < N) {
    			end ++;
    			sum += end;
    		} 
    		if(sum == N) {
    			count ++;
    		}
			sum -= start;
			start ++;
    		
    	}
    	return count;
    }
    
    //s = na1 + n(n-1)/2 = (n+1)a1 + n(n+1)/2 如果能找到需要的n，则必然存在对应a1，a1满足大于0， n满足大于等于0
    //充分体现了学习数学的重要性，这个题其实只想到了暴力破解，但是无奈超时，方法1也是通过网上看到的思路，仍然超时。
    //这个方法理解了老半天，才搞明白了这个公式，好久好久不学数学了。都不会了。
    public int consecutiveNumbersSum(int N) {
        
    	int count = 0;
    	for(int i=0; i<=N; i++) {
    		if(i * (i+1) / 2 > N) {
    			break;
    		}
    		int as = N - i * (i+1) / 2;
    		if(as % (i + 1) != 0) {
    			continue;
    		}
    		if(as / (i + 1) > 0) {
    			count ++;
    		}
    	}
    	return count;
    }
    
    public static void main(String[] args) {
		System.out.println(new ConsecutiveNumbersSum().consecutiveNumbersSum(5));
		System.out.println(new ConsecutiveNumbersSum().consecutiveNumbersSum(9));
		System.out.println(new ConsecutiveNumbersSum().consecutiveNumbersSum(15));
		System.out.println(new ConsecutiveNumbersSum().consecutiveNumbersSum(2));
		System.out.println(new ConsecutiveNumbersSum().consecutiveNumbersSum(1));
		long start = System.currentTimeMillis() ;
		System.out.println(new ConsecutiveNumbersSum().consecutiveNumbersSum(933320757));
		System.out.println(System.currentTimeMillis() - start);
	}
}
