
import java.util.ArrayList;
import java.util.List;
/**
 * 
   问题描述：
   给定一个二叉树，返回它的中序 遍历。

    示例:
    输入: [1,null,2,3]
       1
        \
         2
        /
       3

    输出: [1,3,2]

   解决思路：
    通过迭代，依次遍历左右子节点即可解决

   链接：https://leetcode-cn.com/problems/binary-tree-inorder-traversal/description/
 * @author woniu
 *
 */
class BinaryTreeInorderTraversal {
    public List<Integer> inorderTraversal(TreeNode root) {
        if(root != null){
            return isHasChildren(root);
        }
        return new ArrayList<Integer>();
    }
    
    public List<Integer> isHasChildren(TreeNode root){
        List<Integer> list = new ArrayList<Integer>();
        if(root.left != null){
            list.addAll(isHasChildren(root.left));
        }
        list.add(root.val);
        if(root.right != null){
            list.addAll(isHasChildren(root.right));
        }
        
        return list;
    }
    
    public static void main(String[] args)
    {
        TreeNode root = new TreeNode(1);
        TreeNode rigth1 = new TreeNode(2);
        TreeNode rL = new TreeNode(3);
        root.right = rigth1;
        rigth1.left = rL;
        System.out.println(new BinaryTreeInorderTraversal().inorderTraversal(root));
    }
}

class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
 }
