package leetcode.date_20180912;

import java.util.Arrays;
/**
 * 
 * 
	题目描述：下一个排列
	
	实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。

	如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
	
	必须原地修改，只允许使用额外常数空间。
	
	以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
	1,2,3 → 1,3,2
	3,2,1 → 1,2,3
	1,1,5 → 1,5,1
	
	
	解题思路：从后向前找，找到存在顺序排列的数字，然后将其交换位置，即后一个数字。
	如：23451 
	第一步，4后边没有顺序排列的数字，
	第二步，3后边有比它的4和5，则需要选择最小的一个与其进行交换，然后将其后的数字进行顺序排列即可。
	
	所以此题重点在于排序。明天将排序算法复习一下。
	
	题目链接：https://leetcode-cn.com/problems/next-permutation/description/
 * @author woniu
 *
 */
public class NextPermutation {
	public void nextPermutation(int[] nums) {
		int len = nums.length;
		for (int i = len - 2; i >= 0; i--) {
			int maxMin = Integer.MAX_VALUE;
			int j = i + 1;
			int index = j;
			for (; j < len; j++) {
				if (nums[j] > nums[i] && nums[j] < maxMin) {
					maxMin = nums[j];
					index = j;
				}
			}
			if (maxMin < Integer.MAX_VALUE) {
				nums[index] = nums[i];
				nums[i] = maxMin;
				sort(nums, i + 1, len - 1);
				return;
			}
		}

		sort(nums, 0, len - 1);
	}

	public static void sort(int[] a, int low, int high) {
		if (low >= high)
			return;
		int i = low;
		int j = high;
		int key = a[i];
		while (i < j) {
			while (i < j && a[j] >= key) {
				j--;
			}
			if (i < j) {
				a[i++] = a[j];
			}

			while (i < j && a[i] <= key) {
				i++;
			}
			if (i < j) {
				a[j--] = a[i];
			}

		}
		a[i] = key;
		sort(a, low, i - 1);
		sort(a, i + 1, high);
	}

	public static void moveArrayElement(int[] array, int k) {
		int length = array.length;
		// 右移newk + n * length个位置，和右移newk个位置效果是一样的
		int newk = k % length;
		int temp = 0;
		for (int i = 0; i < newk; i++) {
			temp = array[length - 1];
			for (int j = length - 2; j >= 0; j--) {
				array[j + 1] = array[j];
			}
			array[0] = temp;
		}

	}

	public static void main(String[] args) {
		 int[] nums1 = {1, 3, 2};
		 new NextPermutation().nextPermutation(nums1);
		 System.out.println(Arrays.toString(nums1));//213
		
		 int[] nums2 = {1, 2, 3};
		 new NextPermutation().nextPermutation(nums2);
		 System.out.println(Arrays.toString(nums2));//132
		
		 int[] nums3 = {3, 1, 2};
		 new NextPermutation().nextPermutation(nums3);
		 System.out.println(Arrays.toString(nums3));//321
		
		int[] nums4 = { 3, 2, 1 };
		new NextPermutation().nextPermutation(nums4);
		System.out.println(Arrays.toString(nums4));// 123

		int[] nums5 = { 2, 1, 3 };
		new NextPermutation().nextPermutation(nums5);
		System.out.println(Arrays.toString(nums5));// 231

		int[] nums6 = { 2, 3, 1 };
		new NextPermutation().nextPermutation(nums6);// 312
		System.out.println(Arrays.toString(nums6));
	}
}
