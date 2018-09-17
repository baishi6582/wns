package interview;

import java.util.HashMap;

/**
 * 
 	题目描述：二叉树的最近公共祖先
	给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。

	百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
	
	例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]
	
	        _______3______
	       /              \
	    ___5__          ___1__
	   /      \        /      \
	   6      _2       0       8
	         /  \
	         7   4
	示例 1:
	
	输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
	输出: 3
	解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
	示例 2:
	
	输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
	输出: 5
	解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
	
	解题思路：见下文
	
	题目链接：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/description/
 * 
 * 
 * 
 * @author woniu
 *
 */
public class LowestCommonAncestorOfBinaryTree {
	//通过递归来找p和q，也就是给定了一个root,则必然会有左右子节之分，如果同时找到了p和q，当前的root就是根节点，
	//我猜如果我们当时答出来的话，应该面试就会通过了。
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if(root == null) {
			return null;
		}
		
		if(root == p) {
			return p;
		}
		
		if(root == q) {
			return q;
		}
		
		TreeNode left = lowestCommonAncestor(root.left, p, q);
		TreeNode right = lowestCommonAncestor1(root.right, p, q);
		
		if(left != null && right != null) {
			return root;
		}
		return left != null ? left : right;
	}
	
	
	//该方法并非该问题的想要的解法，但是，当时我也只想到了这个解法，同时，我也并没有写的很好，这个还是事后修改的版本，已经调试通过了。
	//当时的主要思路就是，map来存储其关系，然后，就可以逆序过来相同的父节点了。
	public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode A, TreeNode B) {
		HashMap<Integer, TreeNode> map = new HashMap<Integer, TreeNode>();
		map.put(root.val, root);
		findAB(root, A, B, map);
		
		return findParent(root, A, B, map);
	}

	private TreeNode findParent(TreeNode root, TreeNode A, TreeNode B, HashMap<Integer, TreeNode> map) {
		TreeNode parentNodeA = A;

		while (root.val != parentNodeA.val) {
			TreeNode parentNodeB = B;
			while (parentNodeB != null && parentNodeA.val != parentNodeB.val) {
			
				if(parentNodeB.val == root.val) {
					break;
				}
				parentNodeB = map.get(parentNodeB.val);
			}
			if (parentNodeA.val == parentNodeB.val) {
				return parentNodeA;
			}
			parentNodeA = map.get(parentNodeA.val);
		}
		
		return root;
	}

	public void findAB(TreeNode root, TreeNode A, TreeNode B, HashMap<Integer, TreeNode> map) {

		if(root.left != null) {
			map.put(root.left.val, root);
			findAB(root.left, A, B, map);
		}
		
		if(root.right != null) {
			map.put(root.right.val, root);
			findAB(root.right, A, B, map);
		}
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
