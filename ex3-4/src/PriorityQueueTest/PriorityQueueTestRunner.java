package PriorityQueueTest;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * Classe per runner dei test della Coda di Priorità
 * @author Federico Filì
 */
public class PriorityQueueTestRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(PriorityQueueTest.class);
        for(Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
    }
}
