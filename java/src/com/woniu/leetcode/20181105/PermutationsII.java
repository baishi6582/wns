package leetcode.date_20181105;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * 
 * 题目描述：
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。

	示例:
	
	输入: [1,1,2]
	输出:
	[
	  [1,1,2],
	  [1,2,1],
	  [2,1,1]
	]
 * 题目链接：https://leetcode-cn.com/problems/permutations-ii/
 * @author woniu
 *
 */
public class PermutationsII {
    public List<List<Integer>> permuteUnique(int[] nums) {
		List<List<Integer>> result = method2(nums);
		
        return result;
    }
    
	private List<List<Integer>> method2(int[] nums) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> list = new ArrayList<Integer>();
		boolean[] isVisit = new boolean[nums.length];
		Arrays.sort(nums);
		dfs(nums, result, list, isVisit);
		return result;
	}
    
    public void dfs(int[] nums, List<List<Integer>> result, List<Integer> list, boolean[] isVisit) {
    	if(list.size() == nums.length) {
    		result.add(new ArrayList<Integer>(list));
    	} else {
    		for(int i=0; i<nums.length; i++) {
    			//已经被访问的不再访问
    			//没有被访问的，且与前一个数字相同的，同时，前一个数字没有被访问，则表明前边已经有过相同顺序的结果了
    			//如2,1,1由于位置2上的1与位置1上的1相同，如果位置1上的1标记为未被访问过，证明其为倒退回去后，再次走到了位置2而已
    			if(isVisit[i] || (i>0 && !isVisit[i-1] && nums[i] == nums[i-1])) {
    				continue;
    			}
    			
    			isVisit[i] = true;//标记已经被访问的位置
    			list.add(nums[i]);
    			dfs(nums, result, list, isVisit);
    			list.remove(list.size() - 1);
    			isVisit[i] = false;
    		}
    	}
    }
    
    /**
     * 执行效率不高，主要是由于进行了重复的计算，同时增加了排重处理
     * @param nums
     * @return
     */
	private List<List<Integer>> method1(int[] nums) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> list = new ArrayList<Integer>();
		List<Integer> indexList = new ArrayList<Integer>();//通过链表记录位置，有点大财小用
		findNext(nums, result, list, indexList);
		return result;
	}
    
    public void findNext(int[] nums, List<List<Integer>> result, List<Integer> list, List<Integer> indexList){
    	
    	if(list.size() == nums.length) {
    		if(!result.contains(list)) {//这里增加了排重处理，增加了计算的时长
    			result.add(new ArrayList<Integer>(list));
    		}
    	} else {
    		for (int i = 0; i < nums.length; i++) {
    			//通过下标来控制其不重复，仅能保证的是在同一次计算过程中，一个位置上的数被使用一次，并不能保证相同的数的重复排序。
				if (indexList.contains(i)) {
					continue;
				}
				list.add(nums[i]);
				indexList.add(i);
				findNext(nums, result, list, indexList);
				list.remove(list.size() - 1);//回退到初始上一个位置，继续查找下一个组合
				indexList.remove(indexList.size() - 1);
			}
    	}
    	
    }
    
	public static void main(String[] args) {
		int[] nums = { 1,1,2 };
		System.out.println(new PermutationsII().permuteUnique(nums));
	}
}
