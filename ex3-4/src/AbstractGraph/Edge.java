package AbstractGraph;

/**
 * Implementazione di un Arco in un Grafo
 * @author Federico Filì
 */
public class Edge<V, L> implements AbstractEdge<V, L> {

    /**
     * Nodo di partenza
     */
    private final V start;

    /**
     * Nodo di destinazione
     */
    private final V end;

    /**
     * Etichetta dell'arco
     */
    private final L label;

    /**
     * Costruttore per creare nuovo oggetto Edge
     */
    public Edge(V start, V end, L label) {
        this.start = start;
        this.end = end;
        this.label = label;
    }

    /**
     * Funzione per restituire il nodo di partenza
     * @return nodo di partenza
     */
    public V getStart() {
        return start;
    }

    /**
     * Funzione per restituire il nodo di destinazione
     * @return nodo di destinazione
     */
    public V getEnd() {
        return end;
    }

    /**
     * Funzione per restituire l'etichetta dell'arco
     * @return etichetta dell'arco
     */
    public L getLabel() {
        return label;
    }
}