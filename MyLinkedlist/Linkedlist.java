package MyLinkedlist;

public class Linkedlist<E>{
    class Node{
        public E e;
        public Node next;
        public Node(E e,Node next){
            this.e=e;
            this.next=next;
        }
        public Node(E e){
            this(e,null);
        }

        public Node(){
            this(null,null);
        }
    }
    private int size;
    Node dummyhead;
    public Linkedlist(){
        dummyhead=new Node(null,null);
        size=0;
    }
    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size==0;
    }
    public void add(int index,E e){
        if(index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Illegal index.");
        Node prev=dummyhead;
        for(int i=0;i<index;i++){
            prev=prev.next;
        }
        prev.next=new Node(e,prev.next);
        size++;
    }

    public void addFirst(E e){
        add(0,e);
    }

    public void addLast(E e){
        add(size,e);
    }

    public E get(int index){
        if(isEmpty()){}
        if(index<0||index>size-1){
            throw new IllegalArgumentException("Get failed. Illegal index.");
        }
        Node cur=dummyhead.next;
        for(int i=0;i<index;i++){
            cur=cur.next;
        }
        return cur.e;
    }

    public E getFirst(){
        return get(0);
    }

    public E getLast(){
        return get(size-1);
    }

    public void set(int index,E e){
        if(index<0||index>size-1){
            throw new IllegalArgumentException("Set failed. Illegal index.");
        }
        Node cur=dummyhead.next;
        for(int i=0;i<index;i++){
            cur=cur.next;
        }
        cur.e=e;
    }

    public boolean contains(E e){
        for(Node cur=dummyhead.next;cur!=null;cur=cur.next){
            if(e.equals(cur.e)){
                return true;
            }
        }
        return false;
    }

    public E remove(int index){
        if(index<0||index>size-1){
            throw new IllegalArgumentException("Remove failed. Illegal index.");
        }
        Node prev=dummyhead;
        for(int i=0;i<index;i++){
            prev=prev.next;
        }
        Node retNode=prev.next;
        prev.next=retNode.next;
        retNode.next=null;
        size--;
        return retNode.e;
    }
    public E removeFirst(){
        return remove(0);
    }

    public E removeLast(){
        return remove(size-1);
    }
    @Override
    public String toString(){
        StringBuilder sb=new StringBuilder();
        sb.append("linkedlist:");
        Node cur=dummyhead.next;
        while (cur!=null){
            sb.append(cur.e+"->");
            cur=cur.next;
        }
        sb.append("null");
        return sb.toString();
    }
}
