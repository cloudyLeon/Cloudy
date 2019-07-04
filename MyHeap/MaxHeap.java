package MyHeap;

import MyArray.Array;

public class MaxHeap<E extends Comparable<E>> {
    private Array<E> data;
    public MaxHeap(){
        data=new Array();
    }

    public MaxHeap(int capacity){
        data=new Array(capacity);
    }

    public MaxHeap(E[] arr){

    }
    public int size(){
        return data.getSize();
    }
    public boolean isEmpty(){
        return data.isEmpty();
    }

    private int parent(int index){
        if(index==0){
            throw new IllegalArgumentException("doesn't have parent");
        }
        return (index-1)/2;
    }
    private int leftChild(int index){
        return index*2+1;
    }
    private int rightChild(int index){
        return index*2+2;
    }

    public void add(E e){
        data.addLast(e);
        siftUp(size()-1);
    }

    private void siftUp(int index){
        while (index!=0&&data.get(parent(index)).compareTo(data.get(index))<0){
            data.replace(index,parent(index));
            index=parent(index);
        }
    }

    public E extractMax(){
        E ref=findMax();
        data.replace(0,size()-1);
        data.deleteLast();
        siftDown(0);
        return ref;
    }

    public E findMax() {
        if(data.getSize()==0){
            throw new IllegalArgumentException("");
        }
        return data.get(0);
    }

    private void siftDown(int index) {
        while (leftChild(index)<size()){
            int j=leftChild(index);
            if(rightChild(index)<size()&&
                    data.get(rightChild(index)).compareTo(data.get(leftChild(index)))>0){
                 j=rightChild(index);
            }
            if(data.get(index).compareTo(data.get(j))>0){
                break;
            }
            data.replace(index,j);
            index=j;
        }
    }

    public E replace(E e){
        E ref=data.get(0);
        data.set(0,e);
        siftDown(0);
        return ref;
    }


    @Override
    public String toString(){
        StringBuilder sb=new StringBuilder();
        int floor=0;
        sb.append("MapHeap:");
        for(int i=0;i<data.getSize();i++){
            if(i==(2<<floor)-1){
                sb.append("\r\n");
                floor++;
            }
            sb.append(data.get(i)+",");
        }
        return sb.toString();
    }
}
