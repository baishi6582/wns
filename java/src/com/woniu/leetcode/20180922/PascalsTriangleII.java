package leetcode.date_20180922;

import java.util.ArrayList;
import java.util.List;

/**
 * 
	题目描述：杨辉三角 II
	
	给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
	
	
	
	在杨辉三角中，每个数是它左上方和右上方的数的和。
	
	示例:
	
	输入: 3
	输出: [1,3,3,1]
	
	解题思路：每次通过一个list存储结果，然后下一层通过上一层的list来计算。
	
	这个题多说一句规律。这个也是别人的答案，至少，我认为自己是想不出来的，所以就没去实现该方法，
	仅记录一下该规律吧。
	row ++;
    aa.add(1);
    for(int i=1;i<row;i++) {
    	sum = sum * (row - i)/i; //这个规律很巧，我验证了一下确实没有问题，但是不知其原理
    	aa.add((int)sum);
    }
    return aa;
	
	题目链接：https://leetcode-cn.com/problems/pascals-triangle-ii/description/
 * @author woniu
 *
 */
public class PascalsTriangleII {
    public List<Integer> getRow(int rowIndex) {
    	List<Integer> result = new ArrayList<Integer>();
    	result.add(1);
    	int len = result.size();
    	for(int i=0; i<rowIndex; i++) {
    		List<Integer> list = new ArrayList<Integer>();
    		list.add(1);
    		for(int j=0; j<len-1; j++) {//此循环用于计算出每一层的结果。
    			int m = result.get(j);
    			int n = result.get(j+1);
    			
    			list.add(m + n);
    		}
    		list.add(1);
    		result = list;
    		len = list.size();
    	}
    	
    	return result;
    }
    
    public static void main(String[] args) {
    	System.out.println(new PascalsTriangleII().getRow(4));
    }
}
