package MyLinkedlist;

public class Solution {
    public ListNode removeElements(ListNode head,int value){
        if(head.val==value){
            ListNode ret=head;
            head=head.next;
            ret.next=null;
        }
        ListNode prev=head;
        while (prev.next!=null){
            if (prev.next.val==value){
                prev.next=prev.next.next;
                prev.next.next=null;
            }else {
                prev=prev.next;
            }
        }
        return head;
    }
}
