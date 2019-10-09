
public class Rabbit implements Piece{
	
	private Tuple location;
	private pieceName name; 
	
	public Rabbit (pieceName name) {
		this.name = name;
	}
	
	/**
	 * Handle the jump of a rabbit. ex: up
	 * @param direction 
	 */
	public Tuple jump (Command.CommandWords direction, Board board) {
		int x = this.location.getRowNum();
		int y = this.location.getColNum();
		
		if (direction == Command.CommandWords.UP && x-2 >=0 && board.isOccupied(this.name.toString(), new Tuple (x-1, y))) {
			//return new Tuple (x-1, y, x-2, y);
			int i=1;
			while (board.isOccupied(this.name.toString(), new Tuple (x-i, y))) {
				if (x-i <0) {
					return new Tuple (-1, -1);
				} else if (!board.isOccupied(this.name.toString(), new Tuple (x-i, y))) {
					return new Tuple (x-i, y);
				}
				i++;
			}
			return new Tuple (x-i, y);
		} else if (direction == Command.CommandWords.DOWN && x+2<5 && board.isOccupied(this.name.toString(), new Tuple (x+1, y))) {
			//return new Tuple(x+1, y, x+2, y);
			int i=1;
			while (board.isOccupied(this.name.toString(), new Tuple (x+i, y))) {
				if (x+i >4) {
					return new Tuple (-1, -1);
				} else if (!board.isOccupied(this.name.toString(), new Tuple (x+i, y))) {
					return new Tuple (x+i, y);
				}
				i++;
			}
			return new Tuple(x+i, y);
		} else if (direction == Command.CommandWords.LEFT && y-2>=0 && board.isOccupied(this.name.toString(), new Tuple (x, y-1))) {
			//return new Tuple(x, y-1, x, y-2);
			int i=1;
			while (board.isOccupied(this.name.toString(), new Tuple (x, y-i))) {

				if (y-i <0) {
					return new Tuple (-1, -1);
				} else if (!board.isOccupied(this.name.toString(), new Tuple (x, y-i))) {
					return new Tuple (x, y-i);
				}
				i++;
			}
			return new Tuple (x, y-i);
			
		} else if (direction == Command.CommandWords.RIGHT && y+2<5 && board.isOccupied(this.name.toString(), new Tuple (x, y+1))) {
			//return new Tuple(x, y+1, x, y+2);
			int i=1;
			while (board.isOccupied(this.name.toString(), new Tuple (x, y+i))) {
			
				if (y+i >4) {
					return new Tuple (-1, -1);
				} else if (!board.isOccupied(this.name.toString(), new Tuple (x, y+i))) {
					return new Tuple (x, y+i);
				}
				i++;
			}
			return new Tuple(x, y+i);
			
		}
		return new Tuple (-1, -1);
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

	@Override
	public boolean equals(Piece temp) {
		if (temp.getName() == this.name) {
			return true;
		}
		return false;
	}
}
