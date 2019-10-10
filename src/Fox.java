
public class Fox implements Piece{
	
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
