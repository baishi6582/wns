package leetcode;
/**
 * 
	编写一个程序，找到两个单链表相交的起始节点。
	
	 
	
	例如，下面的两个链表：
	
	A:          a1 → a2
	                   ↘
	                     c1 → c2 → c3
	                   ↗            
	B:     b1 → b2 → b3
	在节点 c1 开始相交。
	
	 
	
	注意：
	
	如果两个链表没有交点，返回 null.
	在返回结果后，两个链表仍须保持原有的结构。
	可假定整个链表结构中没有循环。
	程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
	
	解决思路：题目比较简单，主要先记录两个链条的长度，之后以长度短者为主线，进行遍历查找。之所以以短者为主，是因为，如果两者有相交，则必然不会超过长度，必然不会超过短者。
	
	链接：https://leetcode-cn.com/problems/intersection-of-two-linked-lists/description/
 * @author woniu
 *
 */
public class IntersectionOfTwoLinkedLists {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        
    	int alen = 0, blen = 0, len = 0;
    	ListNode anode = headA;
    	ListNode bnode = headB;
    	while(anode != null) {
    		alen ++;
    		anode = anode.next;
    	}
    	while(bnode != null) {
    		blen ++;
    		bnode = bnode.next;
    	}
    	
    	while(blen - alen > 0) {
    		headB = headB.next;
    		blen --;
    	}
    	
    	while(alen - blen > 0) {
    		headA = headA.next;
    		alen --;
    	}
    	
    	while(headB != null && headA != null) {
    		if(headB.equals(headA)) {
    			return headB;
    		}
    		headB = headB.next;
    		headA = headA.next;
    	}
    	
    	
    	return null;
    }
    
//    public static void main(String[] args) {
//		ListNode a1 = new ListNode(1);
//		ListNode a2 = new ListNode(2);
//		
//		ListNode b1 = new ListNode(1);
//		ListNode b2 = new ListNode(1);
//		ListNode b3 = new ListNode(1);
//		
//		ListNode c1 = new ListNode(3);
//		ListNode c2 = new ListNode(1);
//		
//		a1.next = a2;
//		
//		b1.next = b2;
//		b2.next = b3;
//		
//		c1.next = c2;
//		
//		b3.next = c1;
//		a2.next = c1;
//		
//		System.out.println(new Solution().getIntersectionNode(a1, b1).val);
//		
//	}
}

class ListNode {
     int val;
     ListNode next;
     ListNode(int x) {
         val = x;
         next = null;
     }
 }
