package leetcode.date_20180909;

/**
 * 
	题目描述：编辑距离
	给定两个单词 word1 和 word2，计算出将 word1 转换成 word2 所使用的最少操作数 。

	你可以对一个单词进行如下三种操作：
	
	插入一个字符
	删除一个字符
	替换一个字符
	示例 1:
	
	输入: word1 = "horse", word2 = "ros"
	输出: 3
	解释: 
	horse -> rorse (将 'h' 替换为 'r')
	rorse -> rose (删除 'r')
	rose -> ros (删除 'e')
	示例 2:
	
	输入: word1 = "intention", word2 = "execution"
	输出: 5
	解释: 
	intention -> inention (删除 't')
	inention -> enention (将 'i' 替换为 'e')
	enention -> exention (将 'n' 替换为 'x')
	exention -> exection (将 'n' 替换为 'c')
	exection -> execution (插入 'u')
	
	解题思路：对于word1和word2相关字符串，
	1、如果两个都为空，则需要步骤为0
	2、如果word1为空，则需要word2.length步add操作
	3、如果word2为空，则需要word1.length步del操作。
	之后，我们基于此建立一个二维数组，初始值即为这些特例情况，
	然后，依次判断各个字符相互转换需要的步骤，
	如果word1[i] == word2[i] 则 dp[i][j] = dp[i-1][j-1]
	如果word1[i] != word2[i] 则dp[i][j] = Math.min(dp[i-1][j-1],  dp[i-1][j],  dp[i][j-1]) + 1.
	
	参考链接；https://blog.csdn.net/chichoxian/article/details/53944188
	
	题目链接：https://leetcode-cn.com/problems/edit-distance/description/
	
 * @author woniu
 *
 */
public class EditDistance {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        
        int[][] dp = new int[m + 1][n + 1];
        for(int i=0; i<m+1; i++) {
        	dp[i][0] = i;
        }
        for(int j=0; j<n+1; j++) {
        	dp[0][j] = j;
        }
        
        for(int i=1; i<m+1; i++) {
        	for(int j=1; j<n+1; j++) {
        		if(word1.charAt(i - 1) == word2.charAt(j - 1)) {
        			dp[i][j] = dp[i-1][j-1];
        		} else {
        			dp[i][j] = Math.min(Math.min(dp[i-1][j-1], dp[i-1][j]), dp[i][j-1]) + 1;
        		}
        	}
        }
    	return dp[m][n];
    }
    
    public static void main(String[] args) {
		//String word1 = "horse", word2 = "ros";
		String word1 = "intention", word2 = "execution";
		System.out.println(new EditDistance().minDistance(word1, word2));
	}
}
