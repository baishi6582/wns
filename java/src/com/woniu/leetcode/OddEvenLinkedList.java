package leetcode;

/**
 * 
	题目描述：
	给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
	
	请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
	
	示例 1:
	
	输入: 1->2->3->4->5->NULL
	输出: 1->3->5->2->4->NULL
	示例 2:
	
	输入: 2->1->3->5->6->4->7->NULL 
	输出: 2->3->6->7->1->5->4->NULL
	说明:
	
	应当保持奇数节点和偶数节点的相对顺序。
	链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。
	
	解决思路：主要说方法2，依靠于奇偶的不一致处理原则，比如说：1->2->3->4，此时计算到3的位置，需要做的是
		1) node2 指向 node3的下一个节点
		2) node1 指向 node3
		3）node3 指向 node2
	
	题目链接：https://leetcode-cn.com/problems/odd-even-linked-list/description/
 * @author woniu
 *
 */
public class OddEvenLinkedList {
    public ListNode oddEvenList(ListNode head) {
    	
    	ListNode result = method2(head);
    	
        return result;
    }
    
    private ListNode method2(ListNode head) {
    	//startOdd表示奇数连的起，endOdd表示奇数连的尾
    	ListNode startOdd = null, endOdd = null;
    	ListNode startEven = null, endEven = null;
    	boolean isOdd = true;
    	while(head != null) {
    		if(isOdd) {
    			if(startOdd == null) {
    				startOdd = head;
    				endOdd = head;
    				head = head.next;
    			}else {
    				endEven.next = head.next;
    				endOdd.next = head;
    				endOdd = endOdd.next;
    				endOdd.next = startEven;
    				head = endEven.next;
    			}
    			isOdd = false;
    			
    		}else {
    			if(startEven == null) {
    				startEven = head;
    				endEven = head;
    			}else {
    				endEven = head;
    			}
    			isOdd = true;
    			head = head.next;
    		}
    		
    	}
    	return startOdd;
    }
    
    /**
     * 实现的第一版，其实已经违规了，但是功能是OK的。
     * @param head
     * @return
     */
	private ListNode method1(ListNode head) {
		ListNode oddList = null, oddTemp = null;
    	ListNode evenList = null, evenTemp = null;
    	int count = 0;
    	while(head != null) {
    		if(++count % 2 == 0) {
    			if(evenList == null) {
    				evenList = new ListNode(head.val);
    				evenTemp = evenList;
    			}else {
    				evenTemp.next = new ListNode(head.val);
    				evenTemp = evenTemp.next;
    			}
    		} else {
    			if(oddList == null) {
    				oddList = new ListNode(head.val);
    				oddTemp = oddList;
    			}else {
    				oddTemp.next = new ListNode(head.val);
    				oddTemp = oddTemp.next;
    			}
    		}
    		head = head.next;
    	}
    	if(oddList != null) {
    		oddTemp.next = evenList;
    	}
		return oddList;
	}
    
    public static void main(String[] args) {
		ListNode b1 = new ListNode(1);
		ListNode b2 = new ListNode(2);
		ListNode b3 = new ListNode(3);
		
		ListNode b4 = new ListNode(4);
		ListNode b5 = new ListNode(5);
		b1.next = b2;
		b2.next = b3;
		b3.next = b4;
		b4.next = b5;
		new OddEvenLinkedList().oddEvenList(b1);
	}
}

