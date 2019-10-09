
public class Command {

	public static enum CommandWords {UP, DOWN, RIGHT, LEFT};
	
	private Piece.pieceName word1;
	private CommandWords word2;
	private int word3 = -1;
	private boolean isValid = false; 
	public Command () {
		
	}
			
	public boolean addCommand (String word1, String word2, String word3) {
		try {
			this.word1 = Piece.pieceName.valueOf(word1); 
			this.word2 = CommandWords.valueOf(word2);
			this.word3 = Integer.valueOf(word3);
			this.isValid = true;
		} catch (IllegalArgumentException e) {
			this.isValid = false;
			return false; 
		}
		
		return true;
	}
	
	public boolean addCommand (String word1, String word2) {
		try {
			this.word1 = Piece.pieceName.valueOf(word1); 
			this.word2 = CommandWords.valueOf(word2);
			this.isValid = true;
		} catch (IllegalArgumentException e) {
			this.isValid = false;
			return false; 
		}
		
		return true;
	}
	
	public Piece.pieceName getCommand1 () {
		return this.word1;
	}

	public CommandWords getCommand2 () {
		return this.word2;
	}
	
	public int getCommand3 () {
		return this.word3;
	}
	
	public boolean isValidCommand () {
		return this.isValid;
	}
}
