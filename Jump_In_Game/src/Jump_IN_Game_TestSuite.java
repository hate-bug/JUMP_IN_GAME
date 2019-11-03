import junit.framework.Test;
import junit.framework.TestSuite;

public class Jump_IN_Game_TestSuite extends TestSuite {

	public static void main(String[] args) {
		junit.textui.TestRunner.run(Jump_IN_Game_TestSuite.suite());
	}

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for Jump_IN_Game");
		suite.addTest(new TestSuite(Jump_IN_Model_TestSuite.class));
		suite.addTest(new TestSuite(Jump_IN_Tuple_TestSuite.class));
		return suite;
	}
}