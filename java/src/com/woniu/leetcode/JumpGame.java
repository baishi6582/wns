package leetcode;

/**
 * 
 * 
	题目描述：跳跃游戏
	给定一个非负整数数组，你最初位于数组的第一个位置。
	
	数组中的每个元素代表你在该位置可以跳跃的最大长度。
	
	判断你是否能够到达最后一个位置。
	
	示例 1:
	
	输入: [2,3,1,1,4]
	输出: true
	解释: 从位置 0 到 1 跳 1 步, 然后跳 3 步到达最后一个位置。
	示例 2:
	
	输入: [3,2,1,0,4]
	输出: false
	解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
	
	解题思路：当你拿到一个数字后，代表你可以跳跃的最远距离，如果你在跳跃过程中遇见了大于剩余步数的数字，
	需要替换成新的步数，表明可以跳的更远。
	
	题目链接；https://leetcode-cn.com/problems/jump-game/
 * @author z00364813
 *
 */
public class JumpGame {
	 public boolean canJump(int[] nums) {
		 //对于异常用例，题目中没有明确说明，直接按照可以访问到最后处理
		 if(nums == null && nums.length == 0 || nums.length == 1) {
			 return true;
		 }
		 int len = nums.length;
		 int step = nums[0];
		 int i = 1;
		 for(; i<len-1; i++) {
			 step--;
			 //对于特殊案例，如：[0,2,3]
			 if(step < 0) {
				 return false;
			 }
			 if(nums[i] > step) {
				 step = nums[i];
			 }
			 if(step == 0) {
				 step = nums[i];
			 }
		 }
		 if(step > 0) {
			 return true;
		 }
		 return false;
	}
	 public static void main(String[] args) {
		int[] nums = {2,3,1,1,4};
		System.out.println(new JumpGame().canJump(nums));
	}
}
