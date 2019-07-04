package MyArray;

import MyArray.Array;

public class Main {

    public static void main(String[] args) {
        Array<Integer> array=new Array<>(20);
        for(int i=0;i<10;i++){
            array.add(i,i);
        }
        System.out.println(array);
        array.deleteLast();
        System.out.println(array);
        array.add(5,777);
        System.out.println(array);
    }
}
