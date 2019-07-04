package SegementTree;

public class Main {
    public static void main(String[] args) {
        Integer[]ary={1,-4,-5,-3,-3,6,62,34,7};
        SegementTree<Integer> segementTree=new SegementTree<Integer>(ary,(a, b) -> a + b);
        System.out.println(segementTree);
        int i=segementTree.search(2,3);
        System.out.println(i);
    }


}
