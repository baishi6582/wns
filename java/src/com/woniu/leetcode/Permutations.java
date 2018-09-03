package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 
	题目描述：全排列
	给定一个没有重复数字的序列，返回其所有可能的全排列。

	示例:
	
	输入: [1,2,3]
	输出:
	[
	  [1,2,3],
	  [1,3,2],
	  [2,1,3],
	  [2,3,1],
	  [3,1,2],
	  [3,2,1]
	]
	
	解题思路：其实这个题，想法很简单，但是在写代码时，发现，其实并不是那么容易入手，这个充分体现了自己的动手能力还是十分有限
	大概说一下做法吧，其实是参考网上的思路来做的。
	1, 2, 3
	首先，找到1，接下来找2，最后找3，然后呢？
	该到                这一次该是3，最后是2了。
	也就是需要有个回退操作，即找过后，就回退到上个位置，继续找下一个组合。
	
	题目链接：https://leetcode-cn.com/problems/permutations/description/
 * @author woniu
 *
 */
public class Permutations {

	public List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> list = new ArrayList<Integer>();
		findNext(nums, result, list);

		return result;
	}

	public void findNext(int[] nums, List<List<Integer>> result, List<Integer> list) {

		if (list.size() == nums.length) {
			result.add(new ArrayList<Integer>(list));//切记一定要重新new一个list,否则后边的回退会导致全部数据为空
		} else {
			for (int i = 0; i < nums.length; i++) {
				if (list.contains(nums[i])) {//注意题目中的不重复字样。
					continue;
				}
				list.add(nums[i]);
				findNext(nums, result, list);
				list.remove(list.size() - 1);//回退到初始上一个位置，继续查找下一个组合
			}
		}

	}

	public static void main(String[] args) {
		int[] nums = { 1, 2, 3 };
		System.out.println(new Permutations().permute(nums));
	}
}
