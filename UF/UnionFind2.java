package UF;

public class UnionFind2 implements UnionFind {
    private int[] parent;
    private int[] rank;
    public UnionFind2(int size){
        parent=new int[size];
        rank=new int[size];
        for(int i=0;i<size;i++){
            parent[i]=i;
            rank[i]=0;
        }
    }

    private int find(int p){
        while (p!=parent[p]){
            p=parent[p];
        }
        return p;
    }
    @Override
    public void union(int p, int q) {
        int parentP=find(p);
        int parentQ=find(q);
        if(parentP==parentQ){
            return;
        }
        if(rank[parentP]<rank[parentQ]) {
            parent[parentP] = parentQ;
        }else if(rank[parentQ]<rank[parentP]){
            parent[parentQ]=parentP;
        }else{//rank[parentP]==rank[parentQ]
            parent[parentP]=parentQ;
            rank[parentQ]+=1;
        }
    }

    @Override
    public boolean search(int p, int q) {
        return find(p)==find(q);
    }

    @Override
    public int getSize() {
        return parent.length;
    }
}
