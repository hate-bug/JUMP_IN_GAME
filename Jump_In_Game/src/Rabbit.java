/**
 * A rabbit class which used to store the rabbit name and it's location (x1, y1, x2, y2)
 * @author Jiawei Ma
 *
 */
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
	 * @return the enum value of Rabbit name
	 */
	public pieceName getName () {
		return this.name;
	}

	@Override
	public void setLocation(Tuple location) {
		this.location = location;
		
	}

}