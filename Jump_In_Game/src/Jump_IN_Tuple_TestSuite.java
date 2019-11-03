import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Jump_IN_Tuple_TestSuite {

	private Tuple a, b;
	
	@BeforeEach
	public void setUp() throws Exception {
		 a = new Tuple (2, 3);
		 b = new Tuple (2, 3, 3, 4);
	}

	@Test
	public void test2Value() {
		assertEquals(2, a.getRowNum());
		assertEquals(3, a.getColNum());
		assertEquals(-100, a.getCol1Num());
		assertEquals(true, a.isValidfor1());
		assertEquals(false, a.isValidfor2());
	}
	
	@Test
	public void test4Value() {
		assertEquals(2, b.getRowNum());
		assertEquals(3, b.getColNum());
		assertEquals(3, b.getRow1Num());
		assertEquals(4, b.getCol1Num());
		assertEquals(true, b.isValidfor1());
		assertEquals(true, b.isValidfor2());
	}

}
