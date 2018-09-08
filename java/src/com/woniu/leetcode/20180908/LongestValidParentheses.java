package leetcode.date_20180908;

import java.util.Arrays;
import java.util.Stack;
/**
 * 
	题目描述：最长有效括号
	给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
	
	示例 1:
	
	输入: "(()"
	输出: 2
	解释: 最长有效括号子串为 "()"
	示例 2:
	
	输入: ")()())"
	输出: 4
	解释: 最长有效括号子串为 "()()"
	
	解题思路：见下文具体方法
	
	题目链接：https://leetcode-cn.com/problems/longest-valid-parentheses/description/
 * @author z00364813
 *
 */
public class LongestValidParentheses {
	/**
	 * 
	 * 该方法为首先想到的方法，解法比较笨重，首先是通过标记法，将匹配的括号进行标记为0.
	 * 无法匹配的左括号标记为1，右括号标记为2。
	 * 最后遍历数组，找到连续的0，即为匹配的长度。
	 * @param s
	 * @return
	 */
    public int longestValidParentheses1(String s) {
        int len = s.length();
        
        int[] nums = new int[len];
        
        for(int i=0; i<len; i++) {
        	char c = s.charAt(i);
        	if(c == '(') {
        		nums[i] = 1;
        	}
        	
        	if(c == ')') {
        		nums[i] = 2;
        		for(int j=i-1; j>=0; j--) {//向前边查找对应是否有左括号
        			if(nums[j] == 2) {//如果查到了右括号，表明没有可以匹配的，直接结束
        				break;
        			}
        			if(nums[j] == 1) {
        				nums[i] = 0;
        				nums[j] = 0;
        				break;
        			}
        		}
        	}
        }
        int maxLen = 0, tempLen = 0;
        for(int i=0; i<len; i++) {
        	if(nums[i] == 0) {
        		tempLen ++;
        	}
        	if(nums[i] != 0) {
        		if(maxLen < tempLen) {
        			maxLen = tempLen;
        		}
        		tempLen = 0;
        	}
        }
        
        System.out.println(Arrays.toString(nums) + "   " + maxLen);
    	return maxLen > tempLen ? maxLen : tempLen;
    }
    
    /**
     * 通用的方式，堆栈的方式解决括号匹配的问题。
     * 但是，此方法最初并没有想到，是通过网上的思路来实现的。
     * 确切说，不能是没有想到，而是没有想好怎么用。
     * @param s
     * @return
     */
    public int longestValidParentheses2(String s) {
        int maxLen = 0;
        Stack<Integer> stack = new Stack<>();
        int start = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {           
            	if(stack.isEmpty()) {
            		start = i + 1;//在堆栈为空时，记录当前的新的起始位置。
            	} else {
            		stack.pop();
            		maxLen = stack.isEmpty() ? Math.max(maxLen, i - start + 1) : Math.max(maxLen, i - stack.peek());
            	}
            }
        }
        return maxLen;
    }
    
    /**
     * 
     * 这是一个完全没有想到的思路，通过动态规划的方式解决此问题。思路比较有想法。
     * 动态规划，无非就是把大问题，划分为小问题来解决，后边的小问题依赖于前边的答案。依次得到最终答案。
     * 参考链接：https://blog.csdn.net/zzuzy/article/details/51223988
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        int len = s.length();
    	int[] dp = new int[len];
    	int maxLen = 0;
    	for(int i=1; i<len; i++) {
    		if(s.charAt(i) == ')') {
    			int j = i - 1 - dp[i-1];
    			if(j >= 0 && s.charAt(j) == '(') {
    				dp[i] = dp[i-1] + 2;
    				if(j - 1 >= 0) {
    					dp[i] += dp[j - 1];
    				}
    			}
    		}
    		if(maxLen < dp[i]) {
    			maxLen = dp[i];
    		}
    	}
    	
    	return maxLen;
    }
    
    public static void main(String[] args) {
		System.out.println(new LongestValidParentheses().longestValidParentheses(")()())"));
		System.out.println(new LongestValidParentheses().longestValidParentheses("(()"));
		System.out.println(new LongestValidParentheses().longestValidParentheses("))()(())))()()"));
		System.out.println(new LongestValidParentheses().longestValidParentheses(")()())"));
		
	}
}
