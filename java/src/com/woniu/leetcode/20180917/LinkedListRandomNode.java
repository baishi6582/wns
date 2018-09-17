package leetcode.date_20180917;

import java.util.ArrayList;
import java.util.Random;

/**
 * 
	题目描述：链表随机节点
	给定一个单链表，随机选择链表的一个节点，并返回相应的节点值。保证每个节点被选的概率一样。

	进阶:
	如果链表十分大且长度未知，如何解决这个问题？你能否使用常数级空间复杂度实现？
	
	示例:
	
	// 初始化一个单链表 [1,2,3].
	ListNode head = new ListNode(1);
	head.next = new ListNode(2);
	head.next.next = new ListNode(3);
	Solution solution = new Solution(head);
	
	// getRandom()方法应随机返回1,2,3中的一个，保证每个元素被返回的概率相等。
	solution.getRandom();
	
	解题思路：这道题目难点在于理解题目，既然是随机，结果就是不确定的。
	通过一个list,将node进行转换，然后通过random随机取数，这样就可以解决了。
	
	题目链接：https://leetcode-cn.com/problems/linked-list-random-node/description/
 * @author woniu
 *
 */
public class LinkedListRandomNode {
	ArrayList<Integer> list = new ArrayList<Integer>();
	public LinkedListRandomNode(ListNode head) {
		
		while(head != null) {
			list.add(head.val);
			head = head.next;
		}
	}

	public int getRandom() {
		Random rand = new Random();
		int value =rand.nextInt(list.size());
		return list.get(value % list.size());
	}
	
	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		LinkedListRandomNode solution = new LinkedListRandomNode(head);
		int n = 300;
		while(n-->0)
		System.out.println(solution.getRandom());
	}
}

class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
	}
}
