package leetcode.date_20180910;

/**
 * 
	题目描述：不同的二叉搜索树
	给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？

	示例:
	
	输入: 3
	输出: 5
	解释:
	给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
	
	   1         3     3      2      1
	    \       /     /      / \      \
	     3     2     1      1   3      2
	    /     /       \                 \
	   2     1         2                 3
	   
	解题思路：题目重点是找到每一层的关系，找到关系后，就可以编码处理了。
	1、大概描述一下，比如说，根节点为1，则只需要计算其余i-1个的可能性即可。
	2、根节点为2，则由于1肯定只有一种可能性，则执行要计算i-2个数值的可能性
	3、根节点为3，只需要计算左边1、2的可能性，即dp[2] ,以及右端的i-3个数的可能性dp[i-3],和为dp[2]dp[i-3].
	......依次这样计算即可得到结果。
	dp[i] = dp[i-1]dp[0] + dp[i-2]dp[1] + ... + dp[1]dp[i-2] + dp[0]dp[i-1];
	
	题目链接：https://leetcode-cn.com/problems/unique-binary-search-trees/description/
 * @author woniu
 *
 */
public class UniqueBinarySearchTrees {
    public int numTrees(int n) {
        
    	int[] dp = new int[n + 1];
    	dp[0] = 1;
    	dp[1] = 1;
    	for(int i=2; i<=n; i++) {
    		for(int j=0; j<i; j++) {//先计算出dp[2]、dp[3]、dp[4] ....
        		dp[i] += dp[j] * dp[i-j-1];
        	}
    	}
    	
    	return dp[n];
    }
    
    public static void main(String[] args) {
		System.out.println(new UniqueBinarySearchTrees().numTrees(4));
	}
}
