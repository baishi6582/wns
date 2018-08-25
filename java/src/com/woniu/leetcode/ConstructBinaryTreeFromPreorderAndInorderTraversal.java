package leetcode;

import java.util.Arrays;
/**
 * 
         题目描述：
           根据一棵树的前序遍历与中序遍历构造二叉树。

            注意:
            你可以假设树中没有重复的元素。
            
            例如，给出
            
            前序遍历 preorder = [3,9,20,15,7]
            中序遍历 inorder = [9,3,15,20,7]
            返回如下的二叉树：
            
                3
               / \
              9  20
                /  \
               15   7

            题目链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/
 * @author woniu
 *
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        TreeNode root = method1(preorder, inorder);
        return root;
    }
 
    /**
     * 解题思路：前序遍历第一个值，必为跟节点，之后通过其与中序遍历，来切分成两个新的数组，继续递归查找。
     * 存在的问题为：每次需要复制数组，造成资源的浪费。
     * @param preorder
     * @param inorder
     * @return
     */
    private TreeNode method1(int[] preorder, int[] inorder) {
        if(preorder == null || preorder.length == 0) {
            return null;
        }
        int rVal = preorder[0];
        TreeNode root = new TreeNode(rVal);
        int rootIndex = 0;
        for(rootIndex=0; rootIndex<inorder.length; rootIndex++) {
            if(inorder[rootIndex] == rVal) {
                break;
            }
        }
        int[] left = Arrays.copyOfRange(inorder, 0, rootIndex);
        int[] right = Arrays.copyOfRange(inorder, rootIndex+1, inorder.length);
        if(left.length > 0) {
            root.left = method1(Arrays.copyOfRange(preorder, 1, 1+left.length), left);
        }
        if(right.length > 0) {
            root.right = method1(Arrays.copyOfRange(preorder, left.length+1, preorder.length), right);
        }
        return root;
    }
    
    public static void main(String[] args) {
        int[] preorder = {1,2,3};//{3,9,20,15,7};
        int[] inorder = {3,2,1};//{9,3,15,20,7};
        TreeNode node = new ConstructBinaryTreeFromPreorderAndInorderTraversal().buildTree(preorder, inorder);
        
    }
}
