import java.io.Serializable;

/**
 * An abstract class for all pieces on the board
 * @author zheji
 *
 */
public abstract class Piece implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6507562078086668868L;

	public static enum pieceName {R1, R2, R3, F1, F2, M1, M2, FINISH};
	private Tuple location;
	private pieceName name;
	
	public Piece (pieceName name) {
		this.name = name;
	}
	
	public Tuple getLocation() {
		return this.location;
	}
	
	public pieceName getName () {
		return this.name;
	}
	
	public void setLocation (Tuple location) {
		this.location = location;
	}
	
	
}