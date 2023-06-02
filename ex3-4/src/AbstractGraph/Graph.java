package AbstractGraph;

import java.util.*;

public class Graph<V, L> implements AbstractGraph<V, L> {

    /**
     * Grafo diretto o indiretto (non orientato)
     */
    private final boolean directed;

    /**
     * Grafo etichettato o non etichettato
     */
    private final boolean labelled;

    /**
     * Mappa che associa ogni nodo del grafo a un insieme di archi che vengono collegati
     * ad altri nodi.
     * Memorizza l'elenco dei Nodi del grafo come chiavi e gli insiemi di archi associati a
     * ciascun nodo come valori
     * Per accedere agli archi adiacenti ad un determinato nodo
     */
    private final Map<V, Set<Edge<V, L>>> adjacentList;

    /**
     * Costruttore che prende in input due parametri booleani e
     * crea una nuova istanza di HashMap
     */
    public Graph(boolean directed, boolean labelled) {
        this.directed = directed;
        this.labelled = labelled;
        this.adjacentList = new HashMap<>();
    }

    /**
     * Funzione per controllare se il grafo è diretto o indiretto
     * @return true se il grafo è diretto
     */
    @Override
    public boolean isDirected() {
        return directed;
    }

    /**
     * Funzione per controllare se il grafo è etichettato o non etichettato
     * @return true se il grafo è etichettato
     */
    @Override
    public boolean isLabelled() {
        return labelled;
    }

    /**
     * Funzione per aggiungere un nodo al grafo
     * @param a paramentro di tipo V che rappresenta il nodo da aggiungere al grafo
     * @return true se l'operazione di aggiunta è avvenuta con successo, false altrimenti
     * Se il nodo è presente nel grafo restituisce false
     * Se il nodo non è presente viene creata una nuova entry con chiave a e un insieme vuoto
     * di archi associati
     */
    @Override
    public boolean addNode(V a) {
        if (adjacentList.containsKey(a)) {
            return false;
        }
        // HashSet non permette duplicati, rappresenta il valore di ogni nodo
        adjacentList.put(a, new HashSet<>());
        return true;
    }

