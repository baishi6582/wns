package leetcode;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
/**
         题目描述：
        给定两个非空链表来表示两个非负整数。位数按照逆序方式存储，它们的每个节点只存储单个数字。将两数相加返回一个新的链表。

        你可以假设除了数字 0 之外，这两个数字都不会以零开头。
        
        示例：
        
        输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
        输出：7 -> 0 -> 8
        原因：342 + 465 = 807
   
        解决思路：依次遍历数据，取余及进位
   
        链接：https://leetcode-cn.com/problems/add-two-numbers/description/
   
 * @author woniu
 *
 */
class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = mothod2(l1, l2);
        return result;
    }
    
    /**
     * 根据method1进行重构
     * @param l1
     * @param l2
     * @return
     */
    private ListNode mothod2(ListNode l1, ListNode l2){
        ListNode result = null;
        ListNode temp = null;
        
        int remainder = 0, carry = 0, sum = 0;
        while(l1 != null || l2 != null){
            sum = 0;
            if(l1 != null){
                sum += l1.val;
                l1 = l1.next;
            }
            if(l2 != null){
                sum += l2.val;
                l2 = l2.next;
            }
            remainder = (sum + carry) % 10;
            carry = (sum + carry) / 10;
            if(result == null){
                result = new ListNode(remainder);
                temp = result;
            }else{
                temp.next = new ListNode(remainder);
                temp = temp.next;
            }
        }
        
        if(carry > 0){
            temp.next = new ListNode(carry);
        }
        
        return result;
    }

    /**
     * 个人自己实现的方式，比较繁琐，但基本功能是OK的，主要思想也是依次遍历数据，取余及进位
     *  remainder 表示余数， carry表示 进位，后续可以使用更为准确的标识。
     * @param l1
     * @param l2
     * @return
     */
    private ListNode method1(ListNode l1, ListNode l2)
    {
        ListNode result = null;
        ListNode temp = null;
        //n1表示余数    n2表示进位
        int n1 = 0, n2 = 0, v1 = 0, v2 = 0;
        while(l1 != null && l2 != null){
            v1 = l1.val;
            v2 = l2.val;
            n1 = (v1 + v2 + n2) % 10;
            n2 = (v1 + v2 + n2) / 10;
            if(result == null){
                result = new ListNode(n1);
                temp = result;
            }else{
                temp.next = new ListNode(n1);
                temp = temp.next;
            }
            l1 = l1.next;
            l2 = l2.next;
        }
        while(l1 != null){
            v1 = l1.val;
            n1 = (v1 + n2) % 10;
            n2 = (v1 + n2) / 10;
            if(result == null){
                result = new ListNode(n1);
                temp = result;
            }else{
                temp.next = new ListNode(n1);
                temp = temp.next;
            }
            l1 = l1.next;
        }
        
        while(l2 != null){
            v2 = l2.val;
            n1 = (v2 + n2) % 10;
            n2 = (v2 + n2) / 10;
            if(result == null){
                result = new ListNode(n1);
                temp = result;
            }else{
                temp.next = new ListNode(n1);
                temp = temp.next;
            }
            l2 = l2.next;
        }
        if(n2>0){
            temp.next = new ListNode(n2);
            temp = temp.next;
        }
        return result;
    }
}

class ListNode
{
    int val;
    ListNode next;
    
    ListNode(int x)
    {
        val = x;
    }
}
