package leetcode.date_20181112;

/**
 * 
 * 题目描述：给定一个二叉搜索树的根结点 root, 返回树中任意两节点的差的最小值。

    示例：
    
    输入: root = [4,2,6,1,3,null,null]
    输出: 1
    解释:
    注意，root是树结点对象(TreeNode object)，而不是数组。
    
    给定的树 [4,2,6,1,3,null,null] 可表示为下图:
    
              4
            /   \
          2      6
         / \    
        1   3  
    
    最小的差值是 1, 它是节点1和节点2的差值, 也是节点3和节点2的差值。
 * 
 * 解题思路：通过递归的方式来解决该问题的，通过pre来记录计算前一个节点，这个在题目中有一点没有描述清楚，左子树小于根节点，有节点大于根节点。
 * 通过res来记录出当前的最小值。
 * 
 * 题目链接：https://leetcode-cn.com/problems/minimum-distance-between-bst-nodes/
 * 
 * @author woniu
 *
 */
public class MinimumDistanceBetween_bst_nodes
{
    int res = Integer.MAX_VALUE;
    int pre = -1;
    public int minDiffInBST(TreeNode root) {
        
        if(root.left != null) {
            minDiffInBST(root.left);
        }
        
        if(pre >= 0) {
            res = Math.min(res, Math.abs(root.val - pre));
        }
        
        pre = root.val;//子树计算完成后，则存储的为右子树的值，因为，当前树中，有节点的值是最接近父节点的值。如：root = [4,2,6,1,3,null,null]，在计算完2、1、3这个子树后，pre为3，因为他最近接4.
        
        if(root.right != null) {
            minDiffInBST(root.right);
        }
        
        return res;
    }
    
    public static void main(String[] args)
    {
        TreeNode root = new TreeNode(4);
        TreeNode left = new TreeNode(3);
        TreeNode right = new TreeNode(6);
        TreeNode leftL = new TreeNode(5);
        TreeNode leftR = new TreeNode(7);
        root.left = left;
        root.right = right;
        left.left = leftL;
        left.right = leftR;
        System.out.println(new MinimumDistanceBetween_bst_nodes().minDiffInBST(root));
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
