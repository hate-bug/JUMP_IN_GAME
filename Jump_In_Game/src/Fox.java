/**
 * A fox piece which has it name and its location
 * @author defa Lu, Jiawei Ma
 *
 */
public class Fox extends Piece{
	
	private Tuple location; 
	private pieceName name; 
	
	public Fox (pieceName name) {
		this.name = name; 
	}
	
	@Override
	public Tuple getLocation() {
		return this.location;
	}

	@Override
	public pieceName getName() {
		return this.name;
	}

	@Override
	public void setLocation(Tuple location) {
		this.location = location;
	}

}