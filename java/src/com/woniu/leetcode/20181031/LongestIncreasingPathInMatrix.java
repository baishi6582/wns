package leetcode.date_20181031;

/**
 * 
 * 此题为头条面试题，同时在leetCode上也找到了。所以一做。
 * 面试时。思路有了。但是coding没有写出来。自己有点渣渣。。。
 * 
 * 大体上就是通过动态规划的方式，dp来记录已经查询过的点的路径。
 * 
 * 题目链接：https://leetcode-cn.com/problems/longest-increasing-path-in-a-matrix/	
 * 
 * 题目描述：
 * 给定一个整数矩阵，找出最长递增路径的长度。

	对于每个单元格，你可以往上，下，左，右四个方向移动。 你不能在对角线方向上移动或移动到边界外（即不允许环绕）。
	
	示例 1:
	
	输入: nums = 
	[
	  [9,9,4],
	  [6,6,8],
	  [2,1,1]
	] 
	输出: 4 
	解释: 最长递增路径为 [1, 2, 6, 9]。
 * @author woniu
 *
 */
public class LongestIncreasingPathInMatrix {
	
	int[][] dp;
	int[][] flag;
	int dx[] = {1, -1, 0, 0};
	int dy[] = {0, 0, 1, -1};
	int n, m;
    public int longestIncreasingPath(int[][] matrix) {
    	if(matrix.length == 0) {
    		return 0;
    	}
    	
    	n = matrix.length;
    	m = matrix[0].length;
    	dp = new int[n][m];
    	flag = new int[n][m];
    	int ans = 0;
    	for(int i=0; i<n; i++) {
    		for(int j=0; j<m; j++) {
    			dp[i][j] = search(i, j, matrix);
    			ans = Math.max(ans, dp[i][j]);
    		}
    	}
    	
    	return ans;
    }
    
    public int search(int x, int y, int[][] matrix) {
    	if(flag[x][y] != 0) {
    		return dp[x][y];
    	}
    	int ans = 1;
    	int nx, ny;
    	for(int i=0; i<4; i++) {
    		nx = x + dx[i];
    		ny = y + dy[i];
    		if(nx >=0 && nx < n && ny >= 0 && ny < m) {
    			if(matrix[nx][ny] < matrix[x][y]) {
    				ans = Math.max(ans, search(nx, ny, matrix) + 1);
    			}
    		}
    	}	
    	flag[x][y] = 1;
    	dp[x][y] = ans;
    	
    	return ans;
    }
}
