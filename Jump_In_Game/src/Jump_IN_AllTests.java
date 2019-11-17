import junit.framework.Test;
import junit.framework.TestSuite;

public class Jump_IN_AllTests extends TestSuite {

	public static void main(String[] args) {
		junit.textui.TestRunner.run(Jump_IN_AllTests.suite());
	}

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for com.saorsa.nowplaying.tests");
		suite.addTest(new TestSuite(Jump_IN_Model_TestSuite.class));
		suite.addTest(new TestSuite(Jump_IN_Tuple_TestSuite.class));
		return suite;
	}
}