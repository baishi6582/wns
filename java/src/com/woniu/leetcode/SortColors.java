package leetcode;

import java.util.Arrays;
/**
 * 
      题目描述：
      给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。

     此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
   
     注意:
     不能使用代码库中的排序函数来解决这道题。
   
     示例:
   
     输入: [2,0,2,1,1,0]
     输出: [0,0,1,1,2,2]
     
     解题思路：
              通常的最容易想到的方法为sortColors1，其实问题的本质，就是排序而已，两层遍历，即可解决此问题。
              但是执行效率不高。方法二有点特殊问题特殊处理的味道，其不是我自己想出来的，而是看的网上的解题思路之后完成的。
   
     题目链接：https://leetcode-cn.com/problems/sort-colors/description/
   
 * @author woniu
 *
 */
public class SortColors {
	public void sortColors(int[] nums) {
		int start = -1;//通过start来控制0的位置，所有0均需要存在最左边
		int end = nums.length;//通过end守住2的位置，所有的2均需要存储在最右边。
		for(int i=0; i<end;) {
			if(nums[i] == 0) {
				nums[i] = nums[++start];//进行位置的置换，由于已经知道此处置换后，必然为0，所以也无需通过中间值置换
				nums[start] = 0;
				i++;
			}else if(nums[i] == 2) {//关于此处为啥没有进行i++，主要是由于置换后的数字我们需要再对其进行一次校验
				nums[i] = nums[--end];
				nums[end] = 2;
			}else if(nums[i] == 1) {//1的位置处在中间，不用置换。
				i++;
			}
			
		}
	}

	public void swap(int[] nums, int i, int j) {
		int temp = nums[j];
		nums[j] = nums[i];
		nums[i] = temp;
	}


	private void sortColors1(int[] nums) {
		int len = nums.length;
		for(int i=0; i<len; i++) {
			for(int j=i+1; j<len; j++) {
				if(nums[i] > nums[j]) {
					swap(nums, i, j);
				}
			}
		}
	}
	
	
	
	public static void main(String[] args) {
		int[] nums1 = {1,2,0};//
		new SortColors().sortColors(nums1);
		System.out.println(Arrays.toString(nums1));
		
		int[] nums2 = {2,0,2,1,1,0};
		new SortColors().sortColors(nums2);
		System.out.println(Arrays.toString(nums2));
	}
}
