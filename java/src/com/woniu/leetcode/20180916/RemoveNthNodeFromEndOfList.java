package leetcode.date_20180916;

/**
 * 
	题目描述：删除链表的倒数第N个节点
	给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。

	示例：
	
	给定一个链表: 1->2->3->4->5, 和 n = 2.
	
	当删除了倒数第二个节点后，链表变为 1->2->3->5.
	说明：
	
	给定的 n 保证是有效的。
	
	进阶：
	
	你能尝试使用一趟扫描实现吗？
	
	解题思路：设置了两个node，两者之间距离保持n，这样，在end到达最后时，就能保证begin就是要删除的元素。
	
	题目链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/description/
	
 * @author woniu
 *
 */
public class RemoveNthNodeFromEndOfList {
	public ListNode removeNthFromEnd(ListNode head, int n) {

		ListNode begin = head;
		ListNode end = head;
		int count = 0;
		while(end != null) {
			
			end = end.next;
			if (end != null && n <= count) {
				begin = begin.next;
			}
			count ++;
		}
		if(count == n) {
			head = head.next;
		} else {
			begin.next = begin.next.next;
		}
		
		return head;
		
	}
	
	public static void main(String[] args) {
		ListNode head = new ListNode(1);
//		head.next = new ListNode(2);
//		head.next.next = new ListNode(3);
//		head.next.next.next = new ListNode(4);
//		head.next.next.next.next = new ListNode(5);
		
		ListNode result = new RemoveNthNodeFromEndOfList().removeNthFromEnd(head, 1);
		System.out.println(result);
		while(result != null) {
			System.out.println(result.val);
			result = result.next;
		}
	}
}
class ListNode {
  int val;
  ListNode next;
  ListNode(int x) { val = x; }
}
