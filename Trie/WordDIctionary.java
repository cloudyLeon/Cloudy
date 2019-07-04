package Trie;

import java.util.Map;
import java.util.TreeMap;

public class WordDIctionary {
    private class Node{
        boolean isWord;
        Map<Character,Node> next;
        public Node(){
            this.isWord=false;
            next=new TreeMap<>();
        }
    }
    Node root;
    int size;
    public WordDIctionary(){
        root=new Node();
        size=0;
    }
    public void add(String word){
        Node cur=root;
        for(int i=0;i<word.length();i++){
            char c=word.charAt(i);
            if(cur.next.get(c)==null){
                cur.next.put(c,new Node());
            }
            cur=cur.next.get(c);
        }
        if(cur.isWord==false){
            cur.isWord=true;
            size++;
        }
    }

    public boolean search(String word){
        return search(word,root,0);
    }

    private boolean search(String word, Node root,int index) {
        if(index==word.length())
            return root.isWord;
        char c=word.charAt(index);
        if(root.next.get(c)==null){
            return false;
        }
        return search(word,root.next.get(c),index+1);
    }
}
