
public interface Piece {
	public static enum pieceName {R1, R2, R3, F1, F2, M1, M2, FINISH};
	
	public Tuple getLocation();
	
	public pieceName getName ();
	
	public void setLocation (Tuple location);
	
	
}
