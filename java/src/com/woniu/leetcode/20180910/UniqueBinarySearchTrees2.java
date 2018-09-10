package leetcode.date_20180910;

import java.util.ArrayList;
import java.util.List;
/**
 * 
	题目描述：不同的二叉搜索树 II
	给定一个整数 n，生成所有由 1 ... n 为节点所组成的二叉搜索树。

	示例:
	
	输入: 3
	输出:
	[
	  [1,null,3,2],
	  [3,2,null,1],
	  [3,1,null,null,2],
	  [2,1,3],
	  [1,null,2,null,3]
	]
	解释:
	以上的输出对应以下 5 种不同结构的二叉搜索树：
	
	   1         3     3      2      1
	    \       /     /      / \      \
	     3     2     1      1   3      2
	    /     /       \                 \
	   2     1         2                 3
	
	解题思路：分别确定根节点后，分别查找根节点后，分为两部分左右两部分处理，然后再分别确定根节点。
	比如确定i了， 0 ~ i-1 和 i+1 ~ n 两部分处理。
	分别找到结果后，再算交际即可
	
	题目链接：https://leetcode-cn.com/problems/unique-binary-search-trees-ii/description/
	
 * @author woniu
 *
 */
public class UniqueBinarySearchTrees2 {
    public List<TreeNode> generateTrees(int n) {
        return generateTrees(1, n);
    }
    
    public List<TreeNode> generateTrees(int begin, int n) {
    	List<TreeNode> list = new ArrayList<TreeNode>();
    	
    	for(int i=begin; i<=n; i++) {
    		//分别用于存储左右子树
    		List<TreeNode> left = new ArrayList<TreeNode>();
    		List<TreeNode> right = new ArrayList<TreeNode>();
    		if(i-begin > 0) {
    			left = generateTrees(begin, i - 1);
    		} else {
    			left.add(null);//对于没有左子树的，设为null
    		}
    		
    		if(n-i > 0) {
    			right = generateTrees(i+1, n);
    		} else {
    			right.add(null);
    		}
    		//左右子树交叉求集
    		for(TreeNode nodeL : left) {
    			for(TreeNode nodeR : right) {
    				TreeNode node = new TreeNode(i);
    				node.left = nodeL;
    				node.right = nodeR;
    				list.add(node);
    			}
    		}
    		
    	}
        
        
        return list;
    }
    
    public static void main(String[] args) {
		new UniqueBinarySearchTrees2().generateTrees(3);
	}
}
class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;
  TreeNode(int x) { val = x; }
}
