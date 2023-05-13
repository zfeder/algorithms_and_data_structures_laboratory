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
        return false;
    }

    @Override
    public boolean push(E e) {
        return false;
    }

    @Override
    public boolean contains(E e) {

        return false;
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

    private int getParentIndexByChildIndex(int childIndex) {
        if(childIndex % 2 == 0 && childIndex / 2 > 0) {
            return (childIndex / 2) - 1;
        } else {
            return childIndex / 2;
        }
    }

    private int getLeftChildIndex(int i) {
        if(2 * i + 1 < size) {
            return 2 * i + 1;
        } else {
            return i;
        }
    }
}
