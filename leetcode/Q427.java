package leetcode;

import org.junit.jupiter.api.Test;

public class Q427 {
    class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;

        public Node() {}

        public Node(boolean _val, boolean _isLeaf, Node _topLeft, Node _topRight, Node _bottomLeft, Node _bottomRight) {
            val = _val;
            isLeaf = _isLeaf;
            topLeft = _topLeft;
            topRight = _topRight;
            bottomLeft = _bottomLeft;
            bottomRight = _bottomRight;
        }
    }
    public Node construct(int[][] grid) {
        return constructTree(grid,0,grid.length,0,grid.length);
    }

    private Node constructTree(int[][] grid, int left, int right, int top, int bottom) {
        Node node=new Node();
        if(isleaf(grid,left,right,top,bottom)) {
            node.isLeaf=true;
            if(grid[left][top]==1) node.val=true;
            return node;
        }else{
            node.topLeft=constructTree(grid,left,(right-left)/2,top,(bottom-top)/2);
            node.topRight=constructTree(grid,(right-left)/2,right,top,(bottom-top)/2);
            node.bottomLeft=constructTree(grid,left,(right-left)/2,(bottom-top)/2,bottom);
            node.bottomRight=constructTree(grid,(right-left)/2,right,(bottom-top)/2,bottom);
        }
        return node;
    }

    private boolean isleaf(int[][] grid, int left, int right, int top, int bottom){
        int flag=grid[top][left];
        for(int i=left;i<right;i++){
            for(int j=top;j<bottom;j++){
                if(grid[j][i]!=flag)
                    return false;
            }
        }
        return true;
    }

    @Test
    public void fun(){
        int[][]date={{1,1,1,1,0,0,0,0},{1,1,1,1,0,0,0,0},{1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1},{1,1,1,1,0,0,0,0},{1,1,1,1,0,0,0,0},{1,1,1,1,0,0,0,0},{1,1,1,1,0,0,0,0}};
        construct(date);
    }
}
