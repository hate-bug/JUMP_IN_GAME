
public class Rabbit implements Piece{
	
	private Tuple location;
	private pieceName name; 
	
	public Rabbit (pieceName name) {
		this.name = name;
	}
	
	/**
	 * Getter for current location
	 * @return 2-D array which represents current location
	 */
	public Tuple getLocation () {
		return this.location;
	}
	
	/**
	 * Getter for the current rabbit name
	 * @return
	 */
	public pieceName getName () {
		return this.name;
	}

	@Override
	public void setLocation(Tuple location) {
		this.location = location;
		
	}

}
