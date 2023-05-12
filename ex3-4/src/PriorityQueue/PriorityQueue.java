package PriorityQueue;

import java.util.ArrayList;
import java.util.Comparator;

public class PriorityQueue<E extends Comparable<E>> implements AbstractQueue<E> {
    private ArrayList<E> heap;
    private Comparator<E> comparator;
    private int size;

    public PriorityQueue(Comparator<E> comparator) {
        heap = new ArrayList<>();
        this.comparator = comparator;
        size = 0;
    }

    @Override
    public boolean empty() {
        return heap.isEmpty();
    }

    @Override
    public boolean push(E e) {
        minHeap.insertItem(e);
        return true;
    }

    @Override
    public boolean contains(E e) {
        return minHeap.contains(e);
    }

    @Override
    public E top() {
        return null;
    }

    @Override
    public void pop() {

    }

    @Override
    public boolean remove(E e) {
        return false;
    }

    public ArrayList<E> getHeap() {
        return heap;
    }

    public int getSize() {
        return size;
    }
}
