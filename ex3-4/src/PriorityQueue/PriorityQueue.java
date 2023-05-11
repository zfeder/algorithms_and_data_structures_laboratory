package PriorityQueue;

import MinHeap.MinHeap;

import java.util.Comparator;

public class PriorityQueue<E extends Comparable<E>> implements AbstractQueue<E> {
    MinHeap<E> minHeap;

    public PriorityQueue(Comparator<E> comparator) {
        minHeap = new MinHeap<>(comparator);
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
    public E top() {
        return null;
    }

    @Override
    public void pop() {

    }
}
