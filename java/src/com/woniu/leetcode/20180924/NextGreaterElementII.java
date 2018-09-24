package leetcode.date_20180924;

import java.util.Arrays;
import java.util.Stack;
/**
 * 
 * 题目描述：下一个更大元素 II
 * 给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。数字 x 的下一个更大的元素是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。

	示例 1:
	
	输入: [1,2,1]
	输出: [2,-1,2]
	解释: 第一个 1 的下一个更大的数是 2；
	数字 2 找不到下一个更大的数； 
	第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
	
	题目链接：https://leetcode-cn.com/problems/next-greater-element-ii/description/
 * @author woniu
 *
 */
public class NextGreaterElementII {
	/**
	 * 这个方法并非我想出来的，只是看到有这个方式可以解决，感觉用的方式比较巧妙。
	 * 执行效率自然很高。
	 * @param nums
	 * @return
	 */
	public int[] nextGreaterElements(int[] nums) {
		int n = nums.length;
		int arr[] = new int[n];
		Arrays.fill(arr, -1);
		Stack<Integer> s = new Stack<>();
		for (int i = 0; i < 2 * n; i++) {  
			int num = nums[i % n];
			while (!s.isEmpty() && num > nums[s.peek()]) {//这个一旦找到一个合适的num,就查找前边的所有数字，直到不满足条件为止
				arr[s.pop()] = num;
			}
			if (i < n)
				s.push(i);
		}
		return arr;
	}
	
	/**
	 * 方法就是暴力破解，这个利用轮训的将整个数组遍历一圈，查找是否有合适的，如果有，则表明当前已经找到
	 * @param nums
	 * @return
	 */
	public int[] nextGreaterElements1(int[] nums) {
		int[] result = new int[nums.length];
		int len = nums.length;
		for (int i = 0; i < len; i++) {
			boolean isFind = false;
			for (int j = i + 1; j - i - 1 < len; j++) {//用于查找是否有满足条件的num
				if (nums[j % len] > nums[i]) {
					result[i] = nums[j % len];
					isFind = true;
					break;
				}
			}
			if (!isFind) {
				result[i] = -1;
			}
		}
		return result;
	}

	public static void main(String[] args) {
		int[] result = new NextGreaterElementII().nextGreaterElements(new int[] { 5, 4, 3, 7, 2 });
		System.out.println(Arrays.toString(result));
	}
}
