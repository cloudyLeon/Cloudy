package MyQueue;

import MyArray.Array;

public class ArrayQueue<E> implements Queue<E>{
    private  Array<E> array;

    public ArrayQueue(){
        this(10);
    }

    ArrayQueue(int capacity){
        array=new Array<E>(capacity);
    }
    @Override
    public int getsize() {
        return array.getSize();
    }

    @Override
    public int getCapacity() {
       return array.getCapacity();
    }

    @Override
    public void enqueue(E e) {
        array.addLast(e);
    }

    @Override
    public E depueue() {
        return array.deleteFirst();
    }

    @Override
    public E getFont() {
        return array.getFirst();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        sb.append("arrayQueue:");
        sb.append("[");
        for(int i=0;i<getsize();i++){
            sb.append(array.get(i));
            if(i!=getsize()-1){
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
