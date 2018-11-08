package leetcode.date_20181108;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
/**
 * 
 * 题目描述：前K个高频元素
 * 
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。

    示例 1:
    
    输入: nums = [1,1,1,2,2,3], k = 2
    输出: [1,2]
    示例 2:
    
    输入: nums = [1], k = 1
    输出: [1]
    说明：
    
    你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
    你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
    
    解题思路：见下文
    
    题目链接：https://leetcode-cn.com/problems/top-k-frequent-elements/
    
 * @author woniu
 *
 */
public class Top_K_FrequentElements
{
    /**
     * 主要基于map进行存储，之后，通过一个list对于其中的entry按照value进行排序，难点在于实现comparator接口
     * @param nums
     * @param k
     * @return
     */
    public List<Integer> topKFrequent(int[] nums, int k) {
     
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        
        for(int i=0; i<nums.length; i++){
            if(map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]) + 1);
                
            } else {
                map.put(nums[i], 1);
            }
        }
        
        List<Entry<Integer, Integer>> list = new ArrayList<Entry<Integer, Integer>>();
        list.addAll(map.entrySet());
        
        Collections.sort(list, new Comparator<Entry<Integer, Integer>>()
        {

            @Override
            public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2)
            {
                return o2.getValue() -o1.getValue();
            }
        });
        
        List<Integer> result = new ArrayList<Integer>();
        for(int i=0; i<k; i++){
            result.add(list.get(i).getKey());
        }
        
        return result;
    }
    
    /**
     * 关键步骤均有注释，大概思路如下
     * 1、先记录出当前结数组中的最大最小值
     * 2、统计每个数字出现的次数，通过与最小值做差进行存储
     * 3、求出当前出现次数最高的结果
     * 4、将出现次数存储在数组当中，之后求出满足K的最小次数
     * 5、统计出大于等于最小次数的结果值
     * @param nums
     * @param k
     * @return
     */
    public List<Integer> topKFrequent2(int[] nums, int k) {
        
        //记录当前数组中最大值和最小值
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        
        for(int n : nums){
            if(min > n){
                min = n;
            }
            if(max < n){
                max = n;
            }
        }
        
        //用于记录当前数组中的各个数字出现的次数，通过减最小值，将这个范围降到最小，当然如果这个数组非常散列的话，可能会出现这个列表非常大
        int[] count = new int[max - min + 1];
        
        for(int n : nums){
            count[n - min] ++;
        }
        
        //记录当前出现的最大次数
        int max_count = Integer.MIN_VALUE;
        
        for(int n : count){
            if(max_count < n){
                max_count = n;
            }
        }
        
        //用于记录各个次数出现的频次
        int[] count_nums = new int[max_count + 1];
        for(int n : count){
            if(n > 0){
                count_nums[n] ++;
            }
        }
        
        //记录临界的次数
        int cur_key = 0;
        //记录最小出现次数
        int min_count = 0;
        for(int i=count_nums.length - 1; i>=0; i--){
            if(count_nums[i] > 0) {
                cur_key += count_nums[i];
                if(cur_key >= k){
                    min_count = i;
                    break;
                }
            }
        }
        
        List<Integer> result = new ArrayList<Integer>();
        List<Integer> resultEuqal = new ArrayList<Integer>();//用于存储相当的结果
        List<Integer> resutlGreater = new ArrayList<Integer>();//由于存储大于的结果
        for(int i=0; i<count.length; i++){
            if(count[i] > min_count){
                resutlGreater.add(i + min);
            } else if(count[i] == min_count) {
                resultEuqal.add(i + min);
            }
        }
        //如果大于的满足了K，则直接返回
        if(resutlGreater.size() == k){
            return resutlGreater;
        }
        //之所以这样操作，是避免一种特例，就是相当的min_count的过多，导致无法记录大于的结果了。
        result.addAll(resutlGreater);
        result.addAll(resultEuqal.subList(0, k - resutlGreater.size()));
        
        return result;
    }
    
    
    public static void main(String[] args)
    {
        System.out.println(new Top_K_FrequentElements().topKFrequent2(new int[] {1,1,1,2,2,2,3,3,3,3}, 2));
    }

}
