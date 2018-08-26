package leetcode;

/**
 * 
 	题目描述：不同路径
 	一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。

	机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
	
	问总共有多少条不同的路径？
	
	说明：m 和 n 的值均不超过 100。
	
	示例 1:
	
	输入: m = 3, n = 2
	输出: 3
	解释:
	从左上角开始，总共有 3 条路径可以到达右下角。
	1. 向右 -> 向右 -> 向下
	2. 向右 -> 向下 -> 向右
	3. 向下 -> 向右 -> 向右
	示例 2:
	
	输入: m = 7, n = 3
	输出: 28
 	
 	解题思路：动态规划的题目，总是做不好。缺少这种思想。这个题也是在网上找的攻略才得解，大概做法是
 	d[i][j] = d[i-1][j] + d[i][j-1].思想说起来比较简单，但是每次都用不好。希望日后可以进一步提升吧。
 	
 	题目链接：https://leetcode-cn.com/problems/unique-paths/description/
 * @author woniu
 *
 */
public class UniquePaths {
	public int uniquePaths(int m, int n) {
		if(m == 0 && n == 0) {
			return 0;
		}
		int[][] dp = new int[m][n];
		for(int i=0; i<m; i++) {
			dp[i][0] = 1;
		}
		for(int j=0; j<n; j++) {
			dp[0][j] = 1;
		}
		for(int i=1; i<m; i++) {
			for(int j=1; j<n; j++) {
				dp[i][j] = dp[i][j-1] + dp[i-1][j];
			}
		}
        return dp[m-1][n-1];
    }
}
