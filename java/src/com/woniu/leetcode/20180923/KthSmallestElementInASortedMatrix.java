package leetcode.date_20180923;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * 
 * 
	题目描述：有序矩阵中第K小的元素
	给定一个 n x n 矩阵，其中每行和每列元素均按升序排序，找到矩阵中第k小的元素。
	请注意，它是排序后的第k小元素，而不是第k个元素。
	
	示例:
	
	matrix = [
	   [ 1,  5,  9],
	   [10, 11, 13],
	   [12, 13, 15]
	],
	k = 8,
	
	返回 13。
	
	
	解题思路：首先，我没有太好的办法，通过暴力破解的方式解决，发现最后的两个case过不去，没有办法，想不出更好的版本。所以去网上找寻结果。
	看到了基于二分查找的方式，也就是该二维矩阵的0,0位置最小，n-1，n-1的位置最大，通过中间值，不断尝试判断，直到left和right相等时， 表明此时这个就是我们最终的结果。
	
	题目链接：https://leetcode-cn.com/problems/kth-smallest-element-in-a-sorted-matrix/description/
 * @author woniu
 *
 */
public class KthSmallestElementInASortedMatrix {
	//参考链接：https://blog.csdn.net/qingxili/article/details/79890026
	public int kthSmallest(int[][] matrix, int k) {
		
		int n = matrix.length;
		int low = matrix[0][0];
		int high = matrix[n-1][n-1];
		
		while(low < high) {
			int mid = low + (high - low) / 2;
			int j = n - 1;
			int count = 0;
			for(int i=0; i<n; i++) {
				while(j >= 0 && matrix[i][j] > mid) {
					j --;//注意这个j，并没有每次初始化，是因为，同一层的j是有序的，i层的j+1大于mid，则i+1层的j+1同样大于mid，没有必要判断了。
				}
				count += j + 1;
			}
			
			if(count < k) {
				low = mid + 1;
			} else {
				high = mid;
			}
		}
		return low;
	}

	public static void main(String[] args) {
		System.out.println(new KthSmallestElementInASortedMatrix().kthSmallest(new int[][] {{1, 2}, {1, 3}}, 3));
	}
	
	public int kthSmallest1(int[][] matrix, int k) {

		List<Integer> list = new ArrayList<Integer>(k);
		int n = matrix.length;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (list.size() < k) {
					list.add(matrix[i][j]);
					Collections.sort(list);
				} else if (list.get(k - 1) > matrix[i][j]) {
					list.remove(k - 1);
					list.add(matrix[i][j]);
				}
				if (list.size() == k) {
					Collections.sort(list);
				}
			}
		}

		return list.get(k - 1);
	}
}
