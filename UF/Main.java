package UF;

import java.util.Random;

public class Main {
    public static double testTime(int num,UnionFind uf){
        int size=uf.getSize();
        Random random=new Random();
        long startTime=System.nanoTime();
        for(int i=0;i<num;i++){
            int p=random.nextInt(size);
            int q=random.nextInt(size);
            uf.union(p,q);
        }
        for(int i=0;i<num;i++){
            int p=random.nextInt(size);
            int q=random.nextInt(size);
            uf.search(p,q);
        }
        long endTime=System.nanoTime();
        return (endTime-startTime)/1000000000.0;
    }

    public static void main(String[] args) {
        int size=100000;
        int num=100000;
        UnionFind uf1=new UnionFind1(size);
        double UF1Time=testTime(num,uf1);
        System.out.println(UF1Time+"s");
        UnionFind uf2=new UnionFind2(size);
        double UF2Time=testTime(num,uf2);
        System.out.println(UF2Time+"s");
        UnionFind uf3=new UnionFind3(size);
        double UF3Time=testTime(num,uf3);
        System.out.println(UF3Time+"s");
    }
}
