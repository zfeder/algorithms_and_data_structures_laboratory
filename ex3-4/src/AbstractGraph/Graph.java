package AbstractGraph;

import java.util.*;

public class Graph<V, L> implements AbstractGraph<V, L> {
    private boolean directed; // grafo diretto o indiretto
    private boolean labelled; // grafo etichettato o non etichettato
    private Map<V, Set<Edge<V, L>>> adjacentList;

    /**
     * Costruttore
     * @param directed
     * @param labelled
     */
    public Graph(boolean directed, boolean labelled) {
        this.directed = directed;
        this.labelled = labelled;
        this.adjacentList = new HashMap<>();
    }

    /**
     * Controlla se il grafo è diretto o indiretto
     * @return true se il grafo è diretto
     */
    @Override
    public boolean isDirected() {
        return directed;
    }

    @Override
    public boolean isLabelled() {
        return labelled;
    }

    @Override
    public boolean addNode(V a) {
        return false;
    }

    @Override
    public boolean addEdge(V a, V b, L l) {
        return false;
    }

    @Override
    public boolean containsNode(V a) {
        return false;
    }

    @Override
    public boolean containsEdge(V a, V b) {
        return false;
    }

    @Override
    public boolean removeNode(V a) {
        return false;
    }

    @Override
    public boolean removeEdge(V a, V b) {
        return false;
    }

    @Override
    public int numNodes() {
        return 0;
    }

    @Override
    public int numEdges() {
        return 0;
    }

    @Override
    public Collection<V> getNodes() {
        return null;
    }

    @Override
    public Collection<? extends AbstractGraph.AbstractEdge<V, L>> getEdges() {
        return null;
    }

    @Override
    public Collection<V> getNeighbours(V a) {
        return null;
    }

    @Override
    public L getLabel(V a, V b) {
        return null;
    }

}
