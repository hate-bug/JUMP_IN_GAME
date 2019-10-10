
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
	
	public void init () {
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
	
		Piece.pieceName command1 = commands.getCommand1();
		Command.CommandWords command2 = commands.getCommand2();
		int command3 = commands.getCommand3();

		if (command1 == Piece.pieceName.F1) {
			
			if (!board.moveFox(this.f1, command2, command3)) {
				System.out.println("Invalid move.");
			}
			
		} else if (commands.getCommand1() == Piece.pieceName.F2) {
			if (!board.moveFox(this.f2, command2, command3)) {
				System.out.println("Invalid move.");
			}
			
		} else if (commands.getCommand1() == Piece.pieceName.R1) {
			if (!board.moveRabbit(this.r1, command2)) {
				System.out.println("Invalid move.");
			}

			
		} else if (commands.getCommand1() == Piece.pieceName.R2) {
			if (!board.moveRabbit(this.r2, command2)) {
				System.out.println("Invalid move.");
			}
			
		} else if (commands.getCommand1() == Piece.pieceName.R3) {
			if (!board.moveRabbit(this.r3, command2)) {
				System.out.println("Invalid move.");
			}

		
		}
	}
}