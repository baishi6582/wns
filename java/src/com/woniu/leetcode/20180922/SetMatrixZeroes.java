package leetcode.date_20180922;

import java.util.HashSet;
import java.util.Set;
/**
 * 
	题目描述：矩阵置零
	给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。

	示例 1:
	
	输入: 
	[
	  [1,1,1],
	  [1,0,1],
	  [1,1,1]
	]
	输出: 
	[
	  [1,0,1],
	  [0,0,0],
	  [1,0,1]
	]
	示例 2:
	
	输入: 
	[
	  [0,1,2,0],
	  [3,4,5,2],
	  [1,3,1,5]
	]
	输出: 
	[
	  [0,0,0,0],
	  [0,4,5,0],
	  [0,3,1,0]
	]
	
	解题思路：见下文
	
	题目链接：https://leetcode-cn.com/problems/set-matrix-zeroes/description/
 * @author woniu
 *
 */
public class SetMatrixZeroes {
	/**
	 * 
	 * 该方法需要一个一位数组用于存储列index，之所以这样，是因为，当我们发现了i,j位置后，如果同时刷新第i行和第j列的话
	 * 会影响后边的判断，所以，每次遍历我们先将行的数据信息改为最中结果，列信息先存储，之后再进行刷新。
	 * @param matrix
	 */
	public void setZeroes1(int[][] matrix) {
		boolean[] bool = new boolean[matrix[0].length];//用于记录列的位置信息
		
		for(int i=0; i<matrix.length; i++) {
			boolean isRow = false;
			for(int j=0; j<matrix[0].length; j++) {
				if(matrix[i][j] == 0) {
					isRow = true;//表明当前行是需要全部改为0的
					bool[j] = true;//之所以不退出循环，是因为，还需要判断后边的列
				}
			}
			
			if(isRow) {//将行信息全部改为0
				for(int j=0; j<matrix[i].length; j++) {
					matrix[i][j] = 0;
				}
			}
		}
		//将列信息改为0
		for(int i=0; i<bool.length; i++) {
			if(bool[i]) {
				for(int j=0; j<matrix.length; j++) {
					matrix[j][i] = 0;
				}
			}
		}
		
	}

	/**
	 * 刚方法，有些笨重，通过一边寻找，查找出，0的位置信息，将其存储在一个set中
	 * 之后，在通过set中获取i/j，将i行和j列全部改为0
	 * @param matrix
	 */
	public void setZeroes(int[][] matrix) {
		Set<String> set = new HashSet<String>();
		int m = matrix.length;
		int n = matrix[0].length;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (matrix[i][j] == 0) {
					set.add(i + "," + j);
				}
			}
		}

		for (Object str : set.toArray()) {
			String[] ss = String.valueOf(str).split(",");
			setZerores(Integer.parseInt(ss[0]), Integer.parseInt(ss[1]), m, n, matrix);

		}

	}

	public void setZerores(int i, int j, int m, int n, int[][] matrix) {
		for (int a = 0; a < n; a++) {
			matrix[i][a] = 0;
		}

		for (int a = 0; a < m; a++) {
			matrix[a][j] = 0;
		}
	}

	public static void main(String[] args) {
		int[][] nums = {{1}, {0}};
		new SetMatrixZeroes().setZeroes(nums);
		for(int i=0; i<nums.length; i++) {
			for(int  j=0; j<nums[0].length; j++) {
				System.out.println(nums[i][j]);
			}
		}
	}
}
