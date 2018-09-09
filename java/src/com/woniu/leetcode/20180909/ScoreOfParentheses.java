package leetcode.date_20180909;

import java.util.Stack;
/**
 * 
	题目描述：括号的分数
	给定一个平衡括号字符串 S，按下述规则计算该字符串的分数：

	() 得 1 分。
	AB 得 A + B 分，其中 A 和 B 是平衡括号字符串。
	(A) 得 2 * A 分，其中 A 是平衡括号字符串。
	 
	
	示例 1：
	
	输入： "()"
	输出： 1
	示例 2：
	
	输入： "(())"
	输出： 2
	示例 3：
	
	输入： "()()"
	输出： 2
	示例 4：
	
	输入： "(()(()))"
	输出： 6
	
	解题思路：见下文，个人推崇迭代方式解决这个问题
	
	题目链接：https://leetcode-cn.com/problems/score-of-parentheses/description/
 * @author z00364813
 *
 */
public class ScoreOfParentheses {
	/**
	 * 
	 * 方法简单，但是不易想到。此题自己并没有解出来。参考了网上的解决方案进行解决的。
	 * 以(()(()))为例来说明
	 * 首先，默认该答案为0，需要将0压入栈
	 * （ ，压入0，stack中 0,0
	 * （，压入0，stack中0,0,0
	 *  ），需要完成计算了，stack中0,1
	 *  （压入0,栈中0,1,0
	 *  （压入0，栈中0,1,0,0
	 *  ）,需要计算，stack中0,1,1
	 *  ），需要计算，stack中0,3
	 *  ），需要计算，stack中6
	 * 
	 * @param S
	 * @return
	 */
    public int scoreOfParentheses1(String S) {
    	Stack<Integer> stack = new Stack<>();
    	stack.push(0);
    	for(char c : S.toCharArray()) {
    		if(c == '(') {
    			stack.push(0);
    		} else {
    			int v = stack.pop();//
    			int w = stack.pop();
    			stack.push(w + Math.max(2 * v, 1));
    		}
    	}
    	return stack.pop();
    }
    
    /**
     * 此方法，个人比较推崇，思路清晰明了。
     * 主要根据题目中描述来解题，
     * 对于(),直接返回1，
     * 对于(A),则进行递归，计算A ，然后结果*2
     * 对于AB，则分别计算A和B，然后计算A+B
     * @param S
     * @return
     */
    public int scoreOfParentheses(String S) {
    	if(S.equals("()")) {
    		return 1;
    	}
    	int count = 0;//用于判读何时是一个平衡括号
    	for(int i=0; i<S.length(); i++) {
    		char c = S.charAt(i);
    		if(c == '(') {
    			count ++;
    		} else {
    			count --;
    			if(count == 0) {
    				if(i == S.length() - 1) {
        				return 2 * scoreOfParentheses(S.substring(1, S.length() - 1));//表明最外层是一个括号，内部是一个整体，需要进行*2计算
        			} else {
        				//表明分为了两部分，需要完成A+B的操作
        				return scoreOfParentheses(S.substring(0, i + 1)) + scoreOfParentheses(S.substring(i + 1));
        			}
    			}
    		}
    	}
    	return 0;
    }
    public static void main(String[] args) {
		System.out.println(new ScoreOfParentheses().scoreOfParentheses("(()(()))"));
	}
}
