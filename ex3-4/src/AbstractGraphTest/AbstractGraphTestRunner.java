package AbstractGraphTest;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * Classe per runner dei test del Grafo
 * @author Federico Filì
 */
public class AbstractGraphTestRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(AbstractGraphTest.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
    }
}
