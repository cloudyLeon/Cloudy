package UF;

public class Main2 {
    public static void main(String[] args) {
        UnionFind uf=new UnionFind3(100);
        System.out.println(uf.search(11,13));
        uf.union(11,13);
        uf.union(11,15);
        System.out.println(uf.search(11,13));
        System.out.println(uf.search(15,13));
    }
}
