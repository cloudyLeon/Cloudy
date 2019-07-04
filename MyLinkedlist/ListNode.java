package MyLinkedlist;

public class ListNode<E> {
    int val;
     ListNode next;
     ListNode(int x) { val = x; }
     public ListNode(int[] ary){
         if(ary==null||ary.length==0){
             throw new IllegalArgumentException("parameter Illegal");
         }
         this.val=ary[0];
         ListNode cur=this;
         for(int i=1;i<ary.length;i++){
             cur.next=new ListNode(ary[i]);
             cur=cur.next;
         }
     }
}
