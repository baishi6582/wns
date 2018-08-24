package leetcode;

import java.util.Arrays;

public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
    public TreeNode buildTree(int[] preorder, int[] inorder) {

//    	if(preorder == null || preorder.length == 0) {
//    		return null;
//    	}
    	
//    	TreeNode root = method1(preorder, inorder);
    	TreeNode root = method2(preorder, 0, preorder.length, inorder, 0, inorder.length);
    	
        return root;
    }

    private TreeNode method2(int[] preorder, int pStart, int pEnd, int[] inorder, int iStart, int iEnd) {
    	if(preorder == null || (pEnd - pStart) == 0) {
    		return null;
    	}
    	
    	int rVal = preorder[pStart];
    	TreeNode root = new TreeNode(rVal);
    	int rootIndex = iStart;
    	for(rootIndex=iStart; rootIndex<iEnd; rootIndex++) {
    		if(inorder[rootIndex] == rVal) {
    			break;
    		}
    	}
    	if(rootIndex-iStart > 0) {
    		root.left = method2(preorder, pStart+1, rootIndex+1, inorder, iStart, rootIndex);
    	}
    	if(iEnd-(rootIndex+1) > 0) {
    	  	root.right = method2(preorder, rootIndex+1, pEnd, inorder, rootIndex+1, iEnd);
    	}
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
		int[] preorder = {3,9,20,15,7};
		int[] inorder = {9,3,15,20,7};
		TreeNode node = new ConstructBinaryTreeFromPreorderAndInorderTraversal().buildTree(preorder, inorder);
		
	}
}
