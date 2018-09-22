package leetcode.date_20180922;
/**
 * 
	题目描述：最长的斐波那契子序列的长度
	如果序列 X_1, X_2, ..., X_n 满足下列条件，就说它是 斐波那契式 的：
	n >= 3
	对于所有 i + 2 <= n，都有 X_i + X_{i+1} = X_{i+2}
	给定一个严格递增的正整数数组形成序列，找到 A 中最长的斐波那契式的子序列的长度。如果一个不存在，返回  0 。
	
	（回想一下，子序列是从原序列 A 中派生出来的，它从 A 中删掉任意数量的元素（也可以不删），而不改变其余元素的顺序。例如， [3, 5, 8] 是 [3, 4, 5, 6, 7, 8] 的一个子序列）
	
	示例 1：
	
	输入: [1,2,3,4,5,6,7,8]
	输出: 5
	解释:
	最长的斐波那契式子序列为：[1,2,3,5,8] 。
	示例 2：
	
	输入: [1,3,7,11,12,14,18]
	输出: 3
	解释:
	最长的斐波那契式子序列有：
	[1,11,12]，[3,11,14] 以及 [7,11,18] 。
	
	
	解题思路：主要看注解吧，其实思路比较乱。用的方法有些暴力破解的方式，只因没有找到太好的规律。

	题目链接：https://leetcode-cn.com/problems/length-of-longest-fibonacci-subsequence/description/
 * @author woniu
 *
 */
public class LengthOfLongestFibonacciSubsequence {
    public int lenLongestFibSubseq(int[] nums) {
        int result = 0;
        int len = nums.length;
        
        for(int i=0; i<len-2; i++) {
        	int a = nums[i], b = nums[i+1];
        	int sum = a + b;//计算出当前第一位和第二位的结果
        	int count = 2;//用于记录当前的长度
        	int index = i + 1;//标记当前第二位的位置
        	for(int j=i+2; j<len; j++) {
        		if(sum == nums[j]) {//找到两者的和
        			count ++;
        			a = b;
        			b = nums[j];
        			sum = a + b;
        		} else if (sum < nums[j]) {//如果当前两者的和小于目前该值，则不用继续，因为后续结果必然大于当前结果
        			if(count >=3 && count > result) {//判断是否需要替换结果
                		result = count;
                		
                	}
        			count = 2;//以下全部来开始一轮新的计算
        			a = nums[i];
        			b = nums[++index];
        			sum = a + b;
        			j = index;//因为for循环中会+1，所以这里不用特意加1
        		}
        	}
        	
        	if(count >=3 && count > result) {
        		result = count;
        	}
        }
        
        
    	return result;
    }
    
    public static void main(String[] args) {
		System.out.println(new LengthOfLongestFibonacciSubsequence().lenLongestFibSubseq(new int[] {2,4,7,8,9,10,14,15,18,23,32,50}));
	}
}
