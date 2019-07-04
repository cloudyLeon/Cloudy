package MyQueue;

public class LoopQueue<E> implements Queue<E> {
    private E[] data;
    private int front;
    private int tail;
    private int size;
    public LoopQueue(){
        this(10);
    }

    public LoopQueue(int capacity){
        data= (E[]) new Object[capacity];
        front=0;
        tail=0;
        size=0;
    }
    @Override
    public int getsize() {
        return size;
    }

    @Override
    public int getCapacity() {
        return data.length-1;
    }

    @Override
    public void enqueue(E e) {
        if(size==getCapacity()){
            resize(size*2);
        }
        data[tail]=e;
        size++;
        tail=(tail+1)%data.length;
    }

    private void resize(int newCapacity) {
        E []temp= (E[]) new Object[newCapacity];
        for(int i=0;i<size;i++){
            temp[i]=data[(front+i)%data.length];
        }
        data=temp;
        temp=null;
    }

    @Override
    public E depueue() {
        E ref=data[front];
        front=(front+1)%data.length;
        size--;
        return ref;
    }

    @Override
    public E getFont() {
        return data[front];
    }

    @Override
    public boolean isEmpty() {
        return front==tail;
    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        sb.append("loopQueue:");
        sb.append("front[");
        for(int i=0;i<size;i++){
           sb.append(data[(front+i)%data.length]);
           if((front+i+1)%data.length!=tail){
               sb.append(",");
           }
        }
        sb.append("]tail");
        return sb.toString();
    }
}
