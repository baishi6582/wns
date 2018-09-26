package leetcode.date_20180926;

/**
 * 
 * 题目描述：二叉树的最大深度 
   给定一个二叉树，找出其最大深度。
 * 
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * 
 * 说明: 叶子节点是指没有子节点的节点。
 * 
 * 解题思路：题目比较简单，就是分左右子树依次找就可以了
 * 
 * 题目链接；https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
 * 
 * @author woniu
 *
 */
public class MaximumDepthOfBinaryTree {
    public int maxDepth(TreeNode root) {
    	if(root == null) {
    		return 0;
    	}
    	
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}
