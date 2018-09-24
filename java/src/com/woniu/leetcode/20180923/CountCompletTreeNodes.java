package leetcode.date_20180923;

/**
 * 
	题目描述：完全二叉树的节点个数
	给出一个完全二叉树，求出该树的节点个数。

	说明：
	
	完全二叉树的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~ 2h 个节点。
	
	示例:
	
	输入: 
	    1
	   / \
	  2   3
	 / \  /
	4  5 6
	
	输出: 6
	
	解题思路：首先，暴力破解，无奈，最后两个case超时，
	说当前的解题思路吧
	1、查找左子树的高度
	2、查找右子树的高度
	3、判断两者是否相等，如果相等，表明左子树是一棵完整的完全二叉树，则可以通过公式计算了。否则，右子树是一棵完整的完全二叉树，通过公式计算。
	至于另一半，则，以下一层为根节点，继续查找。
	
	题目链接：https://leetcode-cn.com/problems/count-complete-tree-nodes/description/
	
 * @author woniu
 *
 */
public class CountCompletTreeNodes {
	public int countNodes(TreeNode root) {
		int count = 0;
		if(root == null) {
			return count;
		}
		
		TreeNode left = root.left;
		int leftDep = 0;
		while(left != null) {
			left = left.left;
			leftDep ++;
		}
		
		TreeNode right = root.right;
		int rightDep = 0;
		while(right != null) {
			right = right.right;
			rightDep ++;
		}
		
		if(leftDep == rightDep) {
			count = (int) Math.pow(2, leftDep) + countNodes(root.right);
		} else {
			count = countNodes(root.left) + (int)Math.pow(2, rightDep);
		}
		
		return count;
    }
}
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}	
