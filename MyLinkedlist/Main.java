package MyLinkedlist;

public class Main {
    public static void main(String[] args) {
        Linkedlist<Integer> linkedlist=new Linkedlist<>();
        for(int i=0;i<100;i++){
            linkedlist.addFirst(i);
            if((i+2)%3==0){
                linkedlist.removeFirst();
            }
            System.out.println(linkedlist);
        }
    }
}
