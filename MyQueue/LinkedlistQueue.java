package MyQueue;

import MyLinkedlist.Linkedlist;

public class LinkedlistQueue<E> implements Queue<E> {
    private class Node{
        public E e;
        public Node next;

        public Node(E e, Node next){
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
    private Node front,tail;

    public LinkedlistQueue(){
        front=tail=null;
        size=0;
    }
    @Override
    public int getsize() {
        return size;
    }


    @Override
    public int getCapacity() {
        return 0;
    }

    @Override
    public void enqueue(E e) {
        if(tail==null){
            tail=new Node(e);
            front=tail;
        }else{
            tail.next=new Node(e);
            tail=tail.next;
        }
        size++;
    }

    @Override
    public E depueue() {
        if(isEmpty()){
            throw new IllegalArgumentException("queue is empty can't dequeue");
        }
        Node ret=front;
        front=front.next;
        ret.next=null;
        if(front==null){
            tail=null;
        }
        size--;
        return ret.e;
    }

    @Override
    public E getFont() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }
}
