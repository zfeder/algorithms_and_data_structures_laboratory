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
        if (adjacentList.containsKey(a)) {
            return false;
        }

        adjacentList.put(a, new HashSet<>());
        return true;
    }

    @Override
    public boolean addEdge(V a, V b, L l) {
        if (!adjacentList.containsKey(a) || !adjacentList.containsKey(b)) {
            return false;
        }

        Set<Edge<V, L>> edges = adjacentList.get(a);

        // Check if the edge already exists
        for (Edge<V, L> edge : edges) {
            if (edge.getEnd().equals(b)) {
                return false;
            }
        }

        Edge<V, L> newEdge = new Edge<>(a, b, l);
        edges.add(newEdge);

        if (!directed) {
            Edge<V, L> reverseEdge = new Edge<>(b, a, l);
            adjacentList.get(b).add(reverseEdge);
        }

        return true;
    }

    @Override
    public boolean containsNode(V a) {
        return adjacentList.containsKey(a);
    }

    @Override
    public boolean containsEdge(V a, V b) {
        if (!adjacentList.containsKey(a) || !adjacentList.containsKey(b)) {
            return false;
        }

        for (Edge<V, L> edge : adjacentList.get(a)) {
            if (edge.getEnd().equals(b)) {
                return true;
            }
        }

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
