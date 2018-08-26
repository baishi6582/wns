package leetcode;
/**
 * 
   题目描述：
	 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性：
	
	每行的元素从左到右升序排列。
	每列的元素从上到下升序排列。
	示例:
	
	现有矩阵 matrix 如下：
	
	[
	  [1,   4,  7, 11, 15],
	  [2,   5,  8, 12, 19],
	  [3,   6,  9, 16, 22],
	  [10, 13, 14, 17, 24],
	  [18, 21, 23, 26, 30]
	]
	给定 target = 5，返回 true。
	
	给定 target = 20，返回 false。
	
	解决思路：从左下向右上搜索，这样的好处是，每次只会有一个方向，且只进不退。
	
	题目链接： https://leetcode-cn.com/problems/search-a-2d-matrix-ii/description/
 * @author z00364813
 *
 */
public class Search2DMatrix_II {
	//主要思路是从左下向右上搜索，这样的好处是，每次只会有一个方向，且只进不退。
	public boolean searchMatrix(int[][] matrix, int target) {
		if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return false;
		}
		int colLen = matrix[0].length;
		int rowIndex = matrix.length - 1;
		int colIndex = 0;
		while(true) {
			if(matrix[rowIndex][colIndex] == target) {
				return true;
			}else if(matrix[rowIndex][colIndex] < target){
				colIndex ++;
			}else {
				rowIndex --;
			}
			
			if(rowIndex < 0 || colIndex >= colLen) {
				return false;
			}
		}
	}
	
	public static void main(String[] args) {
		int[][] matrix ={{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};
		System.out.println(new Search2DMatrix_II().searchMatrix(matrix, 5));
	}
}
