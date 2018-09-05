package leetcode.date_20180904;

import java.util.LinkedList;

/**
 * 
	题目描述：单词搜索
	给定一个二维网格和一个单词，找出该单词是否存在于网格中。
	
	单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
	
	示例:
	
	board =
	[
	  ['A','B','C','E'],
	  ['S','F','C','S'],
	  ['A','D','E','E']
	]
	
	给定 word = "ABCCED", 返回 true.
	给定 word = "SEE", 返回 true.
	给定 word = "ABCB", 返回 false.
	
	解题思路：这道题可以使用递归的方式解决，首先确定第一个字母的位置，之后以此为中心，进行上下左右的寻找。
	结束条件ud < 0 || ud >= n || lr <0 || lr >= m， 即超出了当前的整个二维数组的范围了。
	遍寻成功的条件为，遍寻次数count与word的长度相同时，表明已经将整个字符串遍寻结束。
	如果没有达到上述条件，则进行上下左右的寻找。
	
	需要注意一点的是：第一个字母开始的位置，不固定，所以有可能存在确定位置后查找失败后，需要重新从最初开始的位置再次遍寻
	这一点通过一个list来记录遍寻的整个足迹，当失败后，则才最开始的位置重新寻找	
	
	题目链接：https://leetcode-cn.com/problems/word-search/description/
 * @author woniu
 *
 */
public class WordSearch {
    public boolean exist(char[][] board, String word) {
    	
    	LinkedList<String> list = new LinkedList<String>();
    	int n = board.length;
    	int m = board[0].length;
    	
    	for(int i=0; i<n; i++) {
    		for(int j=0; j<m; j++) {
    			list.clear();//清空list，确保此次是一个全新的查找
    			if(exist(board, word, 0, i, j, list)) {
    				return true;
    			}
    		}
    	}
    	return exist(board, word, 0, 0, 0, list);
    }
    
    public boolean exist(char[][] board, String word, int count, int ud, int lr, LinkedList<String> list) {
    	
    	int n = board.length;
    	int m = board[0].length;
    	
    	if(word.length() == count) {
    		return true;
    	}
    	
    	if(ud < 0 || ud >= n || lr <0 || lr >= m) {
    		return false;
    	}
    	
		if(word.charAt(count) == board[ud][lr]) {
			if(list.contains(ud +"," + lr)){
				return false;
			} else {
				list.add(ud +"," + lr);
			}
			//进行上下左右的遍寻
			if( exist(board, word, count+1, ud + 1, lr, list) 
					|| exist(board, word, count+1, ud - 1, lr, list)
					|| exist(board, word, count+1, ud, lr + 1, list)
					|| exist(board, word, count+1, ud, lr - 1, list)) {
				return true;
			} else {
				list.remove(list.size() - 1);//某个路径如果遍寻失败，将此记录从整个遍寻路径中剔除，比便后面通过其他方式走到该处
			}
		}
		
    	return false;
    }
    
    public static void main(String[] args) {
		char[][] board = {{'A','B','C','E'},{'S','F','E','S'},{'A','D','E','E'}};
//				{
//				  {'A','B','C','E'},
//				  {'S','F','C','S'},
//				  {'A','D','E','E'}
//				};
		
		System.out.println(new WordSearch().exist(board, "ABCESEEEFS"));
	}
}
