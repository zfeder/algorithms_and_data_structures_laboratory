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
        return size == 0;
    }

    @Override
    public boolean push(E e) {
        return true;
    }

    @Override
    public boolean contains(E e) {
        return heap.contains(e);
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

    /**
     * Restituisce l'indice del padre, dato un indice
     * @param childIndex
     * @return
     */
    private int getParentIndexByChildIndex(int childIndex) {
        if(childIndex % 2 == 0 && childIndex / 2 > 0) {
            return (childIndex / 2) - 1;
        } else {
            return childIndex / 2;
        }
    }

    /**
     * Restituisce l'indice di un figlio sinistro, dato l'indice del padre
     * @param i
     * @return
     */
    private int getLeftChildIndex(int i) {
        if(2 * i + 1 < size) {
            return 2 * i + 1;
        } else {
            return i;
        }
    }

    /**
     * Restituisce l'indice di un figlio destro, dato l'indice del padre
     * @param i
     * @return
     */
    private int getRightChildIndex(int i) {
        if(2 * i + 2 < size) {
            return 2 * i + 2;
        } else {
            return i;
        }
    }

    /**
     * Restituisce il padre dato l'indice di un figlio
     * @param childIndex
     * @return
     */
    public E getParentByChildIndex(int childIndex) {
        return heap.get(getParentIndexByChildIndex(childIndex));
    }

    public E getLeftChildByParentIndex(int parentIndex) {
        return heap.get(getLeftChildIndex(parentIndex));
    }

    public E getRightChildByParentIndex(int parentIndex) {
        return heap.get(getRightChildIndex(parentIndex));
    }








}
