package leetcode.date_20180916;

import java.util.HashMap;
/**
 * 
	题目描述：四数相加 II
	给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0。

	为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，且 0 ≤ N ≤ 500 。所有整数的范围在 -228 到 228 - 1 之间，最终结果不会超过 231 - 1 。
	
	例如:
	
	输入:
	A = [ 1, 2]
	B = [-2,-1]
	C = [-1, 2]
	D = [ 0, 2]
	
	输出:
	2
	
	解释:
	两个元组如下:
	1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
	2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
	
	解题思路：最初只想到了暴力破解，O(n^4)复杂度，果然，超时了。然后再晚上找到了现在的解答思路。
	方法很简单，却没想到。就是将整个计算过程分为了两部分，先算A+B的所有结果，将其存在map中，
	之后再算C+D的结果取反，查看是否在map中存在。
	参考链接：https://blog.csdn.net/whdAlive/article/details/80459522
	
	题目链接；https://leetcode-cn.com/problems/4sum-ii/description/
	
 * @author woniu
 *
 */
public class _4SumII {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        
    	int count = 0;
    	HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    	for(int i=0; i<A.length; i++) {
    		for(int j=0; j<B.length; j++) {
    			int sum = A[i] + B[j];
    			map.put(sum, map.getOrDefault(sum, 0) + 1);
    		}
    	}
    	
    	for(int i=0; i<C.length; i++) {
    		for(int j=0; j<D.length; j++) {
    			int sum = - C[i] - D[j];
    			count += map.getOrDefault(sum, 0);
    		}
    	}
    	
    	return count;
    }
    
    public static void main(String[] args) {
	}
}
