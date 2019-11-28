import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Undo_Test {
	private Jump_IN_Model model;
	private Piece f1, f2, m1, m2, r1, r2, r3;
	
	@BeforeEach
	void setUp() throws Exception {
		model = new Jump_IN_Model();
		f1 = new Fox (Piece.pieceName.F1); 
		f2 = new Fox (Piece.pieceName.F2); 
		r1 = new Rabbit (Piece.pieceName.R1);
		r2 = new Rabbit (Piece.pieceName.R2);
		r3 = new Rabbit (Piece.pieceName.R3);
		m1 = new Mushroom(Piece.pieceName.M1);
		m2 = new Mushroom(Piece.pieceName.M2);
		model.putPiece(f1, new Tuple (0, 1, 1, 1)); // fox 1
		model.putPiece(f2, new Tuple (3, 3, 3, 4)); // fox 2
		model.putPiece(m1, new Tuple (1, 3)); // mushroom 1 
		model.putPiece(m2, new Tuple (4, 2)); // mushroom 2
		model.putPiece(r1, new Tuple (0, 3)); // Rabbit 1
		model.putPiece(r2, new Tuple (2, 4)); // Rabbit 2
		model.putPiece(r3, new Tuple (4, 1)); // Rabbit 3
		model.setupBoard();
	}

	@Test
	public void Undo_Test1() {
		this.model.setSelectedPiece(f1.getName());
		this.model.movePiece(f1, new Tuple (3, 1, 2, 1));//move fox down
		assertEquals(true, this.model.rollBack()); 
		assertEquals(f1.getName(), this.model.getPiece(new Tuple(0, 1, 1, 1)));//f1 should go back
	}
	
	@Test
	public void Undo_Test2() {
		this.model.setSelectedPiece(r3.getName());
		assertEquals(false, this.model.rollBack());//Undo in the beginning, should get false
		this.model.movePiece(r3, new Tuple(4, 3));
		assertEquals(true, this.model.rollBack());
		assertEquals(r3.getName(), this.model.getPiece(new Tuple (4,1))); //r3 should be return back 
	}

}
