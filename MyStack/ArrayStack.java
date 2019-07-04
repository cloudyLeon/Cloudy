package MyStack;

import MyArray.Array;

public class ArrayStack<T> implements Stack<T>{
    private int size;
    private Array<T> array;
    ArrayStack(){
        array=new Array<>();
    }

    ArrayStack(int capacity){
        array=new Array<>(capacity);
    }

    @Override
    public void push(T t) {
        array.addLast(t);
    }

    @Override
    public T pop() {
        return array.deleteLast();
    }

    @Override
    public T peek() {
        return array.getLast();
    }

    @Override
    public int getSize() {
        return size;
    }

    public int getcapacity(){
        return array.getCapacity();
    }

    @Override
    public boolean isEmpty() {
        if(size==0){
            return true;
        }
        return false;
    }
    @Override
    public String toString(){
        StringBuilder sb=new StringBuilder();
        sb.append(String.format("stack:["));
        for(int i=0;i<size;i++){
            sb.append(array.get(i));
            if(i!=size-1){
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
