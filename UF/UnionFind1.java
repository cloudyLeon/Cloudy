package UF;

public class UnionFind1 implements UnionFind{
    private int[] parent;
    private int size;
    public UnionFind1(int size){
        parent=new int[size];
        this.size=size;
         for(int i=0;i<size;i++){
             parent[i]=i;
         }
    }
    public int getSize(){
        return size;
    }
    private int find(int p){
        while (p!=parent[p]){
            p=parent[p];
        }
        return p;
    }

    public void union(int p,int q){
        int parentP=find(p);
        int parentQ=find(q);
        if(parentP==parentQ){
            return;
        }
        parent[parentP]=parentQ;
    }

    public boolean search(int p,int q){
        return find(p)==find(q);
    }
}
