package leetcode.date_20180919;

import java.util.ArrayList;
import java.util.List;
/**
 * 
	题目描述：组合总和
	给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

	candidates 中的数字可以无限制重复被选取。
	
	说明：
	
	所有数字（包括 target）都是正整数。
	解集不能包含重复的组合。 
	示例 1:
	
	输入: candidates = [2,3,6,7], target = 7,
	所求解集为:
	[
	  [7],
	  [2,2,3]
	]
	示例 2:
	
	输入: candidates = [2,3,5], target = 8,
	所求解集为:
	[
	  [2,2,2,2],
	  [2,3,3],
	  [3,5]
	]
	
	解题思路：递归方式解题。难度不大。
	
	题目链接：https://leetcode-cn.com/problems/combination-sum/description/
 * @author woniu
 *
 */
public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
    	List<List<Integer>> result = new ArrayList<List<Integer>>();
    	List<Integer> list = new ArrayList<Integer>();
    	combinationSum(candidates, target, candidates.length-1, list, result);
    	
    	return result;
    }
    
    public void combinationSum(int[] candidates, int target, int begin,List<Integer> list, List<List<Integer>> result) {
    	for(int i=begin; i>=0; i--) {
    		if(candidates[i] == target) {
    			list.add(candidates[i]);
    			List<Integer> temp = new ArrayList<Integer>(list);
    			result.add(temp);
    		} else if(candidates[i] < target) {
    			list.add(candidates[i]);
    			combinationSum(candidates, target - candidates[i], i, list, result);//需要注意的是减掉已经匹配的数字
    		} else {
    			continue;
    		}
    		list.remove(list.size() - 1);//这里切记清理掉上一层的结果
    	}
    }
    
    public static void main(String[] args) {
		System.out.println(new CombinationSum().combinationSum(new int[] {2,3,6,7}, 2));
	}
}
