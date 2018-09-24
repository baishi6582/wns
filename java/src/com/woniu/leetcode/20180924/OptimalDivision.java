package leetcode.date_20180924;

/**
 * 
	题目描述:最优除法
	给定一组正整数，相邻的整数之间将会进行浮点除法操作。例如， [2,3,4] -> 2 / 3 / 4 。

	但是，你可以在任意位置添加任意数目的括号，来改变算数的优先级。你需要找出怎么添加括号，才能得到最大的结果，并且返回相应的字符串格式的表达式。你的表达式不应该含有冗余的括号。
	
	示例：
	
	输入: [1000,100,10,2]
	输出: "1000/(100/10/2)"
	解释:
	1000/(100/10/2) = 1000/((100/10)/2) = 200
	但是，以下加粗的括号 "1000/((100/10)/2)" 是冗余的，
	因为他们并不影响操作的优先级，所以你需要返回 "1000/(100/10/2)"。
	
	其他用例:
	1000/(100/10)/2 = 50
	1000/(100/(10/2)) = 50
	1000/100/10/2 = 0.5
	1000/100/(10/2) = 2
	
	题目链接：https://leetcode-cn.com/problems/optimal-division/description/
 * @author woniu
 *
 */

public class OptimalDivision {
	/**
	 * 解题思路：一个规律2/3/4/5 想办法变成2*4*5/3，这样结果肯定是最大的，第二位的数，没有任何办法，它必然要是除数，
	 * 所以，我们只需要将第二位之后的数全部变成它的除数即可。怎么办？2/(3/4/5) = 2/(3* 1/4 * 1/5) = 2 * 4* 5 /3
	 * 结果，就是只需要一个括号。就是将第二位后续所有的数字全部括起来即可。
	 * @param nums
	 * @return
	 */
	public String optimalDivision(int[] nums) {
    	int len = nums.length;
    	if(len == 1) {
    		return nums[0] + "";
    	}
    	
    	if(len == 2) {
    		return nums[0] + "/" + nums[1];
    	}
    	
    	StringBuilder sb = new StringBuilder();
    	sb.append(nums[0] + "/(");
    	for(int i=1; i<len-1; i++) {
    		sb.append(nums[i] + "/");
    		
    	}
    	sb.append(nums[len-1] + ")");
    	return sb.toString();
    }
    
    public static void main(String[] args) {
		System.out.println(new OptimalDivision().optimalDivision(new int[] {2,3,4}));
	}
}
