package MyQueue;

public interface Queue<E> {
    int getsize();

    int getCapacity();

    void enqueue(E e);

    E depueue();

    E getFont();

    boolean isEmpty();
}
