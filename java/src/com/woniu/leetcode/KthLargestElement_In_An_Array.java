package leetcode;

import java.util.PriorityQueue;
/**
 *
   题目描述：
   在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。

   示例 1:

   输入: [3,2,1,5,6,4] 和 k = 2
   输出: 5
   示例 2:

   输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
   输出: 4
   说明:

   你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
   
   解题思路：主要是通过一个快排的思想来查找处于第K个位置的元素，其实并非需要一定对数据一定进行一个完成的排序，
   我们只要确定左边最大的数，已经有k-1个了，那么当前标尺的位置即为第K大了。
   
   题目链接：https://leetcode-cn.com/problems/kth-largest-element-in-an-array/description/
 * @author z00364813
 *
 */
public class KthLargestElement_In_An_Array {
	public int findKthLargest(int[] nums, int k) {
		return findKthLargest(nums, 0, nums.length - 1, k);
	}

	//通过一个优先级队列来实现，这个在leetcode中执行效率由于自己实现的执行效率。
	public int findKthLargestByQueue(int[] nums, int k) {
		if (k > nums.length || k < 1)
			return Integer.MIN_VALUE;
		PriorityQueue<Integer> pq = new PriorityQueue<>(k);
		for (int i = 0; i < k; i++) {
			pq.add(nums[i]);
		}

		for (int i = k; i < nums.length; i++) {
			if (nums[i] > pq.peek()) {
				pq.poll();
				pq.add(nums[i]);
			}
		}
		return pq.peek();
	}

	public int findKthLargest(int[] nums, int start, int end, int k) {
		int p = partition(nums, start, end);
		if (p + 1 == k) {
			return nums[p];
		} else if (p + 1 < k) {
			return findKthLargest(nums, p + 1, end, k);
		} else {
			return findKthLargest(nums, start, p - 1, k);
		}
	}

	public int partition(int[] nums, int start, int end) {
		int midVal = nums[start];
		while (start < end) {
			while (start < end && nums[end] <= midVal) {
				end--;
			}
			//这里是根据其他答案中做的一个优化点，避免了每次进行前后数据的交换。
			//这里相当于和2的位置，以及midVal进行了数据置换，只不过是最终的值是要在循环结束后才能确定
			nums[start] = nums[end];

			while (start < end && nums[start] >= midVal) {
				start++;
			}
			nums[end] = nums[start];//2
		}
		nums[start] = midVal;
		return start;
	}

	private void swap(int[] nums, int start, int end) {
		int temp = nums[start];
		nums[start] = nums[end];
		nums[end] = temp;
	}

	public static void main(String[] args) {
		int[] nums = { 3, 2, 3, 1, 2, 4, 5, 5, 6 };// {3,2,1,5,6,4};
		System.out.println(new KthLargestElement_In_An_Array().findKthLargestByQueue(nums, 4));
	}
}
