/**
 * A class to create and store all commands
 * @author , Junyun Chen
 *
 */
public class Command {

	public static enum CommandWords {UP, DOWN, RIGHT, LEFT};
	
	private Piece.pieceName word1;
	private CommandWords word2;
	private int word3 = -1;
	private boolean isValid = false; //by default, a new created command is not valid
	public Command () {
		
	}
	
	/**
	 * Add command for a fox-move command
	 * Typically, it would be fox Direction Distance
	 * ex: f1 right 3. Means let a fox move three pixels to the right
	 * @param word1
	 * @param word2
	 * @param word3
	 * @return true for command is semantically valid. False for invalid command
	 */
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
	/**
	 * Add command for a rabbit-move command 
	 * Typically, it would be just for rabbit Direction 
	 * ex: r1 right: Means let a rabbit jump to right
	 * @param word1
	 * @param word2
	 * @return True for command is semantically, else return false
	 */
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
	
	/**
	 * Command 1 would be either a fox name or a rabbit name
	 * @return Enum value for the piece name 
	 */
	public Piece.pieceName getCommand1 () {
		return this.word1;
	}

	/**
	 * Command 2 would be the direction of the move 
	 * @return Enum value of the move direction
	 */
	public CommandWords getCommand2 () {
		return this.word2;
	}
	
	/**
	 * If command 3 exists, it would be just a integer
	 * Means the distance fox is going to move
	 * @return
	 */
	public int getCommand3 () {
		return this.word3;
	}
	
	/**
	 * Check if it a valid command 
	 * @return true for command is valid
	 */
	public boolean isValidCommand () {
		return this.isValid;
	}
}
