package leetcode;

/**
 * 
	题目描述：零钱兑换
	给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。

	示例 1:
	
	输入: coins = [1, 2, 5], amount = 11
	输出: 3 
	解释: 11 = 5 + 5 + 1
	示例 2:
	
	输入: coins = [2], amount = 3
	输出: -1
	说明:
	你可以认为每种硬币的数量是无限的。
	
	解题思路:使用动态规划的思想来做这道题就比较简单了。dp[i] = Math.min(dp[i-coin] + 1, dp[i])这个为计算公式。
	比如说需要计算11块， 币值5块，则就分为了dp[i] 与 dp[6]+1 哪种最少。所以，初始值要给一个足够大的值，避免引起计算错误。
	
	题目链接：https://leetcode-cn.com/problems/coin-change/description/
 * @author woniu
 *
 */
public class CoinChange {
	public int coinChange(int[] coins, int amount) {

		int[] dp = new int[amount + 1];
		for (int i = 1; i <= amount; i++) {
			dp[i] = amount + 1;//因为题目描述了为整数，所以，最多只可能有amount个(全是1的情况下)
		}

		for (int coin : coins) {
			for (int i = coin; i <= amount; i++) {
				dp[i] = Math.min(dp[i - coin] + 1, dp[i]);
			}
		}
		return dp[amount] == amount + 1 ? -1 : dp[amount];
	}

	public static void main(String[] args) {
		int[] coins = { 2, 5 };
		int amount = 3;
		System.out.println(new CoinChange().coinChange(coins, amount));
	}
}
