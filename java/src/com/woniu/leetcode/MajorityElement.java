package leetcode;

import java.util.HashMap;
/**
 * 
	题目描述：求众数
	给定一个大小为 n 的数组，找到其中的众数。众数是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。

	你可以假设数组是非空的，并且给定的数组总是存在众数。
	
	示例 1:
	
	输入: [3,2,3]
	输出: 3
	示例 2:
	
	输入: [2,2,1,1,1,2,2]
	输出: 2
	
	解题思路：见下文
	
	题目链接：https://leetcode-cn.com/problems/majority-element/description/
 * 
 * @author woniu
 *
 */
public class MajorityElement {
	
	//通过一个map来记录每个num出现的次数，之后，遍历整个map找到超过n/2的key。
    public int majorityElement1(int[] nums) {
    	HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    	for(int n : nums) {
    		if(map.containsKey(n)) {
    			map.put(n, map.get(n) + 1);
    		} else {
    			map.put(n, 1);
    		}
    	}
    	int len = nums.length;
    	for(Integer key : map.keySet()) {
    		if(map.get(key) >= len / 2 + 1) {
    			return key;
    		}
    	}
    	return 0;
    	
    }
    
    //由于题目中说明了，肯定会存在超过n/2的众数存在，也就是说众数的个数必然超过其他数字之和，
    //所以该解法就是利用了这种限制，在count为0时，就存入新的key，最后总能确保在count不为0的数必为超过n/2的key
    public int majorityElement(int[] nums) {
        // 2 0 1 1 1 3 4 3 1
        int count=0;
        int temp=0;
        for(int i : nums){
            if(count==0){
                temp=i;
                count++;
            }else{
                if(temp==i){
                    count++;
                }else{
                    count--;
                }
            }
        }
        return temp;
    }
    
    public static void main(String[] args) {
		int[] nums = {3,2,3,2,3,0,3};
		
		System.out.println(new MajorityElement().majorityElement(nums));
	}
}
