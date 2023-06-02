package AbstractGraph;

import PriorityQueue.PriorityQueue;
import java.io.*;
import java.util.*;

/**
 * Implementazione dell'algoritmo di Prim
 * @author Federico Filì
 */
public class Prim {

    /**
     * Funzione che implementa l'algoritmo di Prim all'interno di un grafo
     * @param graph grafo in input
     * @param <V> tipo dei nodi
     * @param <L> tipo dlle etichette
     * @return collezione degli archi dell'albero di copertura minima
     * L'algoritmo seleziona gli archi con l'etichetta minima e costruisce un albero
     * di copertura, utilizzando una coda di priorità per selezionare gli archi
     * successivi
     */
    public static <V, L extends Number> Collection<? extends AbstractEdge<V, L>> minimumSpanningForest(Graph<V, L> graph) {
        // prim nodo che viene utlizzato come nodo di partenza
        V source = graph.getNodes().iterator().next();
        if (!graph.containsNode(source))
            return null;

        // result rappresenta l'albero di copertura minimo
        Graph<V, L> result = new Graph<V, L>(false, true);

        // visited per tenere traccia dei nodi visitati durante l'esecuzione dell'algoritmo
        HashSet<V> visited = new HashSet<>();

        // comparatore per confrontare gli archi in base all etichette
        Comparator<Edge<V, L>> comparator = new Comparator<>() {
            public int compare(Edge<V, L> o1, Edge<V, L> o2) {
                if (o1.getLabel() == null && o2.getLabel() == null)
                    return 0;
                if (o1.getLabel() == null)
                    return 1;
                if (o2.getLabel() == null)
                    return -1;
                return Double.compare(o1.getLabel().doubleValue(), o2.getLabel().doubleValue());
            }
        };

        // utilizzo della coda di priorità per selezionare gli archi con etichetta minima
        PriorityQueue<Edge<V, L>> priorityQueue = new PriorityQueue<>(comparator);

        // cicla su su tutti i nodi vicini al nodo di partenza
        for (V v : graph.getNeighbours(source)) {
            L l = graph.getLabel(source, v);
            if (l != null) {
                // crea un nuovo arco con il nodo di partenza e viene inserito nella coda di priorità
                priorityQueue.push(new Edge<>(source, v, l));
            }
        }

        // il nodo di partenza viene aggiunto al grafo result
        result.addNode(source);

        // il nodo di partenza viene inserito nell'insieme dei nodi visitati
        visited.add(source);

        // contatore di numero di archi aggiunti
        int numEdges = 0;

        // viene estratto l'arco con etichetta minima e viene rimosso dalla coda
        while (numEdges <= graph.numNodes() - 1 && !priorityQueue.empty()) {
            Edge<V, L> minEdge = priorityQueue.top();

            priorityQueue.pop();

            // se il nodo di destinazione dell'arco non è stato visitato
            if (!visited.contains(minEdge.getEnd())) {
                V end = minEdge.getEnd();
                L label = minEdge.getLabel();

                result.addNode(end);
                result.addEdge(minEdge.getStart(), end, label);

                // viene aggiunto all'interno dei nodi visitati
                visited.add(end);

                numEdges++;

                // viene eseguito un ciclo su tutti i vicini del nodo di destinazione
                for (V v : graph.getNeighbours(end)) {
                    L l = graph.getLabel(end, v);
                    if (l != null && !visited.contains(v)) {
                        // viene inserito un nuovo arco nella coda di priorità
                        priorityQueue.push(new Edge<>(end, v, l));
                    }
                }
            }
        }

        // Stampa il numero di nodi e archi calcolato
        System.out.println("**************************************************");
        System.out.println("*    Dati verificati all'interno di Prim         *");
        System.out.println("*    Source: " + source + "                       *");
        System.out.println("*    Numero di Nodi: " + result.numNodes() + "                       *");
        System.out.println("*    Numero di Archi: " + result.numEdges() + "                      *");
        System.out.println("**************************************************\n");

        // viene restituita la collezione degli archi dell'albero
        return result.getEdges();
    }

    public static void main(String[] args) {
        Graph<String, Double> graph = new Graph<String, Double>(false, true);
        File file = new File("/Users/feder/Desktop/laboratorio-algoritmi-2022-2023/ex3-4/src/AbstractGraph/italian_dist_graph.csv");

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                String[] split = line.split(",");
                graph.addNode(split[0]);
                graph.addNode(split[1]);
                graph.addEdge(split[0], split[1], Double.parseDouble(split[2]));
            }
            br.close();
        } catch (FileNotFoundException notFound) {
            System.out.println("File non trovato: " + notFound);
            System.exit(0);
        } catch (IOException reading) {
            System.out.println("Impossibile leggere il file: " + reading);
            System.exit(0);
        }
        System.out.println("Grafo caricato correttamente.\n");
        System.out.println("**************************************************");
        System.out.println("*    Dati verificati all'interno del Main        *");
        System.out.println("*    Numero di Nodi: " + graph.numNodes() + "                       *");
        System.out.println("*    Numero di Archi: " + graph.numEdges() + "                      *");
        System.out.println("**************************************************\n");
        Collection<? extends AbstractEdge<String, Double>> prim = minimumSpanningForest(graph);
        assert prim != null;
        double sum = 0.0;
        for(AbstractEdge<String, Double> e : prim){
            Double label = e.getLabel();
            if(label != null) {
                sum += label;
            }
        }
        System.out.println("**************************************************");
        System.out.println("*    Altre informazioni                          *");
        System.out.println("*    Dimensione del Grafo dopo Prim: " + prim.size() + "       *");
        System.out.format("*    Peso totale: %.2f km", sum/2000);
        System.out.println("                    *");
        System.out.println("**************************************************\n");
        try {
            //String fileOutput = args[1].substring(0, args[1].length() - 4) + "_output.csv";
            String fileOutput = "/Users/feder/Desktop/laboratorio-algoritmi-2022-2023/ex3-4/src/AbstractGraph/output.csv";
            FileWriter outputWriter = new FileWriter(fileOutput);

            for (AbstractEdge<String, Double> e : prim) {
                outputWriter.write(e.toString() + "\n");
            }

            outputWriter.close();
            System.out.println("Scrittura su file completata con successo.");
        } catch (IOException e) {
            System.out.println("Errore duraente la scrittua del file");
            e.printStackTrace();
        }
    }

}
