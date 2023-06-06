package AbstractGraphTest;

import AbstractGraph.Graph;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Classe per test del Grafo
 * @author Federico Filì
 */
public class AbstractGraphTest {
    private Graph<String, Double> graph_undirected;
    private Graph<String, Double> graph_directed;

    @Before
    public void setUp() {
        graph_undirected = new Graph<String, Double>(false, true);
        graph_directed = new Graph<String, Double>(true, true);
    }

    @Test
    public void testGraphSizeEmpty(){
        assertEquals(0, graph_undirected.numNodes());
        assertEquals(0, graph_undirected.numEdges());
    }

    @Test
    public void testGraphSizeOneElement(){
        graph_undirected.addNode("A");
        assertEquals(1, graph_undirected.numNodes());
        assertEquals(0, graph_undirected.numEdges());
    }

    @Test
    public void testGraphSizeTwoElementsOneEdge(){
        graph_undirected.addNode("A");
        graph_undirected.addNode("B");
        graph_undirected.addEdge("A", "B", 1.1);
        assertEquals(2, graph_undirected.numNodes());
        assertEquals(1, graph_undirected.numEdges());
    }

    @Test
    public void testGraphSizeMoreElementsMoreEdges(){
        graph_undirected.addNode("A");
        graph_undirected.addNode("B");
        graph_undirected.addNode("C");
        graph_undirected.addNode("D");
        graph_undirected.addEdge("A", "B", 1.1);
        graph_undirected.addEdge("A", "D", 2.2);
        graph_undirected.addEdge("B", "C", 3.3);
        assertEquals(4, graph_undirected.numNodes());
        assertEquals(3, graph_undirected.numEdges());
    }

    @Test
    public void testGraphUndirected(){
        graph_undirected.addNode("A");
        graph_undirected.addNode("B");
        graph_undirected.addNode("C");
        graph_undirected.addNode("D");
        graph_undirected.addEdge("A", "B", 1.1);
        graph_undirected.addEdge("A", "D", 2.2);
        graph_undirected.addEdge("B", "C", 3.3);
        assertTrue(graph_undirected.containsEdge("A", "B"));
        assertTrue(graph_undirected.containsEdge("B", "A"));
    }

    @Test
    public void testGraphDirected(){
        graph_directed.addNode("A");
        graph_directed.addNode("B");
        graph_directed.addNode("C");
        graph_directed.addNode("D");
        graph_directed.addEdge("A", "B", 1.1);
        graph_directed.addEdge("A", "D", 2.2);
        graph_directed.addEdge("B", "C", 3.3);
        assertTrue(graph_directed.containsEdge("A", "B"));
        assertFalse(graph_directed.containsEdge("B", "A"));
    }

    @Test
    public void testGraphNodes(){
        graph_undirected.addNode("A");
        graph_undirected.addNode("B");
        graph_undirected.addNode("C");
        graph_undirected.addNode("D");
        graph_undirected.addEdge("A", "B", 1.1);
        graph_undirected.addEdge("A", "D", 2.2);
        graph_undirected.addEdge("B", "C", 3.3);
        assertTrue(graph_undirected.containsNode("A"));
        assertTrue(graph_undirected.containsNode("B"));
        assertTrue(graph_undirected.containsNode("C"));
        assertTrue(graph_undirected.containsNode("D"));
    }

    @Test
    public void testGraphEdges(){
        graph_undirected.addNode("A");
        graph_undirected.addNode("B");
        graph_undirected.addNode("C");
        graph_undirected.addNode("D");
        graph_undirected.addEdge("A", "B", 1.1);
        graph_undirected.addEdge("A", "D", 2.2);
        graph_undirected.addEdge("B", "C", 3.3);
        assertTrue(graph_undirected.containsEdge("A", "B"));
        assertTrue(graph_undirected.containsEdge("B", "A"));
        assertTrue(graph_undirected.containsEdge("A", "D"));
        assertTrue(graph_undirected.containsEdge("D", "A"));
        assertTrue(graph_undirected.containsEdge("B", "C"));
        assertTrue(graph_undirected.containsEdge("C", "B"));
    }

    @Test
    public void testGraphEdgesDirected(){
        graph_directed.addNode("A");
        graph_directed.addNode("B");
        graph_directed.addNode("C");
        graph_directed.addNode("D");
        graph_directed.addEdge("A", "B", 1.1);
        graph_directed.addEdge("A", "D", 2.2);
        graph_directed.addEdge("B", "C", 3.3);
        assertTrue(graph_directed.containsEdge("A", "B"));
        assertFalse(graph_directed.containsEdge("B", "A"));
        assertTrue(graph_directed.containsEdge("A", "D"));
        assertFalse(graph_directed.containsEdge("D", "A"));
        assertTrue(graph_directed.containsEdge("B", "C"));
        assertFalse(graph_directed.containsEdge("C", "B"));
    }

    @Test
    public void testGraphRemoveNode(){
        graph_undirected.addNode("A");
        graph_undirected.addNode("B");
        graph_undirected.addNode("C");
        graph_undirected.addNode("D");
        graph_undirected.addEdge("A", "B", 1.1);
        graph_undirected.addEdge("A", "D", 2.2);
        graph_undirected.addEdge("B", "C", 3.3);
        assertTrue(graph_undirected.removeNode("A"));
        assertFalse(graph_undirected.containsNode("A"));
        assertFalse(graph_undirected.containsEdge("A", "B"));
        assertFalse(graph_undirected.containsEdge("B", "A"));
        assertFalse(graph_undirected.containsEdge("A", "D"));
        assertFalse(graph_undirected.containsEdge("D", "A"));
    }

    @Test
    public void testGraphRemoveEdge(){
        graph_undirected.addNode("A");
        graph_undirected.addNode("B");
        graph_undirected.addNode("C");
        graph_undirected.addNode("D");
        graph_undirected.addEdge("A", "B", 1.1);
        graph_undirected.addEdge("A", "D", 2.2);
        graph_undirected.addEdge("B", "C", 3.3);
        assertTrue(graph_undirected.removeEdge("A", "B"));
        assertFalse(graph_undirected.containsEdge("A", "B"));
        assertFalse(graph_undirected.containsEdge("B", "A"));
    }

    @Test
    public void testGraphRemoveEdgeDirected(){
        graph_undirected.addNode("A");
        graph_undirected.addNode("B");
        graph_undirected.addNode("C");
        graph_undirected.addNode("D");
        graph_undirected.addEdge("A", "B", 1.1);
        graph_undirected.addEdge("A", "D", 2.2);
        graph_undirected.addEdge("B", "C", 3.3);
        assertTrue(graph_undirected.removeEdge("A", "B"));
        assertFalse(graph_undirected.containsEdge("A", "B"));
        assertFalse(graph_undirected.containsEdge("B", "A"));
    }
}

