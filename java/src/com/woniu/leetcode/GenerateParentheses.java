package leetcode;

import java.util.ArrayList;
import java.util.List;
/**
 * 
	题目描述：括号生成
	给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。

	例如，给出 n = 3，生成结果为：
	
	[
	  "((()))",
	  "(()())",
	  "(())()",
	  "()(())",
	  "()()()"
	]
	
	解题思路：解决这个问题，首先要考虑用递归的方式解决。由于只有左括号和右括号，所以，最后两个值必定等于设定的n，同时大于0
	假定左括号和右括号分别使用left和right表示，则其初始值，必为n，最后保证其值并不会小于0，即结束条件为left < 0 || right < 0.
	如果左括号少于右括号也不满足我们的条件
	在left == 0 && right == 0 时，表明，此时已经完成了配对工作，为最终结果。
	如果left > 0， 表明需要添加左括号
	如果right > 0，表明需要添加右括号
	
	题目链接：https://leetcode-cn.com/problems/generate-parentheses/
 * @author z00364813
 *
 */
public class GenerateParentheses {

    public List<String> generateParenthesis(int n) {
    	List<String> list = new ArrayList<String>();
    	
    	generateParenthesis(n, n, n, "", list);
    	
    	return list;
    }
    
    public void generateParenthesis(int n, int left, int right, String out, List<String> result) {
    	
    	if(left < 0 || right < 0 || left > right) {
    		return;
    	}
    	if(left == 0 && right == 0 ) {
    		result.add(out);
    	}
    	if(left > 0) {
    		generateParenthesis(n, left - 1, right, out + "(", result);
    	}
    	if(right > 0) {
    		generateParenthesis(n, left, right - 1, out + ")", result);
    	}
    }
    
    public static void main(String[] args) {
		System.out.println(new GenerateParentheses().generateParenthesis(3));
	}
}
