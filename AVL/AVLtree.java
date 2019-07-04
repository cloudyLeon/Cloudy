package AVL;

import java.util.ArrayList;

public class AVLtree <K extends Comparable<K>,V>{
    private Node root;
    private int size;
    private class Node{
        Node left;
        Node right;
        int height;
        K key;
        V value;
        Node(K key,V value){
            this.key=key;
            this.value=value;
            height =1;
        }
        Node(K key){
            this(key,null);
        }
    }
    public AVLtree(){
        size=0;
    }
    public int getSize(){
        return size;
    }
    public boolean isEmpty(){
        return size==0;
    }
    //像map中添加元素
    public void put(K key,V value){
        root =put(root,key,value);
    }

    private Node put(Node node, K key, V value) {
        if(node==null){
            size++;
            return new Node(key,value);
        }
        if(node.key.compareTo(key)>0){
            node.left=put(node.left,key,value);
        }else if(node.key.compareTo(key)<0){
            node.right=put(node.right,key,value);
        }else{
            node.value=value;
        }
        node.height=Math.max(getHeight(node.left),getHeight(node.right))+1;
        int balanceFactor=getBalanceFactor(node);
        if(balanceFactor>1&&getBalanceFactor(node.left)>0){
           return rightRotate(node);
        }
        if(balanceFactor<-1&&getBalanceFactor(node.right)<0){
            return leftRotate(node);
        }
        if(balanceFactor>1&&getBalanceFactor(node.left)<0){
            leftRotate(node.left);
            return rightRotate(node);
        }
        if(balanceFactor<1&&getBalanceFactor(node.right)>0){
            leftRotate(node.right);
            return rightRotate(node);
        }
        return node;
    }

    private Node leftRotate(Node y) {
        Node x=y.right;
        Node t3=x.left;
        x.left=y;
        y.right=t3;
        y.height=Math.max(getHeight(y.left),getHeight(y.right));
        return x;
    }

    public int getHeight(Node node){
        if(node==null){
            return 0;
        }
        return node.height;
    }
    public boolean isBST(){
        ArrayList<K>ary=new ArrayList();
        inOrder(root,ary);
        for(int i=0;i<ary.size()-1;i++){
            if(ary.get(i).compareTo(ary.get(i+1))>0){
                return false;
            }
        }
        return true;
    }

    public boolean isBalance(){
        return isBalance(root);
    }

    private boolean isBalance(Node node) {
        if(node==null) {
            return true;
        }
        int balanceFactor=getBalanceFactor(node);
        if(Math.abs(balanceFactor)>1){
            return false;
        }
        return isBalance(node.left)&&isBalance(node.right);
    }

    private int getBalanceFactor(Node node) {
        if(node==null){
            return 0;
        }
        return getHeight(node.left)-getHeight(node.right);
    }

    private void inOrder(Node root, ArrayList ary) {
        if(root==null){
            return;
        }
        inOrder(root.left,ary);
        ary.add(root.key);
        inOrder(root.right,ary);
    }
    // 对节点y进行向右旋转操作，返回旋转后新的根节点x
    //        y                              x
    //       / \                           /   \
    //      x   T4     向右旋转 (y)        z     y
    //     / \       - - - - - - - ->    / \   / \
    //    z   T3                       T1  T2 T3 T4
    //   / \
    // T1   T2
    public Node rightRotate(Node y){
        Node x=y.left;
        Node t3=x.right;
        x.right=y;
        y.left=t3;
        y.height=Math.max(getHeight(y.left),getHeight(y.right))+1;
        x.height=Math.max(getHeight(x.left),getHeight(x.right))+1;
        return x;
    }

    // 返回以node为根节点的二分搜索树中，key所在的节点
    private Node getNode(Node node, K key){

        if(node == null)
            return null;

        if(key.equals(node.key))
            return node;
        else if(key.compareTo(node.key) < 0)
            return getNode(node.left, key);
        else // if(key.compareTo(node.key) > 0)
            return getNode(node.right, key);
    }

    public boolean contains(K key){
        return getNode(root, key) != null;
    }

    public V get(K key){

        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }
}
