package leetcode;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
	题目描述：逆波兰表达式求值
	根据逆波兰表示法，求表达式的值。

	有效的运算符包括 +, -, *, / 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
	
	说明：
	
	整数除法只保留整数部分。
	给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
	示例 1：
	
	输入: ["2", "1", "+", "3", "*"]
	输出: 9
	解释: ((2 + 1) * 3) = 9
	示例 2：
	
	输入: ["4", "13", "5", "/", "+"]
	输出: 6
	解释: (4 + (13 / 5)) = 6
	示例 3：
	
	输入: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
	输出: 22
	解释: 
	  ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
	= ((10 * (6 / (12 * -11))) + 17) + 5
	= ((10 * (6 / -132)) + 17) + 5
	= ((10 * 0) + 17) + 5
	= (0 + 17) + 5
	= 17 + 5
	= 22
	
	解题思路：通过一个栈来存储数字，判断如果是计算符号的话，则从栈中出数进行计算。题目不难。
	
	题目链接：https://leetcode-cn.com/problems/evaluate-reverse-polish-notation/description/
 * @author woniu
 *
 */
public class EvaluateReversePolishNotation {
    public int evalRPN(String[] tokens) {
        
    	Pattern pattern = Pattern.compile("-?\\d+");
    	Matcher matcher = null;
    	Stack<String> stack = new Stack<String>();
    	
    	for(String token : tokens) {
    		matcher = pattern.matcher(token);
    		if(matcher.matches()) {
    			stack.push(token);
    		} else {
    			stack.push(getResult(stack.pop(), stack.pop(), token));
    		}
    		
    	}
    	
    	return Integer.parseInt(stack.pop());
    }
    
    public String getResult(String n2, String n1, String symbol) {
    	switch (symbol) {
		case "*":
			return String.valueOf(Integer.parseInt(n1) * Integer.valueOf(n2));
		case "+":
			return String.valueOf(Integer.parseInt(n1) + Integer.valueOf(n2));
		case "/":
			return String.valueOf(Integer.parseInt(n1) / Integer.valueOf(n2));
		case "-":
			return String.valueOf(Integer.parseInt(n1) - Integer.valueOf(n2));
		default:
			return "";
		}
    }
    
    public static void main(String[] args) {
    	String[] tokens = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
    	System.out.println(new EvaluateReversePolishNotation().evalRPN(tokens));
	}
}
