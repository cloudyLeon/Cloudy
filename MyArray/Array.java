package MyArray;


public class Array<T> {
    //数组数据
    private T []data;
     //数组大小
    private int size;
    public Array(int capacity){
        data= (T[]) new Object[capacity];
        size=0;
    }
    public Array(){
        this(10);
    }

    //得到数组长度
    public int getCapacity(){
        return data.length;
    }

    public int getSize(){
        return size;
    }

    //数组是否为空
    public  boolean isEmpty(){
        return size==0;
    }
    //末尾添加
    public void addLast(T key){
       add(size,key);
    }

    public void addFirst(T key){
        add(0,key);
    }
    //指定位置插入
    public void add(int index,T key){
        //数组容量判读

        if(index<0||index>size){
            throw new IllegalArgumentException("插入索引index不合法");
        }
        if(size== data.length){
            resize(size*2);
        }
        for(int i=size-1;i>=index;i--){
            data[i+1]=data[i];
        }
        data[index]=key;
        size++;
    }
    //打印数组
    @Override
    public String toString(){
        StringBuffer sb=new StringBuffer();
        sb.append(String.format("MyArray.Array:size=%d,capacity=%d\n",size,data.length));
        sb.append("[");
        for(int i=0;i<size;i++){
            sb.append(data[i]);
            if(i!=size-1){
            sb.append(",");
            }
        }
        sb.append("]");
        return  sb.toString();
    }

    //得到数组元素
    public T get(int index){
        if(index<0||index>size-1){
            throw new IllegalArgumentException("索引index不合法");
        }
        return data[index];
    }

    public T getFirst(){
        return get(0);
    }

    public T getLast(){
        return get(size-1);
    }

    //得到数组元素
    public void set(int index,T e){
        if(index<0||index>size-1){
            throw new IllegalArgumentException("索引index不合法");
        }
        data[index]=e;
    }

    public boolean contains(T e){
        for (int i=0;i<size;i++){
            if (data[i].equals(e)){
                return true;
            }
        }
        return false;
    }

    public int findIndex(T e){
        for (int i=0;i<size;i++){
            if (data[i].equals(e)){
                return i;
            }
        }
        return -1;
    }

    //删除指定位置
    public T delete(int index){
        if(index<0||index>size-1){
            throw new IllegalArgumentException("索引index不合法");
        }
        T ele=data[index];
        for(int i=index;i<size-1;i++){
            data[i]=data[i+1];
        }
        data[size-1]=null;
        size--;
        if(size==data.length/4&&data.length/2!=0){
            resize(data.length/2);
        }
        return ele;
    }

    public  T deleteFirst(){
        return delete(0);
    }

    public T deleteLast(){
       return delete(size-1);
    }
    //删除指定元素
    public boolean remove(T e){
        int index=findIndex(e);
        if(index!=-1){
            delete(index);
            return true;
        }
        return false;
    }

    private void resize(int capacity){
        T[] newAry= (T[]) new Object[capacity];
        for(int i=0;i<size;i++){
            newAry[i]=data[i];
        }
        data=newAry;
        newAry=null;
    }

    public void replace(int i, int j) {
        T temp=data[i];
        data[i]=data[j];
        data[j]=temp;
    }
}
