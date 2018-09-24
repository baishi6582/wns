package leetcode.date_20180923;

import java.util.Random;
import java.util.TreeMap;
/**
 * 
	题目描述：按权重随机选择
	给定一个正整数数组 w ，其中 w[i] 代表位置 i 的权重，请写一个函数 pickIndex ，它可以随机地获取位置 i，选取位置 i 的概率与 w[i] 成正比。

	说明:
	
	1 <= w.length <= 10000
	1 <= w[i] <= 10^5
	pickIndex 将被调用不超过 10000 次
	示例1:
	
	输入: 
	["Solution","pickIndex"]
	[[[1]],[]]
	输出: [null,0]
	示例2:
	
	输入: 
	["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
	[[[1,3]],[],[],[],[],[]]
	输出: [null,0,1,1,1,0]
	
	解题思路：此题太难以理解了。以至于做了很多边。
	大体思路是以第一个数为基准，后续数做偏移量计算，然后通过Random取随机值，得到的结果查看在当前哪个范围。
	如3,5,7,1，则表示为3,8,15,16，如果随机值为13，则首先找到15，然后获取index为2.此题通过前边学习到的TreeMap做的，
	亦可通过二分查找来实现。
	
	题目链接：https://leetcode-cn.com/problems/random-pick-with-weight/description/
 * @author woniu
 *
 */
public class RandomPickwithWeight {

	private TreeMap<Integer, Integer> map= new TreeMap<Integer, Integer>();
	private int len;
	private int sum;
    public RandomPickwithWeight(int[] w) {
    	len = w.length;
    	sum = w[0];
    	map.put(sum, 0);
    	for(int i=1; i<len; i++) {
    		sum += w[i];
    		map.put(sum, i);
    	}
    }
    
    public int pickIndex() {
    	int rand = (new Random().nextInt((sum)) % (sum)) + 1;
    	
    	return map.get(map.ceilingKey(rand));
    }
    
    public static void main(String[] args) {
//    	Solution solution = new Solution(new int[] {3, 5, 7, 10, 1});
//    	int n = 100;
//    	while(n-- > 0) {
//    		System.out.println(solution.pickIndex());
//    		
//    	}
    	for(int i=0; i<100; i++)
		System.out.println(new Random().nextInt((2)));
	}
}
