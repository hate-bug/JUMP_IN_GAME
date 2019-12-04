/**
 * Unit test for File input and output
 */
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Jump_In_IO_Test {

	private Jump_IN_Model model;
	private Jump_IN_Controller controller;
	private Piece f1,r1;
	
	@BeforeEach
	void setUp() throws Exception {
		model = new Jump_IN_Model();
		f1 = new Fox (Piece.pieceName.F1); 
		r1 = new Rabbit (Piece.pieceName.R1);
		controller = new Jump_IN_Controller(new Jump_IN_View(), this.model);
	}
	
	@Test
	public void saveGameTest () {
		assertEquals(true, model.saveBoard("Test_Jump_In"));
		
	}
	
	@Test 
	public void loadGameTest () throws IOException {
		controller.levels(1);
		model.setupBoard();
		assertEquals(f1.getName(), this.model.getPiece(new Tuple(0, 1)));
		assertEquals(r1.getName(), this.model.getPiece(new Tuple(2, 3)));	
	}

}
