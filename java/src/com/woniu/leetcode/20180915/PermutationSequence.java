package leetcode.date_20180915;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 	题目描述： 第k个排列
 	给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。

	按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
	
	"123"
	"132"
	"213"
	"231"
	"312"
	"321"
	给定 n 和 k，返回第 k 个排列。
	
	说明：
	
	给定 n 的范围是 [1, 9]。
	给定 k 的范围是[1,  n!]。
	示例 1:
	
	输入: n = 3, k = 3
	输出: "213"
	示例 2:
	
	输入: n = 4, k = 9
	输出: "2314"
	
	解题思路：见下文方法描述
	
	题目链接：https://leetcode-cn.com/problems/permutation-sequence/description/
 * @author woniu
 *
 */
public class PermutationSequence {
	/**
	 * 
	 * 自己想的方法，执行效率一般，处理逻辑，首先算出n-1的阶乘是多少，如果k 等于该值，说明第一位已经确定了。为当前最小值；
	 * 如果k小于该值，第一位的值也已经确定了。即为当前i的值，因为上一个数字和小于k，当前最后一位大于K,所以，此数必在两者之间
	 * 举个例子说明吧。感觉说的不清楚。
	 * 如n=4, k=19吧。
	 * 首先n-1的阶乘为6，则第一次大于19为4*6，表明第一位为4，因为1、2、3开头的数字全部个数小于19;
	 * 之后，寻找下一位，即还有1、2、3，此时的k为1了。
	 * 
	 * n-1的阶乘为2，但是第一步k就小于2，表明这一次选出来的是1，
	 * 之后，继续寻找，当前还有2、3，此时k仍未1。
	 * 
	 * n-1的阶乘为1，此时k==1,所以此时选择出的是2，剩余的数字逆序补为3.  这里说明一下为啥是逆序，因为在当前剩余数的范围，第一个肯定是顺序，最后一个为逆序。
	 * 所以最终的结果为4123；
	 * 
	 * @param n
	 * @param k
	 * @return
	 */
    public String getPermutation1(int n, int k) {
    	
    	StringBuilder sb = new StringBuilder();
    	for(int i=1; i<=n; i++) {
    		sb.append(i);
    	}
    	return get(n, k, sb.toString());
    }
    
    public String get(int n, int k, String str) {
    	
    	int num = 1;
    	for(int i=1; i<n; i++) {
    		num *= i;
    	}
    	
    	for(int i=1; i<=n; i++) {
    		int sum = i * num;
    		if(sum == k) {//相等，表明找到，其余的顺序填充
    			StringBuilder sb = new StringBuilder(str.substring(0, i-1) + str.substring(i));
    			return str.charAt(i-1) + sb.reverse().toString();
    		}
    		if(sum > k) {
    			sum = sum - num;
    			return str.charAt(i-1) + get(n-1, k-sum, str.substring(0, i-1) + str.substring(i));
    		}
    	}
    	
    	return "";
    }
    
    //该方法执行效率较高，为参考别人的实现方式，思想的话，与上述方法思想差不多。都是先判断n-1阶乘。
    //不过其通过数字和链表的方式实现，没有过多的拼接字符串，效率比较高。
    public String getPermutation(int n, int k) {
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            res.add(i);
        }
        int[] fact = new int[n];
        fact[0] = 1;
        for (int i = 1; i < n; i++) {
            fact[i] = i * fact[i - 1];
        }
        k = k - 1;
        StringBuilder sb = new StringBuilder();
        for (int i = n; i > 0; i--) {
            int index = k / fact[i - 1];
            k = k % fact[i - 1];
            sb.append(res.get(index));
            res.remove(index);
        }
        return sb.toString();
    }
    
    public static void main(String[] args) {
//		System.out.println(new Solution().getPermutation(3, 1));//123
//		System.out.println(new Solution().getPermutation(3, 2));//132
//		System.out.println(new Solution().getPermutation(3, 3));//213
//		System.out.println(new Solution().getPermutation(3, 4));//231
//		System.out.println(new Solution().getPermutation(3, 5));//312
//		System.out.println(new Solution().getPermutation(3, 6));//321
//		
		System.out.println(new PermutationSequence().getPermutation(4, 2));//1243
		System.out.println(new PermutationSequence().getPermutation(4, 19));//4123
	}
}
