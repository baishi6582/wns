package leetcode.date_20180924;

/**
 * 
 * 题目描述：两个字符串的删除操作
 * 给定两个单词 word1 和 word2，找到使得 word1 和 word2 相同所需的最小步数，每步可以删除任意一个字符串中的一个字符。

	示例 1:
	
	输入: "sea", "eat"
	输出: 2
	解释: 第一步将"sea"变为"ea"，第二步将"eat"变为"ea"
	说明:
	
	给定单词的长度不超过500。
	给定单词中的字符只含有小写字母。
	
	题目链接：https://leetcode-cn.com/problems/delete-operation-for-two-strings/description/
 * @author woniu
 *
 */
public class DeleteOperationForTwoStrings {
	
	/**
	 * 将此题进行了转换，转为求其最大长度子序列，
	 * 其中dp[i][j]表示word1的前i个字符和word2的前j个字符组成的两个单词的最长公共子序列的长度
	 * 如果word1.charAt(i) == word2.charAt(j),则 dp[i-1][j-1] + 1，这个比较好理解，因为当前有多了一个相同的字符。所以加1
	 * 如果word1.charAt(i) != word2.charAt(j), 则Math.max(dp[i-1][j], dp[i][j-1]),这个表示，当前字符不相等，错位比较。
	 * sea和eat比较e和a不相等时，则比较s和ea和se和e的最大长度了。
	 * 
	 * @param word1
	 * @param word2
	 * @return
	 */
	 public int minDistance(String word1, String word2) {
		 
		 int len1 = word1.length(), len2 = word2.length();
		 int[][] dp = new int[len1+1][len2+1];
		 
		 for(int i=1; i<=len1; i++) {
			 for(int j=1; j<=len2; j++) {
				 if(word1.charAt(i-1) == word2.charAt(j-1)) {
					 dp[i][j] = dp[i-1][j-1] + 1;
				 } else {
					 dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				 }
			 }
		 }
		 
		 return len1 + len2 - 2 * dp[len1][len2];
	 }
	
	/**
	 * 
	 * 此题的解法，参考了https://github.com/baishi6582/wns/blob/master/java/src/com/woniu/leetcode/20180909/EditDistance.java
	 * 与编辑距离一题及其相似。
	 * @param word1
	 * @param word2
	 * @return
	 */
    public int minDistance1(String word1, String word2) {
        
    	int len1 = word1.length(), len2 = word2.length();
    	
    	int[][] dp = new int[len1 + 1][len2 + 1];
    	
    	for(int i=0; i<len1+1; i++) {
    		dp[i][0] = i;
    	}
    	
    	for(int j=0; j<len2+1; j++) {
    		dp[0][j] = j;
    	}
    	
    	for(int i=1; i<=len1; i++) {
    		for(int j=1; j<=len2; j++) {
    			if(word1.charAt(i-1) == word2.charAt(j-1)) {
    				dp[i][j] = dp[i-1][j-1];
    			} else {
    				dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + 1;
    			}
    		}
    	}
    	
    	return dp[len1][len2];
    }
    
    public static void main(String[] args) {
		System.out.println(new DeleteOperationForTwoStrings().minDistance("deap", "paed"));//6
		System.out.println(new DeleteOperationForTwoStrings().minDistance("sea", "ate"));//4
		System.out.println(new DeleteOperationForTwoStrings().minDistance("sea", "eat"));//2
		System.out.println(new DeleteOperationForTwoStrings().minDistance("spartan", "part"));//3
	}
    
}
