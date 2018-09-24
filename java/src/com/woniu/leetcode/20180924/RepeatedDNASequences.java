package leetcode.date_20180924;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * 题目描述：重复的DNA序列
 * 所有 DNA 由一系列缩写为 A，C，G 和 T 的核苷酸组成，例如：“ACGAATTCCG”。在研究 DNA 时，识别 DNA 中的重复序列有时会对研究非常有帮助。
	
	编写一个函数来查找 DNA 分子中所有出现超多一次的10个字母长的序列（子串）。
	
	示例:
	
	输入: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
	
	输出: ["AAAAACCCCC", "CCCCCAAAAA"]
	
	题目链接：https://leetcode-cn.com/problems/repeated-dna-sequences/description/
 * @author woniu
 *
 */
public class RepeatedDNASequences {
	/**
	 * 其实这个题，解法上没有难度，毕竟只要一次遍历即可解决。
	 * 从网上看了其他人的解法，方式上没有太多变化。重点，他们用了数字表示，这样可以节省存储空间，确实是个不错的想法。
	 * @param s
	 * @return
	 */
    public List<String> findRepeatedDnaSequences(String s) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        
        int len = s.length();
        List<String> result = new ArrayList<String>();
        for(int i=0; i<len-9; i++) {
        	String str = s.substring(i, i+10);
        	if(map.containsKey(str) && !result.contains(str)) {
        		result.add(str);
        	} else {
        		map.put(str, 1);
        	}
        }
    	
    	return result;
    }
}
