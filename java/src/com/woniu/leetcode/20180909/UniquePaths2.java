package leetcode.date_20180909;

/**
 * 
	题目描述：不同路径 II
	一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。

	机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
	
	现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
	
	
	
	网格中的障碍物和空位置分别用 1 和 0 来表示。
	
	说明：m 和 n 的值均不超过 100。
	
	示例 1:
	
	输入:
	[
	  [0,0,0],
	  [0,1,0],
	  [0,0,0]
	]
	输出: 2
	解释:
	3x3 网格的正中间有一个障碍物。
	从左上角到右下角一共有 2 条不同的路径：
	1. 向右 -> 向右 -> 向下 -> 向下
	2. 向下 -> 向下 -> 向右 -> 向右
	
	解题思路：动态规划，需要注意一点事0,0位置需要判断一下是否存在障碍物
	之后，需要注意其中存在障碍物时，将dp设置为0.
	其他的场景，则是计算前边的dp[i][j] = dp[i-1][j] + dp[i][j-1];
	
	题目链接；https://leetcode-cn.com/problems/unique-paths-ii/description/ 
 * @author woniu
 *
 */
public class UniquePaths2 {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        
    	int m = obstacleGrid.length;
    	int n = obstacleGrid[0].length;
    	
    	int[][] dp = new int[m][n];
    	if(obstacleGrid[0][0] == 0) {
    		dp[0][0] = 1;
    	}
    	
    	
    	for(int i=0; i<m; i++) {
    		for(int j=0; j<n; j++) {
    			if((i == 0 && j == 0) || obstacleGrid[i][j] == 1) {
    				continue;
    			}
    			dp[i][j] = (i-1 >= 0 ? dp[i-1][j] : 0) + (j-1 >=0 ? dp[i][j-1] : 0);
    		}
    	}
    	
    	return dp[m - 1][n - 1];
    }
    
    public static void main(String[] args) {
    	int[][] obstacleGrid = {
    			  {0,0,0},
    			  {0,1,0},
    			  {0,0,0}
    			};
		System.out.println(new UniquePaths2().uniquePathsWithObstacles(obstacleGrid));
	}
}
