package leetcode.date_20180908;

import java.util.Stack;

/**
 * 
	题目描述： 有效的括号	
			
	给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
	
	有效字符串需满足：
	
	左括号必须用相同类型的右括号闭合。
	左括号必须以正确的顺序闭合。
	注意空字符串可被认为是有效字符串。
	
	示例 1:
	
	输入: "()"
	输出: true
	示例 2:
	
	输入: "()[]{}"
	输出: true
	示例 3:
	
	输入: "(]"
	输出: false
	示例 4:
	
	输入: "([)]"
	输出: false
	示例 5:
	
	输入: "{[]}"
	输出: true
	
	解题思路：通常遇到括号匹配的问题，就直接使用堆栈，先进后出，用来做括号匹配，再好不过了
	
	题目链接：https://leetcode-cn.com/problems/valid-parentheses/description/
 * @author z00364813
 *
 */

public class ValidParentheses {
    public boolean isValid(String s) {
        
    	Stack<Character> stack = new Stack<Character>();
    	int len = s.length();
    	for(int i=0; i<len; i++) {
    		char c = s.charAt(i);
    		if( c == '(' ||  c == '[' || c == '{') {
    			stack.push(c);
    			continue;
    		}
    		if(stack.size() == 0) {
    			return false;
    		}
    		char pc = stack.pop();
    		
    		if(c == ')' && pc != '(') {
    			return false;
    		}
    		if(c == ']' && pc != '[') {
    			return false;
    		}
    		if(c == '}' && pc != '{') {
    			return false;
    		}
    	}
    	
    	if(stack.size() != 0) {
    		return false;
    	}
    	
    	return true;
    }
    
    public static void main(String[] args) {
		System.out.println(new ValidParentheses().isValid("()[]{}"));
		System.out.println(new ValidParentheses().isValid("(()[]{}"));
		System.out.println(new ValidParentheses().isValid("())[]{}"));
		System.out.println(new ValidParentheses().isValid("([)[{}"));
		System.out.println(new ValidParentheses().isValid("{[()]}"));
	}
}
