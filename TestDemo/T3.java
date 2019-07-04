package TestDemo;

import MyQueue.ArrayQueue;
import MyQueue.LoopQueue;
import MyQueue.Queue;

public class T3 {
    public static void main(String[] args) {
        Queue<Integer> loopQueue=new LoopQueue();
        Queue<Integer> arrayQueue=new ArrayQueue();
        //TestQueue(100000,loopQueue);
        //TestQueue(100000,arrayQueue);
        for(int i=0;i<11;i++){
            loopQueue.enqueue(i);
            if(i%3==2){
                loopQueue.depueue();
            }
            System.out.println(loopQueue);
        }

        for(int i=0;i<11;i++){
            arrayQueue.enqueue(i);
            if(i%3==2){
                arrayQueue.depueue();
            }
            System.out.println(arrayQueue);
        }
    }

    public static void TestQueue(int count,Queue queue){
        Queue<Integer> loopQueue=new LoopQueue();
        Queue<Integer> arrayQueue=new LoopQueue();
        long start=System.nanoTime();
        for (int i=0;i<count;i++){
            int a= (int) (Math.random()*100);
            queue.enqueue(a);
        }
        System.out.println(queue);
        for (int i=0;i<count;i++){
            queue.depueue();
        }
        long end=System.nanoTime();
        double time=(end-start)/100000000.0;
        System.out.println(time+"s");
    }
}
