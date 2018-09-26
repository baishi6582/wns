package leetcode.date_20180926;
/**
 * 
 * 题目描述：具有所有最深结点的最小子树
 * 给定一个根为 root 的二叉树，每个结点的深度是它到根的最短距离。

	如果一个结点在整个树的任意结点之间具有最大的深度，则该结点是最深的。
	
	一个结点的子树是该结点加上它的所有后代的集合。
	
	返回能满足“以该结点为根的子树中包含所有最深的结点”这一条件的具有最大深度的结点。
	
	 
	
	示例：
	
	输入：[3,5,1,6,2,0,8,null,null,7,4]
	输出：[2,7,4]
	解释：
	
	我们返回值为 2 的结点，在图中用黄色标记。
	在图中用蓝色标记的是树的最深的结点。
	输入 "[3, 5, 1, 6, 2, 0, 8, null, null, 7, 4]" 是对给定的树的序列化表述。
	输出 "[2, 7, 4]" 是对根结点的值为 2 的子树的序列化表述。
	输入和输出都具有 TreeNode 类型。
 
 	解题思路：先找到最大深度，然后遍历找到满足最大深度的节点。
 	如果找到左孩子和有孩子都不为null，表明当前节点则为满足要求的节点，如果只有某一个子树，则为该子树满足条件
 	
 	题目链接：https://leetcode-cn.com/problems/smallest-subtree-with-all-the-deepest-nodes/description/
 * @author woniu
 *
 */
public class SmallestSubtreeWithAllTheDeepestNodes {
	public TreeNode subtreeWithAllDeepest(TreeNode root) {

		if(root == null || (root.left == null & root.right == null)){
			return root;
		}
		int count = depthOfTree(root) - 1;		
		
		System.out.println(count);
		
		return subtreeWithAllDeepest(root, 0, count);
	}
	
    private int depthOfTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(depthOfTree(root.left), depthOfTree(root.right));
    }
	
	public TreeNode subtreeWithAllDeepest(TreeNode root, int n, int count) {
		
		if(root == null) {
			return null;
		}
		if(n == count) {
			return root;
		}
		
		TreeNode left = subtreeWithAllDeepest(root.left, n+1, count);
		TreeNode right = subtreeWithAllDeepest(root.right, n+1, count);
		if(left != null && right != null) {
			return root;
		}
		return left != null ? left : right;
	}
	
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(3);
		TreeNode l1 = new TreeNode(5);
		TreeNode r1 = new TreeNode(1);
		TreeNode ll1 = new TreeNode(6);
		TreeNode lr1 = new TreeNode(2);
		TreeNode rl1 = new TreeNode(0);
		TreeNode rr1 = new TreeNode(8);
		TreeNode lrl1 = new TreeNode(7);
		TreeNode lrr1 = new TreeNode(4);
		
		TreeNode rrr1 = new TreeNode(9);
		
		root.left = l1;
		root.right = r1;
		l1.left = ll1;
		l1.right = lr1;
		
		r1.left = rl1;
		r1.right = rr1;
		
		lr1.left = lrl1;
		lr1.right = lrr1;
		
		rr1.right = rrr1;
		
		System.out.println(new SmallestSubtreeWithAllTheDeepestNodes().subtreeWithAllDeepest(root).val);
		
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
