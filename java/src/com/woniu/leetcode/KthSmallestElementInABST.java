package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 
	题目描述：
	
	 给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。
	
	说明：
	你可以假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数。
	
	示例 1:
	
	输入: root = [3,1,4,null,2], k = 1
	   3
	  / \
	 1   4
	  \
	   2
	输出: 1
	示例 2:
	
	输入: root = [5,3,6,2,4,null,null,1], k = 3
	       5
	      / \
	     3   6
	    / \
	   2   4
	  /
	 1
	输出: 3
	 
	解题思路：
		主要是通过一个list来存储排序最小的K个值，当新的值小于当前存储的第K个值时，替换其。
	
	题目链接：https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst/description/
	
	
 * @author woniu
 *
 */
public class KthSmallestElementInABST {
    public int kthSmallest(TreeNode root, int k) {
        
        List<Integer> list = new ArrayList<Integer>(k);
        list.add(root.val);
        getKList(root.left, list, k);
        getKList(root.right, list, k);
        if(list.size() > 0){
            return list.get(0);
        }
        return 0;
    }

    private void getKList(TreeNode root, List<Integer> list, int k)
    {
        //判断list.size() < k 是防止新来的节点的值大于list中已经存储的数据，但是list并未存储K个值。
        if(root != null && (list.size() < k || root.val < list.get(0))){
            if(list.size() >= k){
                list.remove(0);
            }
            boolean isInsert = false;
            int i=0;
            for(; i<list.size(); i++){
                if(root.val > list.get(i)){
                    list.add(i, root.val);
                    isInsert = true;
                    break;
                }
            }
            if(!isInsert) {
                list.add(i, root.val);
            }
            getKList(root.left, list, k);
            getKList(root.right, list, k);
        }
    }
}
