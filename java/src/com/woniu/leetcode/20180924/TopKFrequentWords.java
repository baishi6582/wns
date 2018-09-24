package leetcode.date_20180924;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
/**
 * 
 * 题目描述：前K个高频单词
 * 给一非空的单词列表，返回前 k 个出现次数最多的单词。

	返回的答案应该按单词出现频率由高到低排序。如果不同的单词有相同出现频率，按字母顺序排序。
	
	示例 1：
	
	输入: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
	输出: ["i", "love"]
	解析: "i" 和 "love" 为出现次数最多的两个单词，均为2次。
	    注意，按字母顺序 "i" 在 "love" 之前。
	 
	
	示例 2：
	
	输入: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
	输出: ["the", "is", "sunny", "day"]
	解析: "the", "is", "sunny" 和 "day" 是出现次数最多的四个单词，
	    出现次数依次为 4, 3, 2 和 1 次。
	    
	题目链接：https://leetcode-cn.com/problems/top-k-frequent-words/description/
 * @author woniu
 *
 */
public class TopKFrequentWords {
	/**
	 * 
	 * 通过一个map存储信息，然后，将其转换为一个List，通过将实现一个Comparator来进行排序，难度不大，重点是实现Comparator.
	 * @param words
	 * @param k
	 * @return
	 */
    public List<String> topKFrequent(String[] words, int k) {
        TreeMap<String, Integer> map = new TreeMap<String, Integer>();
        for(int i=0; i<words.length; i++) {
        	map.put(words[i], map.getOrDefault(words[i], 0) + 1);
        }
        List<Map.Entry<String, Integer>> entryList = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());

        Collections.sort(entryList, new Comparator<Map.Entry<String, Integer>>() {

			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				
				return o1.getValue() < o2.getValue()  ? 1  : 
					(o1.getValue() > o2.getValue() ? -1 : 
						(o1.getKey().compareTo(o1.getKey())));
			}
		});
        
        List<String> list = new ArrayList<String>();
        for(int i=0; i<k; i++) {
        	list.add(entryList.get(i).getKey());
        }
    	return list;
    }
    
    public static void main(String[] args) {
    	System.out.println(new TopKFrequentWords().topKFrequent(new String[] {"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"}, 4));
    }
}
