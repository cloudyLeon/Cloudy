package leetcode;

import java.util.PriorityQueue;

public class Jhope {
    static class  Freq implements Comparable<Freq>{
        Freq(int num,int freq){
            this.num=num;
            this.freq=freq;
        }
        int num,freq;
        @Override
        public int compareTo(Freq another) {
            if(this.freq>another.freq) return 1;
            else if(this.freq<another.freq) return -1;
            else return 0;
        }
    }

    public static void main(String[] args) {
        Jhope jhope=new Jhope();
        PriorityQueue<Freq>queue=new PriorityQueue<>();
        for(int i=30;i>0;i--){
            queue.offer(new Freq(i,50-i));
        }
        for(int i=0;i<30;i++){
            System.out.println(queue.poll().freq);
        }
    }
}
