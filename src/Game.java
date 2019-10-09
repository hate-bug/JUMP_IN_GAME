
public class Game {
	
	private Board board; 
	private Fox f1, f2; 
	private Mushroom m1, m2; 
	private Rabbit r1, r2, r3;
	private CommandParser parser; 
	
	public Game () {
		this.board = new Board();
		f1 = new Fox (Piece.pieceName.F1); 
		f2 = new Fox (Piece.pieceName.F2); 
		r1 = new Rabbit (Piece.pieceName.R1);
		r2 = new Rabbit (Piece.pieceName.R2);
		r3 = new Rabbit (Piece.pieceName.R3);
		m1 = new Mushroom(Piece.pieceName.M1);
		m2 = new Mushroom(Piece.pieceName.M2);
		parser = new CommandParser();
		
	}
	
	public void play () {
		levels(1);
		System.out.println(this.board.printBoard());
		boolean finish = false;
		while (! finish) {
			System.out.println("Please input command.");
			this.processCommands(parser.getCommand());
			System.out.println(this.board.printBoard());
			finish = this.board.isFinished();
		}
		System.out.println("You win!!!");
	}
	
	private void levels (int level) {
		if (level == 1) {
			board.putPiece(f1, new Tuple (0, 1, 1, 1)); // fox 1
			board.putPiece(f2, new Tuple (3, 3, 3, 4)); // fox 2
			board.putPiece(m1, new Tuple (1, 3)); // mushroom 1 
			board.putPiece(m2, new Tuple (4, 2)); // mushroom 2
			board.putPiece(r1, new Tuple (0, 3)); // Rabbit 1
			board.putPiece(r2, new Tuple (2, 4)); // Rabbit 2
			board.putPiece(r3, new Tuple (4, 1)); // Rabbit 3
		}
		
	}
	
	private void processCommands (Command commands) {
				
		if (!commands.isValidCommand()) {
			System.out.println ("Invalid input");
			return;
		}
		
		Tuple newLocation, newLocation1, newLocation2;
		Piece.pieceName command1 = commands.getCommand1();
		Command.CommandWords command2 = commands.getCommand2();
		int command3 = commands.getCommand3();

		if (command1 == Piece.pieceName.F1) {
			newLocation = f1.slide(command2, command3);
			if (newLocation.isValid() && !this.board.isOccupied(command1.toString(), newLocation)) {
				board.changePiece(f1, newLocation);
			} else {
				System.out.println("Invalid move.");
			}
			
		} else if (commands.getCommand1() == Piece.pieceName.F2) {
			newLocation = f2.slide(command2, command3);
			if (newLocation.isValid() && !this.board.isOccupied(command1.toString(), newLocation)) {
				board.changePiece(f2, newLocation);
			} else {
				System.out.println("Invalid move.");
			}
			
		} else if (commands.getCommand1() == Piece.pieceName.R1) {
			newLocation = r1.jump(command2, board);
			if (newLocation.isValid()) {
				board.changePiece(r1, newLocation);
			} else {
				System.out.println("Invalid move.");
			}

			
		} else if (commands.getCommand1() == Piece.pieceName.R2) {
			newLocation = r2.jump(command2, board);
			if (newLocation.isValid()) {
				board.changePiece(r2, newLocation);
			} else {
				System.out.println("Invalid move.");
			}
			
		} else if (commands.getCommand1() == Piece.pieceName.R3) {
			newLocation = r3.jump(command2, board);
			if (newLocation.isValid()) {
				board.changePiece(r3, newLocation);
			} else {
				System.out.println("Invalid move.");
			}
		
		}
	}
}
