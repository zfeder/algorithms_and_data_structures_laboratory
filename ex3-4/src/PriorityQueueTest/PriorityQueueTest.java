package PriorityQueueTest;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.Comparator;
import PriorityQueue.PriorityQueue;

/**
 * Classe per test della Coda di Priorità
 * @author Federico Filì
 */
public class PriorityQueueTest {
    private PriorityQueue<Integer> priorityQueue;

    @Before
    public void setUp() {
        priorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer val1, Integer val2) {
                return val1 - val2;
            }
        });
    }

    @Test
    public void testCreateEmptyPriorityQueue() {
        assertEquals(0, priorityQueue.getHeap().size());
    }

    @Test
    public void testGetParentByChildIndex() {
        priorityQueue.push(7);
        priorityQueue.push(1);
        priorityQueue.push(3);
        assertEquals(Integer.valueOf(1), priorityQueue.getParentByChildIndex(1));
    }

    @Test
    public void testGetLeftChildByParentIndex() {
        priorityQueue.push(7);
        priorityQueue.push(1);
        priorityQueue.push(3);
        assertEquals(Integer.valueOf(7), priorityQueue.getLeftChildByParentIndex(0));
    }

    @Test
    public void testGetRightChildByParentIndex() {
        priorityQueue.push(7);
        priorityQueue.push(1);
        priorityQueue.push(3);
        assertEquals(Integer.valueOf(3), priorityQueue.getRightChildByParentIndex(0));
    }

    @Test
    public void testPushItem() {
        priorityQueue.push(7);
        priorityQueue.push(1);
        priorityQueue.push(3);
        Integer[] expected = {1, 7, 3};
        assertArrayEquals(expected, priorityQueue.getHeap().toArray());
    }

    @Test
    public void testPopItem() {
        priorityQueue.push(7);
        priorityQueue.push(1);
        priorityQueue.push(3);
        priorityQueue.pop();
        Integer[] expected = {3, 7};
        assertArrayEquals(expected, priorityQueue.getHeap().subList(0, priorityQueue.getSize()).toArray());
    }

    @Test
    public void testTopItem() {
        priorityQueue.push(7);
        priorityQueue.push(1);
        priorityQueue.push(3);
        assertEquals(Integer.valueOf(1), priorityQueue.top());
    }

    @Test
    public void testRemoveItem() {
        priorityQueue.push(7);
        priorityQueue.push(1);
        priorityQueue.push(3);
        priorityQueue.remove(7);
        Integer[] expected = {1, 3};
        assertArrayEquals(expected, priorityQueue.getHeap().subList(0, priorityQueue.getSize()).toArray());
    }

    @Test
    public void testEmpty() {
        assertTrue(priorityQueue.empty());

    }

    @Test
    public void testContainsItem() {
        priorityQueue.push(1);
        assertTrue(priorityQueue.contains(1));
        assertFalse(priorityQueue.contains(2));
    }




}
