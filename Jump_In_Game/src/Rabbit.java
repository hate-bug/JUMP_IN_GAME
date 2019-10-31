/**
 * A rabbit class which used to store the rabbit name and it's location (x1, y1, x2, y2)
 * @author Jiawei Ma
 *
 */
public class Rabbit extends Piece{
	
	private Tuple location;
	private pieceName name; 
	
	public Rabbit (pieceName name) {
		this.name = name;
	}

	@Override
	public void setLocation(Tuple location) {
		this.location = location;
		
	}

}