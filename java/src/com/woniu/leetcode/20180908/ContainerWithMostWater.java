package leetcode.date_20180908;

/**
 * 
	题目描述：盛最多水的容器
	
	给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
	
	说明：你不能倾斜容器，且 n 的值至少为 2。
	
	示例:
	
	输入: [1,8,6,2,5,4,8,3,7]
	输出: 49
	
	解题思路：
	1、最简单方式二次循环，求出所有肯能性的面积，然后选出最大值，当然效率自然就会低了
	2、分为两路开始，从前向后，和从后向前，依次选出最大面积，只需要O(n)即可得到结果
	
	题目链接；https://leetcode-cn.com/problems/container-with-most-water/description/
 * @author woniu
 *
 */
public class ContainerWithMostWater {
	//方法比较巧妙，要多学习这种思想
    public int maxArea1(int[] height) {
     
    	  int l = 0, r = height.length - 1;
          int max = 0, h = 0;
          while (l < r) {
              h = Math.min(height[l], height[r]);//选择出左右最小高度。
              max = Math.max(max, (r - l) * h);//求当前面积，选择最大值
              while (height[l] <= h && l < r) ++l;//如果后续的左侧都小于当前的l，则面积不可能超过当前面积
              while (height[r] <= h && l < r) --r;//同左侧结论
          }
          return max;
    }
    
    public int maxArea(int[] height) {
        
    	int len = height.length;
    	int area = 0;
    	for(int i=0; i<len; i++) {
    		for(int j=i+1; j<len; j++) {
    			int temp = height[i] > height[j] ? (j - i) * height[j] :(j - i) * height[i];
    			if(temp > area) {
    				area = temp;
    			}
    		}
    		
    	}
    	return area;
    }
    
    public static void main(String[] args) {
//    	int[] height = {1,8,6,2,3,4,8,3,7};
//    	
//		System.out.println(new Solution().maxArea(height));
    	

//    	int[] height = {1,2,3,4,5,6,7,8,9,10};
//
//		System.out.println(new Solution().maxArea(height));
	
    	int[] height = {1,2,4,3};
    	
		System.out.println(new ContainerWithMostWater().maxArea(height));
    }
}
