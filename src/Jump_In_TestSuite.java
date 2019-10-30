import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class Jump_In_TestSuite {
	private Jump_IN_Model model;
	private Piece f1, f2, m1, m2, r1, r2, r3;  
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@Before
	public void setUp() throws Exception {
		model = new Jump_IN_Model();
		f1 = new Fox (Piece.pieceName.F1); 
		f2 = new Fox (Piece.pieceName.F2); 
		r1 = new Rabbit (Piece.pieceName.R1);
		r2 = new Rabbit (Piece.pieceName.R2);
		r3 = new Rabbit (Piece.pieceName.R3);
		m1 = new Mushroom(Piece.pieceName.M1);
		m2 = new Mushroom(Piece.pieceName.M2);
	}

	@Test
	public void test() {
		model.setSelectedPiece(f1.getName());
		assertTrue(model.movePiece(f1, new Tuple (2, 1)));
	}

}
