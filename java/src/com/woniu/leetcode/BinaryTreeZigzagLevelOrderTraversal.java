package leetcode;

import java.util.LinkedList;
import java.util.List;
/**
 * 
	题目描述：
	 给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。

	例如：
	给定二叉树 [3,9,20,null,null,15,7],

	    3
	   / \
	  9  20
	    /  \
	   15   7
	返回锯齿形层次遍历如下：

	[
	  [3],
	  [20,9],
	  [15,7]
	]

	解决思路：
		该问题，其实我自己目前还没有太滤清楚思路，大概的做法是，将首先将每一层先拆成一个链条，通过遍历链条形成最终的结果。
		构建链条的过程，主要是注意奇偶层左右顺序的不同。

	题目链接：https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/description/
 *  @author woniu
 *
 */
public class BinaryTreeZigzagLevelOrderTraversal {
	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		List<List<Integer>>  resultList = new LinkedList<List<Integer>>();
		if(root == null) {
			return resultList;
		}
		List<Integer> list = new LinkedList<Integer>();
		LinkedList<TreeNode> nodeList = new LinkedList<TreeNode>();
		LinkedList<TreeNode> tmpList = new LinkedList<TreeNode>();
		nodeList.add(root);
		boolean isOdd = false;
		while(nodeList.size() > 0) {
			TreeNode node = nodeList.remove();
			list.add(node.val);
			
			if(!isOdd) {
				if(node.left != null) {
					tmpList.addFirst(node.left);
				}
				if(node.right != null) {
					tmpList.addFirst(node.right);
				}
			}
			else {
				if(node.right != null) {
					tmpList.addFirst(node.right);
				}
				if(node.left != null) {
					tmpList.addFirst(node.left);
				}
			}
			
			if(nodeList.size() == 0) {
				nodeList = tmpList;
				tmpList = new LinkedList<TreeNode>();
				resultList.add(list);
				list = new LinkedList<Integer>();
				isOdd = !isOdd;
			}
		}
		return resultList;
	}
	
	public static void main(String[] args) {
		TreeNode node = new TreeNode(3);
		TreeNode left = new TreeNode(9);
		TreeNode right = new TreeNode(20);
		node.left = left;
		node.right = right;
		
		TreeNode left2 = new TreeNode(15);
		TreeNode right2 = new TreeNode(7);
		right.left = left2;
		right.right = right2;
		
		System.out.println(new BinaryTreeZigzagLevelOrderTraversal().zigzagLevelOrder(node));
	}
}

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}
