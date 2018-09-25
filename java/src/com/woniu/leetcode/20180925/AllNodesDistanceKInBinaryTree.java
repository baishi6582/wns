package leetcode.date_20180925;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 * 
 	题目描述:二叉树中所有距离为 K 的结点
 	给定一个二叉树（具有根结点 root）， 一个目标结点 target ，和一个整数值 K 。

	返回到目标结点 target 距离为 K 的所有结点的值的列表。 答案可以以任何顺序返回。
	
	 
	
	示例 1：
	
	输入：root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2
	
	输出：[7,4,1]
	
	解释：
	所求结点为与目标结点（值为 5）距离为 2 的结点，
	值分别为 7，4，以及 1
	
	
	
	注意，输入的 "root" 和 "target" 实际上是树上的结点。
	上面的输入仅仅是对这些对象进行了序列化描述。
 	
 	解题思路：
 	首先，理解题目,题目中有一个关键的信息点----输入的 "root" 和 "target" 实际上是树上的结点。
 	这表明我们就可以根据target直接找到对应的子节点了。只要顺藤摸瓜，就能找到子节点中的K.
 	此问题的难点不在于子节点。而在于兄弟节点或者父节点。这就要求我们找出一个逆向的关系网。
 	所以，我们首先将所有节点存储一份逆向关系网。这样就可以找父节点了。
 	有了父节点，兄弟节点就可以找到了。所以接下来就需要判断满足K了，这样问题就解决了。
 	
 	题目链接：https://leetcode-cn.com/problems/all-nodes-distance-k-in-binary-tree/description/
 * @author woniu
 *
 */
public class AllNodesDistanceKInBinaryTree {
	public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
		List<Integer> res = new ArrayList<Integer>();
		Map<TreeNode, TreeNode> map = new HashMap<TreeNode, TreeNode>();
		getParentNode(root, map, null);
		List<TreeNode> list = new ArrayList<TreeNode>();
		list.add(target);

		Set<TreeNode> set = new HashSet<TreeNode>();
		set.add(target);
		while (list.size() > 0 && K > 0) {
			K--;
			int size = list.size();
			for (int i = 0; i < size; i++) {
				TreeNode node = list.remove(0);
				if (node.left != null && set.add(node.left)) {
					list.add(node.left);
				}

				if (node.right != null && set.add(node.right)) {
					list.add(node.right);
				}

				if (map.containsKey(node) && set.add(map.get(node))) {
					list.add(map.get(node));
				}
			}
		}

		for (int i = 0; i < list.size(); i++) {
			res.add(list.get(i).val);
		}

		return res;
	}

	public void getParentNode(TreeNode root, Map<TreeNode, TreeNode> map, TreeNode parent) {
		if (root == null) {
			return;
		}
		if (parent != null) {
			map.put(root, parent);
		}

		getParentNode(root.left, map, root);
		getParentNode(root.right, map, root);
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(0);
		TreeNode r1 = new TreeNode(1);
		TreeNode r2 = new TreeNode(2);
		TreeNode r3 = new TreeNode(3);
		TreeNode r3l = new TreeNode(4);
		root.right = r1;
		r1.right = r2;
		r2.right = r3;
		r3.left = r3l;
		System.out.println(new AllNodesDistanceKInBinaryTree().distanceK(root, r3l, 1));
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
