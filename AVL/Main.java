package AVL;

public class Main {
    public static void main(String[] args) {
        AVLtree<Integer,Integer> map=new AVLtree();
        for(int i=0;i<1000;i++){
            map.put(i,i);
        }
        System.out.println(map.isBST());
        System.out.println(map.isBalance());
    }
}