    /**
     * Funzione per aggiungere un arco tra due nodi, specificando l'etichetta dell'arco
     * @param a primo nodo
     * @param b secondo nodo
     * @param l etichetta dell'arco
     * @return true se l'arco è stato aggiunto con successo, false altrimenti
     * Viene recuperato l'insieme di archi associati al nodo a (archi uscenti)
     * Viene creato un nuovo oggetto Edge e viene aggiunto al set di archi
     * Se il grafo non è diretto viene creato un nodo inverso e aggiunto nella mappa
     */
    @Override
    public boolean addEdge(V a, V b, L l) {
        // Controlla se il nodo a e il nodo b sono presenti nella mappa
        if (!adjacentList.containsKey(a) || !adjacentList.containsKey(b)) {
            return false;
        }
        Set<Edge<V, L>> edges = adjacentList.get(a);

        // Controlla se esiste un arco che collega il nodo a e il nodo b
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

    /**
     * Funzione per verificare se un nodo è presente nel grafo
     * @param a nodo da verificare
     * @return true se il nodo è presente, false altrimenti
     */
    @Override
    public boolean containsNode(V a) {
        return adjacentList.containsKey(a);
    }

    /**
     * Funzione per verificare se esiste un arco tra due nodi
     * @param a primo nodo
     * @param b secondo nodo
     * @return true se presente un arco che collega i due nodi, false altrimenti
     */
    @Override
    public boolean containsEdge(V a, V b) {
        if (!adjacentList.containsKey(a) || !adjacentList.containsKey(b)) {
            return false;
        }
        for (Edge<V, L> edge : adjacentList.get(a)) {
            // Controlla se la destinazione è il nodo b partendo da archi associati al nodo a
            if (edge.getEnd().equals(b)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Funzione per rimuovere un nodo e tutti gli archi associati al nodo
     * @param a nodo da rimuovere
     * @return true se il nodo è stato rimosso, false altrimenti
     */
    @Override
    public boolean removeNode(V a) {
        if (!adjacentList.containsKey(a)) {
            return false;
        }
        adjacentList.remove(a);

        // cicla attraverso tutti gli archi
        for (Set<Edge<V, L>> edges : adjacentList.values()) {
            // rimuove tutti gli archi che hanno il nodo a come destinazione
            edges.removeIf(edge -> edge.getEnd().equals(a));
        }
        return true;
    }

    /**
     * Funzione per rimuovere un arco tra due nodi
     * @param a primo nodo
     * @param b secondo nodo
     * @return true se l'arco è stato rimosso, false altrimenti
     */
    @Override
    public boolean removeEdge(V a, V b) {
        if (!adjacentList.containsKey(a) || !adjacentList.containsKey(b)) {
            return false;
        }

        // recupera l'insieme di archi associato al nodo a
        Set<Edge<V, L>> edges = adjacentList.get(a);
        // rimuove tutti gli archi che hanno come destinazione il nodo b
        boolean removed = edges.removeIf(edge -> edge.getEnd().equals(b));

        // se il grafo è non orientato vengono rimossi gli archi del nodo b
        // che ha come destinazione il nodo a
        if (removed && !directed) {
            edges = adjacentList.get(b);
            edges.removeIf(edge -> edge.getEnd().equals(a));
        }
        return removed;
    }

    /**
     * Funzione per restituire il numero di nodi presenti nel grafo
     * @return numero di nodi del grafo
     */
    @Override
    public int numNodes() {
        return adjacentList.size();
    }

    /**
     * Funzione per restituire il numero di archi presenti nel grafo
     * @return numero di archi del grafo
     */
    @Override
    public int numEdges() {
        int count = 0;

        // cicla tutti gli archi della mappa
        for (Set<Edge<V, L>> edges : adjacentList.values()) {
            count += edges.size();
        }

        // se il grafo è non orientato il valore viene diviso per due
        if (!directed) {
            count /= 2;
        }
        return count;
    }

    /**
     * Funzione per restituire una collezione di tutti i nodi presenti nel grafo
     * @return collezioni di tutti i nodi
     * Vengono restituite tutte le chiavi (nodi) presenti nella mappa
     */
    @Override
    public Collection<V> getNodes() {
        return adjacentList.keySet();
    }

    /**
     * Funzione per restituire una collezione di tutti gli archi presenti nel grafo
     * @return collezione di tutti gli archi
     */
    @Override
    public Collection<? extends AbstractEdge<V, L>> getEdges() {
        List<Edge<V, L>> edges = new ArrayList<>();

        for (Set<Edge<V, L>> edgeSet : adjacentList.values()) {
            // aggiunge gli archi alla lista
            edges.addAll(edgeSet);
        }

        return edges;
    }

    /**
     * Funzione per restituire una collezione di nodi adiacenti ad un nodo
     * @param a nodo
     * @return collezione di nodi adiacenti al nodo che viene passato come paramentro
     */
    @Override
    public Collection<V> getNeighbours(V a) {
        if (!adjacentList.containsKey(a)) {
            return Collections.emptySet();
        }

        // crea un nuovo insieme per memorizzare i nodi adiacenti
        Set<V> neighbours = new HashSet<>();
        for (Edge<V, L> edge : adjacentList.get(a)) {
            // arco di destinazione del nodo passato come paramentro
            neighbours.add(edge.getEnd());
        }
        return neighbours;
    }

    /**
     * Funzione per resituire l'etichetta associata all'arco fra due nodi
     * @param a primo nodo
     * @param b secondo nodo
     * @return etichetta associata all'arco tra due nodi
     */
    @Override
    public L getLabel(V a, V b) {
        if (!adjacentList.containsKey(a)) {
            return null;
        }

        // itera gli archi associati al nodo a
        for (Edge<V, L> edge : adjacentList.get(a)) {
            // controlla se la destinazione del nodo a corrisponde al nodo b
            if (edge.getEnd().equals(b)) {
                // restituisce l'etichetta dell'arco
                return edge.getLabel();
            }
        }
        return null;
    }

}
