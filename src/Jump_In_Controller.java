import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Initialize the game and handle the interaction between player and all game 
 * ALso decide when to finish the game 
 * @author Zhe Ji, Junyuan Chen
 * 
 */
public class Jump_In_Controller {
	
	private Piece f1, f2, m1, m2, r1, r2, r3;  
	private Jump_IN_View view;
	private Jump_IN_Model model; 
	private int level = -1;
	
	
	public Jump_In_Controller (Jump_IN_View view, Jump_IN_Model model) {
		
		this.view = view;
		this.model = model;
		f1 = new Fox (Piece.pieceName.F1); 
		f2 = new Fox (Piece.pieceName.F2); 
		r1 = new Rabbit (Piece.pieceName.R1);
		r2 = new Rabbit (Piece.pieceName.R2);
		r3 = new Rabbit (Piece.pieceName.R3);
		m1 = new Mushroom(Piece.pieceName.M1);
		m2 = new Mushroom(Piece.pieceName.M2);
		this.view.addChooseLevelButtonListener(new chooseLevel1ButtonListener());
		this.view.addNewGameButtonListener(new newGameButtonListener());
		for (int x=0; x<5; x++) {
			for (int y=0; y<5; y++) {
				this.view.addGridButtonListener(x, y, new GridButtonListener(new Tuple(x, y)));
			}
		}
		
	}
		
	/**
	 * Method to start the game
	 */
	public void play () {
		levels(this.level);

	}
	
	public void setLevel (int level) {
		this.level = level;
	}
	
	/**
	 * Method to store the piece initial location on the board 
	 * For now we just have one level, set it to one by default
	 * @param level
	 */
	private void levels (int level) {
		if (level == 1) {
			model.putPiece(f1, new Tuple (0, 1, 1, 1)); // fox 1
			model.putPiece(f2, new Tuple (3, 3, 3, 4)); // fox 2
			model.putPiece(m1, new Tuple (1, 3)); // mushroom 1 
			model.putPiece(m2, new Tuple (4, 2)); // mushroom 2
			model.putPiece(r1, new Tuple (0, 3)); // Rabbit 1
			model.putPiece(r2, new Tuple (2, 4)); // Rabbit 2
			model.putPiece(r3, new Tuple (4, 1)); // Rabbit 3
			this.view.setupButtons(model.setupBoard());
		}
		
		if (level ==2) {
			model.putPiece(f1, new Tuple (0, 3, 1, 3)); // fox 1
			model.putPiece(f2, new Tuple (3, 0, 3, 1)); // fox 2
			model.putPiece(m1, new Tuple (1, 1)); // mushroom 1 
			model.putPiece(m2, new Tuple (1, 2)); // mushroom 2
			model.putPiece(r1, new Tuple (0, 1)); // Rabbit 1
			model.putPiece(r2, new Tuple (3, 4)); // Rabbit 2
			model.putPiece(r3, new Tuple (2, 1)); // Rabbit 3
			this.view.setupButtons(model.setupBoard());
		}
		
	}


	public class GridButtonListener implements ActionListener {
		private Tuple pieceLocation; 
		private Piece piece;
		
		public GridButtonListener (Tuple location) {
			this.pieceLocation = location;
		}
		@Override
		public void actionPerformed(ActionEvent e) {

			if (model.hasSelected()!=null && (model.hasSelected().toString().startsWith("F") || model.hasSelected().toString().startsWith("R"))) { // if a fox has selected, then move the piece
				
				if (model.hasSelected().toString().equals("F1")) {
					this.piece = f1;
				} else if (model.hasSelected().toString().equals("F2")) {
					this.piece = f2;				
				} else if (model.hasSelected().toString().equals("R1")) {
					this.piece = r1;
				} else if (model.hasSelected().toString().equals("R2")) {
					this.piece = r2;
				} else if (model.hasSelected().toString().equals("R3")) {
					this.piece = r3;
				}
				if (model.movePiece(this.piece, this.pieceLocation)) {//valid move, notify view
					model.setSelectedPiece(null);
					view.setSelected("");
					view.setupButtons(model.setupBoard());
					if (model.isFinished()) {
						view.showDialog("Game finished, you win!");
						play();					
					}
				} else { //invalid move, show dialog
					view.showDialog("Invalid Move");
					model.setSelectedPiece(null);
					view.setSelected("");
				}
				
			} else { // set selected in model
				if (model.getPiece(pieceLocation) != null) {
					Piece.pieceName name = model.getPiece(this.pieceLocation);
					model.setSelectedPiece(name);
					view.setSelected(name.toString());
				} else {
					view.showDialog("This item is not allowed to move.");
				}
		
			}
			
		}
	
	}
	
	public class newGameButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (level == -1) {
				view.setLabel("Please set level first.", Color.RED);
			} else {
				play();
			}
			
		}
		
	}
	
	public class chooseLevel1ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			setLevel(view.levelInputDialog());
			if (level >0) {
				view.setLabel("Level: " + String.valueOf(level), Color.BLUE);
			}
		}
		
	}

}
