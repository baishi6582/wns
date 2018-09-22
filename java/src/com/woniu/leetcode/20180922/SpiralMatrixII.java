package leetcode.date_20180922;
/**
 * 
	题目描述：螺旋矩阵 II
	给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。

	示例:
	
	输入: 3
	输出:
	[
	 [ 1, 2, 3 ],
	 [ 8, 9, 4 ],
	 [ 7, 6, 5 ]
	]
	
	解题思路：此题并没有找到什么规律，就用了暴力破解，方法套路比较简单，就是一个一个数的填充，直到填充到n^2;
	
	题目链接；https://leetcode-cn.com/problems/spiral-matrix-ii/description/
 * @author woniu
 *
 */
public class SpiralMatrixII {
    public int[][] generateMatrix(int n) {
    	int[][] result = new int[n][n];
    	int i = 0, j = 0;
    	for(int num=1; num<=n * n; ) {
    		while(j<n && result[i][j] == 0) {//用来填充行，如1、2、3
    			result[i][j] = num ++;
    			j++;
    		}
    		j --;//是因为j已经数组越界了，所以必须回退一位
    		i ++;//之所以加一，是因为在result[0][2]的位置上已经填充了3，
    		while(i<n && result[i][j] == 0) {//用来填充右侧列，如4、5
    			result[i][j] = num ++;
    			i++;
    		}
    		i --;
    		j --;
    		while(j>=0 && result[i][j] == 0) {//填充6、7
    			result[i][j] = num ++;
    			j--;
    		}
    		j ++;
    		i --;
    		while(i>=0 && result[i][j] == 0) {//填充8
    			result[i][j] = num ++;
    			i --;
    		}
    		i ++;
    		j ++;
    	}
    	
    	return result;
    }
    
    public static void main(String[] args) {
    	int n = 0;
		int[][] result = new SpiralMatrixII().generateMatrix(n);
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				System.out.print(result[i][j] + " ");
			}
			System.out.println();
		}
	}
}
