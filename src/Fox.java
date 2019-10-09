
public class Fox implements Piece{
	
	private Tuple location; 
	private pieceName name; 
	
	public Fox (pieceName name) {
		this.name = name; 
	}
	
	/**
	 * Handle the move of a fox. ex: Slide right 2 
	 * @param direction the direction to slide
	 * @param indexNum the distance to slide
	 * @return true for move successfully, false for illegal move. 
	 */
	public Tuple slide (Command.CommandWords direction, int indexNum) {
		int x1 = this.location.getRowNum();
		int y1 = this.location.getColNum();
		int x2 = this.location.getRow1Num();
		int y2 = this.location.getCol1Num();
		
		if (direction == Command.CommandWords.UP) {
			if (y1 == y2 && Math.min(x1, x2)-indexNum >= 0) {
				
				return new Tuple (x1-indexNum, y1, x2-indexNum, y2);
			}

		} else if (direction == Command.CommandWords.DOWN) {
			if (y1 == y2 && Math.max(x1, x2)+indexNum < 5) {
				
				return new Tuple (x1+indexNum, y1, x2+indexNum, y2);
			} 
		} else if (direction == Command.CommandWords.LEFT) {
			if (x1 == x2 && Math.min(y1, y2)-indexNum >= 0) {
			
				return new Tuple (x1, y1-indexNum, x2, y2-indexNum);
			}
			
		} else if (direction == Command.CommandWords.RIGHT) {
			if (x1 == x2 && Math.max(y1, y2)+indexNum < 5) {
				
				return new Tuple (x1, y1+indexNum, x2, y2+indexNum);
			}
		}
		
		return new Tuple (-1, -1, -1, -1);
		
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

	@Override
	public boolean equals(Piece temp) {
		if (temp.getName() == this.name) {
			return true;
		}
		return false;
	}

}
