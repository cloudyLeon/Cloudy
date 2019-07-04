package SegementTree;

public class SegementTree<E> {
    private E[] data;
    private E[] tree;
    private Merger<E> merger;
    public SegementTree(E[] arr,Merger<E> merger){
        this.merger=merger;
        data= (E[]) new Object[arr.length];
        for(int i=0;i<arr.length;i++){
            data[i]=arr[i];
        }
        tree=(E[]) new Object[arr.length*4];
        buildSegmentTree(0,0,arr.length-1);
    }

    private void buildSegmentTree(int treeIndex, int l, int r) {
        if(l==r){
            tree[treeIndex]=data[l];
            return;
        }
        int mid=l+(r-l)/2;
        int leftChild=leftChild(treeIndex);
        int rightChild=rightChild(treeIndex);
        buildSegmentTree(leftChild,l,mid);
        buildSegmentTree(rightChild,mid+1,r);
        tree[treeIndex]=merger.Merge(tree[leftChild],tree[rightChild]);
    }

    private int leftChild(int index){
        return (index*2)+1;
    }

    private int rightChild(int index){
        return (index*2)+2;
    }
    public int getSize(){
        return data.length;
    }
    public E get(int index){
        return data[index];
    }
    @Override
    public String toString(){
        StringBuilder sb=new StringBuilder();
        for(int i = 0 ; i < tree.length ; i ++){
            if(tree[i] != null)
                sb.append(tree[i]);
            else
                sb.append("null");

            if(i != tree.length - 1)
                sb.append(", ");
        }
        sb.append(']');
        return sb.toString();
    }

    public E search(int queryL,int queryR){
        return search(0,0,data.length-1,queryL,queryR);
    }

    private E search(int treeIndex, int l, int r, int queryL, int queryR) {
        if(queryL<l||queryR<l||queryL>r||queryR>r||queryL>queryR){
            throw new IllegalArgumentException("illegal param");
        }
        int mid=l+(r-l)/2;
        int rightChild=rightChild(treeIndex);
        int leftChirld=leftChild(treeIndex);
        if(l==queryL&&r==queryR){
            return tree[treeIndex];
        }else if(queryL>mid){
            return search(rightChild,mid+1,r,queryL,queryR);
        }else if(queryR<=mid ){
            return search(leftChirld,l,mid,queryL,queryR);
        }
        E leftRes=search(leftChirld,l,mid,queryL,mid);
        E rightRes=search(rightChild,mid+1,r,mid+1,queryR);
        return merger.Merge(leftRes,rightRes);
    }
}
