
public class Mushroom implements Piece {
	
	private pieceName name; 
	private Tuple location; 
	
	public Mushroom (pieceName name) {
		this.name = name;
	}
	
	public Tuple getLocation () {
		return this.location;
	}
	
	@Override
	public pieceName getName () {
		return this.name;
	}

	@Override
	public void setLocation(Tuple location) {
		this.location = location;
		
	}	

}
