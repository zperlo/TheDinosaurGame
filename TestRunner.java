import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
    public static void main(String[] args) {
        Result playerResult = JUnitCore.runClasses(TestPlayer.class);
        Result gameResult = JUnitCore.runClasses(TestGame.class);

        for (Failure failure : playerResult.getFailures()) {
            System.out.println(failure.toString());
        }
        for (Failure failure : gameResult.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println("\n\n");
        System.out.println("Testing Player returns: " + playerResult.wasSuccessful());
        System.out.println("Testing Game returns: " + gameResult.wasSuccessful());
    }
}  	