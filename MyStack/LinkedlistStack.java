package MyStack;

import MyLinkedlist.Linkedlist;

public class LinkedlistStack<E> implements Stack<E>{
    private Linkedlist<E> linkedlist;
    public LinkedlistStack(){
        linkedlist=new Linkedlist<>();
    }
    public int getSize(){
        return linkedlist.getSize();
    }

    @Override
    public boolean isEmpty() {
        return linkedlist.isEmpty();
    }

    public void push(E e){
        linkedlist.addFirst(e);
    }

    public E pop(){
        return linkedlist.removeFirst();
    }
    public E peek(){
        return linkedlist.getFirst();
    }
    @Override
    public String toString(){
        StringBuilder sb=new StringBuilder();
        sb.append("stack:top");
        sb.append(linkedlist);
        return sb.toString();
    }

    public static void main(String[] args) {
        Stack<Integer> stack=new LinkedlistStack<>();
        for(int i=0;i<5;i++){
            stack.push(i);
            System.out.println(stack);
        }
        stack.pop();
        System.out.println(stack);
    }
}
