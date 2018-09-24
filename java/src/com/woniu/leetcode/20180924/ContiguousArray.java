package leetcode.date_20180924;

import java.util.HashMap;
import java.util.Map;
/**
 * 
 * 	题目描述：连续数组
 * 给定一个二进制数组, 找到含有相同数量的 0 和 1 的最长连续子数组。

	示例 1:
	
	输入: [0,1]
	输出: 2
	说明: [0, 1] 是具有相同数量0和1的最长连续子数组。
	示例 2:
	
	输入: [0,1,0]
	输出: 2
	说明: [0, 1] (或 [1, 0]) 是具有相同数量0和1的最长连续子数组。
	
	题目链接：https://leetcode-cn.com/problems/contiguous-array/description/
 * @author woniu
 *
 */
public class ContiguousArray {
	/**
	 * 解法比较奇特，将其中所有的0全部换成-1，这样就可以通过和来计算出0与1是1:1的关系了。
	 * 因为当然相等的和中间的数字和肯定为0
	 * @param nums
	 * @return
	 */
	public int findMaxLength(int[] nums) {
		int len = nums.length;
		for (int i = 0; i < len; i++) {
			if (nums[i] == 0) {
				nums[i] = -1;
			}
		}

		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		int sum = 0;
		int result = 0;
		for (int i = 0; i < len; i++) {
			sum += nums[i];
			if (sum == 0) {//当sum为0时，表明之前的数字，0和1的比例是1:1
				if (i >= result) {
					result = i + 1;
				}
			}
			if (!map.containsKey(sum)) {//没有得到过该结果，则存储下标信息
				map.put(sum, i);
				continue;
			}
			int tmp = map.get(sum);//计算相同结果的直接的距离，之所以这样是因为，既然能得到相同结果，则表明中间的必然为0，代表1和0属于1：1
			if (i - tmp > result) {
				result = i - tmp;
			}
		}

		return result;
	}

	public static void main(String[] args) {
		System.out.println(new ContiguousArray().findMaxLength(new int[] { 0}));
	}
}
