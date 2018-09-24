package leetcode.date_20180923;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
 * 
	题目描述：两个数组的交集 II
	给定两个数组，编写一个函数来计算它们的交集。

	示例 1:
	
	输入: nums1 = [1,2,2,1], nums2 = [2,2]
	输出: [2,2]
	示例 2:
	
	输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
	输出: [4,9]
	说明：
	
	输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。
	我们可以不考虑输出结果的顺序。
	进阶:
	
	如果给定的数组已经排好序呢？你将如何优化你的算法？
	如果 nums1 的大小比 nums2 小很多，哪种方法更优？
	如果 nums2 的元素存储在磁盘上，磁盘内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
	
	解题思路：见下文。两种解法，其中一个解法是按照排序好后，做出的。虽然是一道简单题， 不用暴力破解的话，还是比较有趣的。
	发现简单题之所以叫简单，是因为允许你使用暴力破解
	
	题目链接：https://leetcode-cn.com/problems/intersection-of-two-arrays-ii/description/
 * @author woniu
 *
 */
public class IntersectionOfTwoArraysII {
	
	//先对两个数组进行排序，排序后，则可以比较便利的处理了。
	public int[] intersect(int[] nums1, int[] nums2) {
		if (nums1.length > nums2.length) {//主要用于保证nums1当前是较小长度的，这样再初始化result存储空间时，不必太长
			return intersect(nums2, nums1);
		}

		Arrays.sort(nums1);
		Arrays.sort(nums2);

		int len1 = nums1.length, len2 = nums2.length;
		int i = 0, j = 0, k = 0;
		int[] result = new int[len1];
		while (i < len1 && j < len2) {

			int isZore = nums1[i] < nums2[j] ? -1 : (nums1[i] > nums2[j] ? 1 : 0);

			switch (isZore) {
			case -1:
				i++;//由于已经排序了。所以，当nums1[i] < nums2[j]时， nums1需要找更大的值作比较
				break;
			case 1:
				j++;//由于已经排序了。所以，当nums1[i] > nums2[j]时， nums1需要找更大的值作比较
				break;
			case 0:
				result[k++] = nums1[i];
				i++;
				j++;
				break;
			}
		}

		return Arrays.copyOf(result, k);
	}

	public static void main(String[] args) {
		int[] result = new IntersectionOfTwoArraysII().intersect(new int[] { 1, 2, 2, 1 }, new int[] { 2, 2 });
		System.out.println(Arrays.toString(result));
	}

	// 传说中的暴力破解的方式，不过也AC了
	public int[] intersect1(int[] nums1, int[] nums2) {
		List<Integer> list = new ArrayList<Integer>();//用于存储结果
		Set<Integer> set = new HashSet<Integer>();//用于存储下标，防止下标重复使用
		int len1 = nums1.length, len2 = nums2.length;

		for (int i = 0; i < len1; i++) {
			for (int j = 0; j < len2; j++) {
				if (nums1[i] == nums2[j] && !set.contains(j)) {
					list.add(nums2[j]);
					set.add(j);
					break;
				}
			}
		}

		int[] result = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			result[i] = list.get(i);
		}
		return result;
	}

}
