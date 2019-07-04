package MyHeap;

public class Main {
    public static void main(String[] args) {
        MaxHeap<Integer> heap=new MaxHeap<>();
        for(int i=0;i<10;i++){
            int num= (int) (Math.random()*100);
            heap.add(num);
        }
        System.out.println(heap);
    }
}
