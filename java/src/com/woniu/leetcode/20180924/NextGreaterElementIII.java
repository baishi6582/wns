package leetcode.date_20180924;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
	题目描述：下一个更大元素 III
	
	给定一个32位正整数 n，你需要找到最小的32位整数，其与 n 中存在的位数完全相同，并且其值大于n。如果不存在这样的32位整数，则返回-1。
	
	示例 1:
	
	输入: 12
	输出: 21
	示例 2:
	
	输入: 21
	输出: -1
	
	题目链接；https://leetcode-cn.com/problems/next-greater-element-iii/description/
 * @author woniu
 *
 */
public class NextGreaterElementIII {
	/**
	 * 
	 * 到着找顺序对，其中最接近的顺序对，交换位置，即我们所需要的答案。
	 * 如23453， 当我们找到4时，发现4/5为顺序的，则将其交换位置，其后的数字按照顺序排列，23534
	 * 如果最终我们没有找到顺序对，表明当前几个数字已经是可以组成的最大数字，如1000
	 * 
	 * @param n
	 * @return
	 */
    public int nextGreaterElement(int n) {
    	List<Long> list = new ArrayList<Long>();
    	
    	long tmp = n;
    	while(tmp != 0) {
    		long num = tmp % 10;
    		tmp = tmp / 10;
    		int index = 0;
    		long maxMin = Integer.MAX_VALUE;
    		for(int i=0; i<list.size(); i++) {
    			Long tmpNum = list.get(i);
    			if(num < tmpNum && tmpNum < maxMin) {
    				maxMin = tmpNum;
    				index = i;
    			}
    		}
    		
    		if(maxMin <  Integer.MAX_VALUE) {
    			tmp = tmp * 10 + list.remove(index);
    			list.add(num);
    			Collections.sort(list);
    			for(long tmpNum : list) {
    				tmp = tmp * 10 + tmpNum;
    			}
    			break;
    		}
    		list.add(num);
    		
    	}
    	
    	if(tmp == 0 || tmp > Integer.MAX_VALUE) {
    		return -1;
    	}
    	
    	return (int) tmp;
    }
    
    public static void main(String[] args) {
		System.out.println(new NextGreaterElementIII().nextGreaterElement(Integer.MAX_VALUE));
	}

}
