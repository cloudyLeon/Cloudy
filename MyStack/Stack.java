package MyStack;

public interface Stack<T> {
    //入栈
    void push(T t);
    //出栈
    T pop();
    T peek();
    int getSize();

    boolean isEmpty();
}
