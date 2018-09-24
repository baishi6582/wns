package leetcode.date_20180923;

import java.util.TreeMap;
/**
 * 
	题目描述：寻找右区间
	给定一组区间，对于每一个区间 i，检查是否存在一个区间 j，它的起始点大于或等于区间 i 的终点，这可以称为 j 在 i 的“右侧”。
	
	对于任何区间，你需要存储的满足条件的区间 j 的最小索引，这意味着区间 j 有最小的起始点可以使其成为“右侧”区间。如果区间 j 不存在，则将区间 i 存储为 -1。最后，你需要输出一个值为存储的区间值的数组。
	
	注意:
	
	你可以假设区间的终点总是大于它的起始点。
	你可以假定这些区间都不具有相同的起始点。
	示例 3:
	
	输入: [ [1,4], [2,3], [3,4] ]
	输出: [-1, 2, -1]
	
	解释:对于区间[1,4]和[3,4]，没有满足条件的“右侧”区间。
	对于[2,3]，区间[3,4]有最小的“右”起点。
	
	解题思路：个人通过暴力破解解决的该问题，之后，发现解决答案中有更好的方式，就是通过TreeMap，这个是值得学习的一道题。
	
	题目链接：https://leetcode-cn.com/problems/find-right-interval/description/
	
 * @author woniu
 *
 */
public class FindRightInterval {
	//该方式解决问题，绝对值得学习了。并不是说该法多牛逼，而是其中关于treemap的方式，一直没有学习过treemap，今天发现这个绝对是个好工具
	public int[] findRightInterval(Interval[] intervals) {
		TreeMap<Integer, Integer> map = new TreeMap<>();
		int[] res = new int[intervals.length];
		for (int i = 0; i < intervals.length; i++)
			map.put(intervals[i].start, i);
		for (int i = 0; i < intervals.length; i++) {
			Integer key = map.ceilingKey(intervals[i].end);//方法lowerEntry、floorEntry、ceilingEntry和higherEntry分别返回与小于、小于等于、大于等于、大于给定键的键关联的Map.Entry对象，如果不存在这样的键，则返回null。
			res[i] = key != null ? map.get(key) : -1;
		}
		return res;
	}
	//暴力破解
	public int[] findRightInterval1(Interval[] intervals) {
		int n = intervals.length;
		int[] result = new int[n];

		for(int i=0; i<n; i++) {
			int index = -1;
			int diff = Integer.MAX_VALUE;
			for(int j=0; j<n; j++) {
				if(intervals[i].end <= intervals[j].start) {
					if(intervals[j].start - intervals[i].end < diff) {//为了选出最近的那个区间
						diff = intervals[j].start - intervals[i].end;
						index = j;
					}
					if(intervals[j].start - intervals[i].end == 0){
						break;
					}
				}
			}
			result[i] = index;
		}
		
		return result;
	}
}

class Interval {
	int start;
	int end;

	Interval() {
		start = 0;
		end = 0;
	}

	Interval(int s, int e) {
		start = s;
		end = e;
	}
}
