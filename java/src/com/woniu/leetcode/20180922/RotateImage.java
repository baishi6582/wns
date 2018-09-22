package leetcode.date_20180922;

/**
 * 
	题目链接：旋转图像
	给定一个 n × n 的二维矩阵表示一个图像。

	将图像顺时针旋转 90 度。
	
	说明：
	
	你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
	
	示例 1:
	
	给定 matrix = 
	[
	  [1,2,3],
	  [4,5,6],
	  [7,8,9]
	],
	
	原地旋转输入矩阵，使其变为:
	[
	  [7,4,1],
	  [8,5,2],
	  [9,6,3]
	]
	
	解题思路：这个题完全是一道数学题，虽然，我们还是没有想清楚具体是怎么转的，哎，还是太懒了，现在，不想去学习数学了。
	为啥数这是一个数学题呢？
	首先，顺时针旋转90度，其实就是，将整体做两次操作，先关于y=x对称做交换，然后，关于y=0对称。为啥说明搞明白呢？
	是因为，如果是数学题的话，我忘记了具体该怎么建这个坐标系，只不过通过推断，大概找到了转换规律而已。
	
	题目链接：https://leetcode-cn.com/problems/rotate-image/description/
 * @author woniu
 *
 */
public class RotateImage {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for(int i=0; i<n; i++) {
        	for(int j=0; j<n-i-1; j++) {
        		int temp = matrix[i][j];
        		matrix[i][j] = matrix[n-j-1][n-i-1];//注意这里，注意下标的位置，i与n-i-1的和为n-1, j与n-j-1的位置是n-1.
        		matrix[n-j-1][n-i-1] = temp;
        	}
        }
        
        for(int i=0; i<n/2; i++) {//注意对称，所以只要找一半数据即可
        	for(int j=0; j<n; j++) {
        		int temp = matrix[i][j];
        		matrix[i][j] = matrix[n-i-1][j];
        		matrix[n-i-1][j] = temp;
        	}
        }
    }
}
