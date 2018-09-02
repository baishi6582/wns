package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 
	题目描述：电话号码的字母组合
	
	给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
	
	给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
	
	
	
	示例:
	
	输入："23"
	输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
	说明:
	尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
	
	解题思路：这个题主要运用了递归的思想，对于一个给定的字符串"2554"，需要做的是每一步都找到该数字对应的所有可能的结果
	之后，在遍历下一个数字的可能性，直到得到的字符串与给定的字符串长度一致。
	
	题目链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/description/
 * @author z00364813
 *
 */
public class LetterCombinations_of_a_PhoneNumber {
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<String>();
        if(digits.length() == 0) {
        	return result;
        }
        
        backTracking(digits, "", 0, result);
        return result;
    }
    
    public void backTracking(String digits, String s, int begin, List<String> result) {
    	String[] ss = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    	//s用于记录当前的形成的字符串，如果已经和给出的数字长度相同，表明已经遍历完了所有的数字
    	if(s.length() >= digits.length()) {
    		result.add(s);
    		return;
    	}
    	
    	String letters = ss[digits.charAt(begin) - '0'];
    	for(int i=0; i<letters.length(); i++) {
    		backTracking(digits, s + letters.charAt(i), begin + 1, result);
    	}
    	
    }
    
    public static void main(String[] args) {
		System.out.println(new LetterCombinations_of_a_PhoneNumber().letterCombinations(""));
	}
}
