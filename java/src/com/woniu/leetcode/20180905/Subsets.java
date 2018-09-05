package leetcode.date_20180905;

import java.util.LinkedList;
import java.util.List;

/**
 * 
	题目描述：子集
	给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。

	说明：解集不能包含重复的子集。
	
	示例:
	
	输入: nums = [1,2,3]
	输出:
	[
	  [3],
	  [1],
	  [2],
	  [1,2,3],
	  [1,3],
	  [2,3],
	  [1,2],
	  []
	]
	
	解题思路：通过递归的方式解决该问题，需要找到子集，且不重复，我的解题思路是，从第一个位置开始，依次向后查找。
	查找的结束条件为查找的位置已经大于了数组长度，则不再查找；begin > length;
	若干结果集result中没有对应的list，则将其保存在结果集中。
	之后依次循环下层进行遍历，然后递归的查询子一层，即可得到结果。
	
	题目链接：https://leetcode-cn.com/problems/subsets/description/
 * @author woniu
 *
 */
public class Subsets {
	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> result = new LinkedList<List<Integer>>();
		List<Integer> list = new LinkedList<Integer>();
		int length = nums.length;
		for (int i = 0; i < length; i++) {

			subsets(nums, i, length, result, list);
		}
		return result;
	}

	public void subsets(int[] nums, int begin, int length, List<List<Integer>> result, List<Integer> list) {
		if (begin > length) {
			return;
		}

		if (!result.contains(list)) {
			result.add(new LinkedList<Integer>(list));
		} 
		
		for (int i = begin; i < length; i++) {
			list.add(nums[i]);
			subsets(nums, i + 1, length, result, list);
			list.remove(list.size() - 1);
		}
		

	}

	public static void main(String[] args) {
		int[] nums = {  1,2,3};
		System.out.println(new Subsets().subsets(nums));
	}
}
