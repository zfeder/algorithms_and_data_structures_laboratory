package PriorityQueue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

/**
 * Implementazione di una Coda di Priorità
 * @author Federico Filì
 */
public class PriorityQueue<E> implements AbstractQueue<E> {
    private final ArrayList<E> priorityQueue;
    private final Comparator<E> comparator;
    private final HashMap<E, Integer> hashMap;

    public PriorityQueue(Comparator<E> comparator) {
        priorityQueue = new ArrayList<>();
        hashMap = new HashMap<>();
        this.comparator = comparator;
    }

    @Override
    public boolean empty() {
        return getSize() == 0;
    }

    @Override
    public boolean push(E e) {
        if(hashMap.containsKey(e)) {
            return false;
        }
        priorityQueue.add(e);
        hashMap.put(e, priorityQueue.size() - 1);
        int index = priorityQueue.size() - 1;
        int parentIndex = getParentIndexByChildIndex(index);
        while(parentIndex != index && comparator.compare(priorityQueue.get(index), priorityQueue.get(parentIndex)) < 0) {
            swap(index, parentIndex);
            index = parentIndex;
            parentIndex = getParentIndexByChildIndex(index);
        }
        return true;
    }

    @Override
    public boolean contains(E e) {
        return hashMap.containsKey(e);
    }

    @Override
    public E top() {
        if(getSize() != 0) {
            return priorityQueue.get(0);
        }
        return null;
    }

    @Override
    public void pop() {
        if(getSize() == 0) {
            return;
        }
        E firstValue = priorityQueue.get(0);
        swap(0, getSize() - 1);
        hashMap.remove(firstValue);
        priorityQueue.remove(getSize() - 1);
        fixHeap(0);

    }

    public boolean remove(E e) {
        if(getSize() == 0) {
            return false;
        }
        if(hashMap.containsKey(e)) {
            int index = hashMap.get(e);
            if(index == getSize() - 1){
                priorityQueue.remove(getSize() - 1);
                hashMap.remove(e);
                return true;
            }
            swap(index, getSize() - 1);
            priorityQueue.remove(getSize() - 1);
            hashMap.remove(e);
            int parentIndex = getParentIndexByChildIndex(index);
            while(index > 0 && comparator.compare(priorityQueue.get(index), priorityQueue.get(parentIndex)) < 0) {
                swap(index, parentIndex);
                index = parentIndex;
                parentIndex = getParentIndexByChildIndex(index);

            }
            fixHeap(index);
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
            return (childIndex - 1) / 2;
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
    public void fixHeap(int index) {
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
            swap(index, minValue);
            fixHeap(minValue);
        }
    }

    private void swap(int index, int min) {
        E tempValue = priorityQueue.get(min);
        E tempValue2 = priorityQueue.get(index);
        priorityQueue.set(min, tempValue2);
        priorityQueue.set(index, tempValue);
        hashMap.put(tempValue, index);
        hashMap.put(tempValue2, min);


    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SIZE = ").append(getSize()).append(" ");
        for (int i = 0; i < getSize(); i++) {
            sb.append("[").append(priorityQueue.get(i)).append("]");
        }
        return sb.toString();
    }




}
