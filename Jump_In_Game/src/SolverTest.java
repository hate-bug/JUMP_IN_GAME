import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SolverTest {

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
	}

	@Test
	/**
	 * In this step, we are in the last step before win. Just need to Move R1 left. 
	 *   •	 •	 •	 •	 •
	 *   
	 *   •	 •	 •	 M1	 •
	 *   
	 *   •   • 	 R2  •   •
	 *   
	 *   •   F1  •   F2  F2
	 *   
	 *   •   F1  M2  R1  R3
	 */ 
	public void LastStepTest() {
		model.putPiece(f1, new Tuple (3, 1, 4, 1)); // fox 1
		model.putPiece(f2, new Tuple (3, 3, 3, 4)); // fox 2
		model.putPiece(m1, new Tuple (1, 3)); // mushroom 1 
		model.putPiece(m2, new Tuple (4, 2)); // mushroom 2
		model.putPiece(r1, new Tuple (4, 3)); // Rabbit 1
		model.putPiece(r2, new Tuple (2, 2)); // Rabbit 2
		model.putPiece(r3, new Tuple (4, 4)); // Rabbit 3
		model.setupBoard();
		assertEquals("Move R1 left. ", model.findSolver()); 
	}
	
	@Test
	/**
	 * In this step, we are in the last two step before win. Just need to  Move F1 down 3 positions, then move R1 left. 
	 *   •	 F1	 •	 •	 •
	 *   
	 *   •	 F1	 •	 M1	 •
	 *   
	 *   •   • 	 R2  •   •
	 *   
	 *   •   •   •   F2  F2
	 *   
	 *   •   •   M2  R1  R3
	 */ 
	public void LasttwoStepTest() {
		model.putPiece(f1, new Tuple (0, 1, 1, 1)); // fox 1
		model.putPiece(f2, new Tuple (3, 3, 3, 4)); // fox 2
		model.putPiece(m1, new Tuple (1, 3)); // mushroom 1 
		model.putPiece(m2, new Tuple (4, 2)); // mushroom 2
		model.putPiece(r1, new Tuple (4, 3)); // Rabbit 1
		model.putPiece(r2, new Tuple (2, 2)); // Rabbit 2
		model.putPiece(r3, new Tuple (4, 4)); // Rabbit 3
		model.setupBoard();
		assertEquals("Move F1 down 3position.Move R1 left. ", model.findSolver()); 
	}
	
	
	/**
	 * In this step, we are in the last three steps before win. 
	 * Just need to  Move F1 down 3 positions, then move R1 left, move R3 down.
	 * 	 *   F1	  • 	•	 •	
	 *  
	 *   •	 F1	  •	   M1	 •
	 *   
	 *   •   • 	  R2   R1     •
	 *   
	 *   •   •    •    F2    F2
	 *   
	 *   •   R3    M2   •    •
	 */
	@Test
	public void LastFourStepTest() {
		model.putPiece(f1, new Tuple (0, 1, 1, 1)); // fox 1
		model.putPiece(f2, new Tuple (3, 3, 3, 4)); // fox 2
		model.putPiece(m1, new Tuple (1, 3)); // mushroom 1 
		model.putPiece(m2, new Tuple (4, 2)); // mushroom 2
		model.putPiece(r1, new Tuple (2, 3)); // Rabbit 1
		model.putPiece(r2, new Tuple (2, 2)); // Rabbit 2
		model.putPiece(r3, new Tuple (2, 4)); // Rabbit 3
		model.setupBoard();
		assertEquals("Move R1 down.Move R3 down.Move F1 down 3position.Move R1 left. ", model.findSolver()); 
	}
	
}
