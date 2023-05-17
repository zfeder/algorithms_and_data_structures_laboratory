package PriorityQueue;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Implementazione di una Coda di Priorità
 * @author Federico Filì
 */
public class PriorityQueue<E extends Comparable<E>> implements AbstractQueue<E> {
    private ArrayList<E> priorityQueue;
    private final Comparator<E> comparator;

    public PriorityQueue(Comparator<E> comparator) {
        priorityQueue = new ArrayList<>();
        this.comparator = comparator;
    }

    @Override
    public boolean empty() {
        return getSize() == 0;
    }

    @Override
    public boolean push(E e) {
        if(contains(e)) {
            return false;
        }
        priorityQueue.add(e);
        int index = priorityQueue.size() - 1;
        int parentIndex = getParentIndexByChildIndex(index);
        while(parentIndex != index && comparator.compare(priorityQueue.get(index), priorityQueue.get(parentIndex)) < 0) {
            swapValue(index, parentIndex);
            index = parentIndex;
            parentIndex = getParentIndexByChildIndex(index);
        }
        return priorityQueue.contains(e);
    }

    @Override
    public boolean contains(E e) {
        return priorityQueue.contains(e);
    }

    @Override
    public E top() {
        return priorityQueue.get(0);
    }

    @Override
    public void pop() {
        E lastItem = priorityQueue.get(getSize() - 1);
        priorityQueue.set(0, lastItem);
        priorityQueue.remove(getSize() - 1);
        heapify(0);
    }

    @Override
    public boolean remove(E e) {
        if(getSize() == 0) {
            return false;
        }
        if(priorityQueue.contains(e)) {
            int index = priorityQueue.indexOf(e);
            E lastItem = priorityQueue.get(getSize() - 1);
            priorityQueue.set(index, lastItem);
            priorityQueue.remove(getSize() - 1);
            heapify(index);
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<E> getHeap() {
        return priorityQueue;
    }

    /**
     * Ristituisce la dimensione dell'heap
     * @return dimensione dell'heap
     */
    public int getSize() {
        return priorityQueue.size();
    }

    /**
     * Restituisce l'indice del padre, dato un indice
     * @param childIndex indice del figlio
     * @return indice del padre
     */
    private int getParentIndexByChildIndex(int childIndex) {
        if(childIndex == 0){
            return 0;
        } else if(childIndex % 2 == 0 && childIndex / 2 > 0){
            return (childIndex - 1) / 2;
        } else {
            return childIndex / 2;
        }
    }

    /**
     * Restituisce l'indice di un figlio sinistro, dato l'indice del padre
     * @param i indice del padre
     * @return indice del figlio sinistro
     */
    private int getLeftChildIndex(int i) {
        if(2 * i + 1 < getSize()) {
            return (2 * i + 1);
        } else {
            return i;
        }
    }

    /**
     * Restituisce l'indice di un figlio destro, dato l'indice del padre
     * @param i indice del padre
     * @return indice del figlio destro
     */
    private int getRightChildIndex(int i) {
        if (2 * i + 2 < getSize()) {
            return (2 * i + 2);
        } else {
            return i;
        }
    }

    /**
     * Restituisce il padre dato l'indice di un figlio
     * @param childIndex indice del figlio
     * @return il padre dell'indice del figlio
     */
    public E getParentByChildIndex(int childIndex) {
        return priorityQueue.get(getParentIndexByChildIndex(childIndex));
    }

    /**
     * Restituisce i figli di sinistra, dato l'indice del padre
     * @param parentIndex indice del padre
     * @return figli di sinistra
     */
    public E getLeftChildByParentIndex(int parentIndex) {
        return priorityQueue.get(getLeftChildIndex(parentIndex));
    }

    /**
     * Restituisce i figli di destra, dato l'indice del padre
     * @param parentIndex indice del padre
     * @return figli di destra
     */
    public E getRightChildByParentIndex(int parentIndex) {
        return priorityQueue.get(getRightChildIndex(parentIndex));
    }

    /**
     * Verifica che l'indice index in input è il valore minimo dell'heap, in caso
     * contrario scambio il valore con il minimo e richiama heapify risorsivamente
     * @param index indice
     */
    public void heapify(int index) {
        int leftIndex = getLeftChildIndex(index);
        int rightIndex = getRightChildIndex(index);
        int minValue = index;
        if(leftIndex < getSize() && comparator.compare(priorityQueue.get(leftIndex), priorityQueue.get(index)) < 0) {
            minValue = leftIndex;
        }
        if(rightIndex < getSize() && comparator.compare(priorityQueue.get(rightIndex), priorityQueue.get(minValue)) < 0) {
            minValue = rightIndex;
        }
        if(minValue != index) {
            swapValue(index, minValue);
            heapify(minValue);
        }
    }

    /**
     *
     * @param index
     * @param parent
     */
    private void swapValue(int index, int parent) {
        E tempValue = priorityQueue.get(parent);
        priorityQueue.set(parent, priorityQueue.get(index));
        priorityQueue.set(index, tempValue);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SIZE = " + getSize() + " ");
        for (int i = 0; i < getSize(); i++) {
            sb.append("[" + priorityQueue.get(i) + "]");
        }
        return sb.toString();
    }




}
