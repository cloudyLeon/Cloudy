package UF;

public interface UnionFind {
    void union(int p,int q);
    boolean search(int p,int q);
    int getSize();
}
