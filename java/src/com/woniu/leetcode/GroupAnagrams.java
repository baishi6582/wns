package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 
 * 题目描述：
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 * 示例:
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * 输出:
 * [
 *	  ["ate","eat","tea"],
 *	  ["nat","tan"],
 *	  ["bat"]
 *	]
 * 说明：
 * 所有输入均为小写字母。
 * 不考虑答案输出的顺序。
 * 
 * 解决思路：
 * 1、首先，自然还是想到了最笨的两层循环，或许已经成了定式思维了（工作中，总是if esle for循环了）
 * 2、就是当前的解决办法，无论怎么异构，排序后的字符串总是一致的，所以利用这个规则，结合map进行可以在一次循环中解决问题。
 * @author woniu
 *
 */
public class GroupAnagrams {
	 public List<List<String>> groupAnagrams(String[] strs) {

	        Map<String, List<String>> map = new HashMap<String, List<String>>(strs.length);
	        for(String str : strs){
	            char[] ss = str.toCharArray();
	            Arrays.sort(ss);
	            String newString = new String(ss);
	            List<String> list = map.get(newString);
	            if(null == list){
	                list = new ArrayList<String>();
	            }
	            list.add(str);
	            map.put(newString, list);
	        }
	        
	        return new ArrayList(map.values());
	    }
}
