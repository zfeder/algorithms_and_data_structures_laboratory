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
        if(graph == null)
            return null;

        // minimumForest rappresenta la foresta di copertura minima
        Graph<V, L> minimumForest = new Graph<V, L>(graph.isDirected(), graph.isLabelled());

        // nodeVisited per tenere traccia dei nodi visitati
        HashSet<V> nodeVisited = new HashSet<>();

        // comparatore per confrontare gli archi in base all'etichetta
        Comparator<Edge<V, L>> comparator = Comparator.comparingDouble(e -> e.getLabel().doubleValue());

        // coda di priorità per selezionare gli archi con etichetta minima
        PriorityQueue<Edge<V, L>> priorityQueue = new PriorityQueue<>(comparator);

        // iterazione sui tutti nodi del grafo
        for (V node : graph.getNodes()) {
            if (!nodeVisited.contains(node)) {
                // selezione di un nodo non visitato come radice e aggiunto all'insieme per indicare che è stato visitato
                nodeVisited.add(node);

                // aggiunta di archi adiacenti al nodo radice nella coda di priorità
                for (V neighbor : graph.getNeighbours(node)) {
                    L label = graph.getLabel(node, neighbor);
                    if (label != null) {
                        priorityQueue.push(new Edge<>(node, neighbor, label));
                    }
                }

                while (!priorityQueue.empty()) {
                    // estrazione dell'arco con etichetta minima
                    AbstractEdge<V, L> minEdge = priorityQueue.top();
                    priorityQueue.pop();

                    V start = minEdge.getStart();
                    V end = minEdge.getEnd();

                    // controlla se il nodo di destinazione è già stato visitato
                    if (nodeVisited.contains(end)) {
                        continue;
                    }

                    // aggiunta del nodo di destinazione all'insieme dei nodi visitati
                    nodeVisited.add(end);

                    // aggiunta dell'arco al risultato
                    minimumForest.addNode(start);
                    minimumForest.addNode(end);
                    minimumForest.addEdge(start, end, minEdge.getLabel());

                    // aggiunta di archi adiacenti al nodo di destinazione nella coda di priorità
                    for (V neighbor : graph.getNeighbours(end)) {
                        L label = graph.getLabel(end, neighbor);
                        if (label != null) {
                            priorityQueue.push(new Edge<>(end, neighbor, label));
                        }
                    }
                }
            }
        }

        // Stampa il numero di nodi e archi calcolato
        System.out.println("**************************************************");
        System.out.println("*    Dati verificati all'interno di Prim         *");
        System.out.println("*    Numero di Nodi: " + minimumForest.numNodes() + "                       *");
        System.out.println("*    Numero di Archi: " + minimumForest.numEdges() + "                      *");
        System.out.println("**************************************************\n");

        // collezione di archi
        return minimumForest.getEdges();
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
        System.out.format("*    Peso totale: %.3f km", sum/2000);
        System.out.println("                   *");
        System.out.println("**************************************************\n");
        try {
            List<AbstractEdge<String, Double>> sortedPrim = new ArrayList<>(prim);

            Collections.sort(sortedPrim, (e1, e2) -> e1.toString().compareTo(e2.toString()));

            String fileOutput = "/Users/feder/Desktop/laboratorio-algoritmi-2022-2023/ex3-4/src/AbstractGraph/output.csv";
            FileWriter outputWriter = new FileWriter(fileOutput);
            for (AbstractEdge<String, Double> e : sortedPrim) {
                outputWriter.write(e.toString() + "\n");
            }
            outputWriter.close();
            System.out.println("Scrittura su file completata con successo.");
        } catch (IOException e) {
            System.out.println("Errore durante la scrittura del file");
            e.printStackTrace();
        }
    }

}

