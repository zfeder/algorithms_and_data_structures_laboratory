package PriorityQueue;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Implementazione di una Coda di Priorità
 * @author Federico Filì
 */
public class PriorityQueue<E extends Comparable<E>> implements AbstractQueue<E> {
    private ArrayList<E> heap;
    private final Comparator<E> comparator;
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
        int i = size;
        heap.add(i, e);
        while(size > 0 && comparator.compare(heap.get(i), heap.get(getParentIndexByChildIndex(i))) < 0) {
            E temp = heap.get(i);
            heap.set(i, heap.get(getParentIndexByChildIndex(i)));
            heap.set(getParentIndexByChildIndex(i), temp);
            i = getParentIndexByChildIndex(i);
        }
        size++;
        return heap.contains(e);
    }

    @Override
    public boolean contains(E e) {
        return heap.contains(e);
    }

    @Override
    public E top() {
        if(size > 0) {
            return heap.get(0);
        } else {
            return null;
        }
    }

    @Override
    public void pop() {
        if(size > 0) {
            // imposto come primo valore l'ultimo elemento dell'heap
            heap.set(0, heap.get(size - 1));
            // funzione heapify
            heapify(0);
            size--;
        }
    }

    @Override
    public boolean remove(E e) {
        // controllo che l'elemento è presente nell'heap
        if(heap.contains(e)) {
            // rimuovo l'elemento
            heap.remove(e);
            size--;
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<E> getHeap() {
        return heap;
    }

    public int getSize() {
        return size;
    }

    /**
     * Restituisce l'indice del padre, dato un indice
     * @param childIndex indice del figlio
     * @return indice del padre
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
     * @param i indice del padre
     * @return indice del figlio sinistro
     */
    private int getLeftChildIndex(int i) {
        if(2 * i + 1 < size) {
            return 2 * i + 1;
        } else {
            return i; // indice del padre
        }
    }

    /**
     * Restituisce l'indice di un figlio destro, dato l'indice del padre
     * @param i indice del padre
     * @return indice del figlio destro
     */
    private int getRightChildIndex(int i) {
        if(2 * i + 2 < size) {
            return 2 * i + 2;
        } else {
            return i; // indice del padre
        }
    }

    /**
     * Restituisce il padre dato l'indice di un figlio
     * @param childIndex indice del figlio
     * @return il padre dell'indice del figlio
     */
    public E getParentByChildIndex(int childIndex) {
        return heap.get(getParentIndexByChildIndex(childIndex));
    }

    /**
     * Restituisce i figli di sinistra, dato l'indice del padre
     * @param parentIndex indice del padre
     * @return figli di sinistra
     */
    public E getLeftChildByParentIndex(int parentIndex) {
        return heap.get(getLeftChildIndex(parentIndex));
    }

    /**
     * Restituisce i figli di destra, dato l'indice del padre
     * @param parentIndex indice del padre
     * @return figli di destra
     */
    public E getRightChildByParentIndex(int parentIndex) {
        return heap.get(getRightChildIndex(parentIndex));
    }

    /**
     * Restituisce l'indice dell'elemento più piccolo, dati tre indici
     * @param val1 indice del padre
     * @param val2 indice del figlio di sinistra
     * @param val3 indice del figlio di destra
     * @return valore minimo tra i tre indici
     */
    private int minValue(int val1, int val2, int val3) {
        int minIndex = 0;
        if(comparator.compare(heap.get(val1), heap.get(val2)) < 0) {
            if(comparator.compare(heap.get(val3), heap.get(val1)) < 0) {
                minIndex = val3;
            } else {
                minIndex = val1;
            }
        } else {
            if(comparator.compare(heap.get(val2), heap.get(val3)) < 0) {
                minIndex = val2;
            } else {
                minIndex = val3;
            }
        }
        return minIndex;
    }

    /**
     * Verifica che l'indice i in input deve essere il valore minimo dell'heap, e in caso
     * contrario deve essere scambiato con il valore minimo e successivamente viene
     * nuovamente chiamato heapify in modo ricorsivo
     * @param i indice del nodo
     */
    private void heapify(int i) {
        // imposto di una funzione di confronto tra valori minimi
        int m = minValue(i, getLeftChildIndex(i), getRightChildIndex(i));
        if(m != i) {
            E temp = heap.get(m);
            heap.set(m, heap.get(i));
            heap.set(i, temp);
            heapify(m);
        }
    }








}
