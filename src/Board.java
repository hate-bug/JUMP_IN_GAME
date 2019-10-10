import java.util.HashMap;
import java.util.Map;


public class Board {
	private final static int row=5;
	private final static int column=5;
	private final static String rowSpace = "\t";
	private final static String columnSpace = "\n\n\n";
	private final static String hole = "O";
	private String[][] grid;
	private HashMap<Piece.pieceName, Tuple> pieceLocations;
	
	public Board () {
		
		pieceLocations = new HashMap<Piece.pieceName, Tuple>();
	}
	
	/**
	 * Initialize the board, add everything to the board including hole, dots and pieces
	 */
	private void setupBoard () {
		this.grid = new String[row][column]; 
		//adding all holes
		this.grid[0][0] = hole;
		this.grid[0][4] = hole;
		this.grid[2][2] = hole;
		this.grid[4][0] = hole;
		this.grid[4][4] = hole;
		
		for (Map.Entry<Piece.pieceName, Tuple> element: this.pieceLocations.entrySet()) {
			String name = element.getKey().toString();
			Tuple location = element.getValue();
			if (location.has4Value()) { //for fox, which consumes two coordinates
				grid[location.getRowNum()][location.getColNum()] = name;
				grid[location.getRow1Num()][location.getCol1Num()] = name;
			} else { // for rest pieces, just use one coordinates
				grid[location.getRowNum()][location.getColNum()] = name;
			}
		}
		
		//adding all the points
		for (int x=0; x<row; x++) {
			for (int y=0; y<column; y++) {
				if (grid[x][y] == null) {
					this.grid[x][y] = "\u2022";
				}
			}
		}
	}
	
	/**
	 * Print the whole board, looping through the grid
	 * @return a simulating board string
	 */
	public String printBoard () {
		setupBoard();
		String boardInfo = ("--------------PlayBoard--------------\n\n");
		for (int x=0; x < row; x++) {
			for (int y=0; y< column; y++) {
				//boardInfo += y;
				boardInfo += this.grid[x][y];
				boardInfo += rowSpace;
			}
			boardInfo += columnSpace;
		}
		return boardInfo;
	}
	
	/**
	 * Put the piece to the board 
	 * Change the hashmap inside the board, hasmap stores pairs of piecesEnum name and their corresponding location (Tuple)
	 * @param piece
	 * @param location
	 */
	public void putPiece (Piece piece, Tuple location) {
		this.pieceLocations.put(piece.getName(), location);
		piece.setLocation(location);
		
	}
		
	public boolean isFinished () {
		int inHole = 0;
		if (!(this.grid[0][0]).equals("O")) {
			inHole ++; 
		}
		if (!(this.grid[0][4]).equals("O")) {
			inHole ++; 
		}
		if (!(this.grid[2][2]).equals("O")) {
			inHole ++; 
		}
		if (!(this.grid[4][0]).equals("O")) {
			inHole ++; 
		}
		if (!(this.grid[4][4]).equals("O")) {
			inHole ++; 
		}
		if (inHole == 3) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Given a piece name and a location. 
	 * If given location has other piece that is not provided piece name, this location is occupied
	 * @param name
	 * @param location
	 * @return True for location occupied, false for not occupied
	 */
	private boolean isOccupied (String name, Tuple location) {
		if (location.has4Value()) {
			boolean aOccupied = true;
			boolean bOccupied = true;
			int x1 = location.getRowNum();
			int y1 = location.getColNum();
			int x2 = location.getRow1Num();
			int y2 = location.getCol1Num();
			String dot1 = this.grid[x1][y1]; 
			String dot2 = this.grid[x2][y2];
			try {
				Piece.pieceName.valueOf(dot1);
			} catch (IllegalArgumentException e) {
				aOccupied = false;
			} 
			try {
				Piece.pieceName.valueOf(dot2);
			} catch (IllegalArgumentException e) {
				bOccupied = false;
			} 
			if (!aOccupied && !bOccupied) { // both not occupied
				return false;
			} else if ((aOccupied || bOccupied) && (dot1.equals(name) || dot2.equals(name))) {
				return false;
			}
			
			return true;
			
		} else {
			int x1 = location.getRowNum();
			int y1 = location.getColNum();
			String dot1 = this.grid[x1][y1];
			try {
				Piece.pieceName.valueOf(dot1);
			} catch (IllegalArgumentException e) {
				return false;
			} 
			if (dot1.equals(name)) {
				return false;
			}
			return true;
		}
	}
	
	/**
	 * Method for move a fox inside the board based on providing direction and distance
	 * @param piece
	 * @param direction
	 * @param distance
	 * @return true for move successfully, false for invalid command
	 */
	public boolean moveFox (Piece piece, Command.CommandWords direction, int distance) {
		int x1 = piece.getLocation().getRowNum();
		int y1 = piece.getLocation().getColNum();
		int x2 = piece.getLocation().getRow1Num();
		int y2 = piece.getLocation().getCol1Num();
		Tuple newLocation = new Tuple(-1, -1, -1, -1); 
		if (direction == Command.CommandWords.UP) {
			if (y1 == y2 && Math.min(x1, x2)-distance >= 0) {
				
				newLocation = new Tuple (x1-distance, y1, x2-distance, y2);
			}

		} else if (direction == Command.CommandWords.DOWN) {
			if (y1 == y2 && Math.max(x1, x2)+distance < 5) {
				
				newLocation = new Tuple (x1+distance, y1, x2+distance, y2);
			} 
		} else if (direction == Command.CommandWords.LEFT) {
			if (x1 == x2 && Math.min(y1, y2)-distance >= 0) {
			
				newLocation = new Tuple (x1, y1-distance, x2, y2-distance);
			}
			
		} else if (direction == Command.CommandWords.RIGHT) {
			if (x1 == x2 && Math.max(y1, y2)+distance < 5) {
				
				newLocation = new Tuple (x1, y1+distance, x2, y2+distance);
			}
		}
		if (newLocation.isValid() && !isOccupied(piece.getName().toString(), newLocation)) {
			putPiece(piece, newLocation);
			return true; 
		}
		return false;
		
	}
	
	/**
	 * Method to move the rabbit, determine if it;s a valid command with providing direction
	 * @param piece
	 * @param direction
	 * @return true for move successfully, false for wrong move
	 */
	public boolean moveRabbit (Piece piece, Command.CommandWords direction) {
		int x = piece.getLocation().getRowNum();
		int y = piece.getLocation().getColNum();
		String name = piece.getName().toString();
		if (direction == Command.CommandWords.UP && x-2 >=0 && isOccupied(name, new Tuple (x-1, y))) {
			//return new Tuple (x-1, y, x-2, y);
			int i=2;
			while (isOccupied(name, new Tuple (x-i, y))) {
				if (x-i <0) {
					return false;
				} else if (!isOccupied(name, new Tuple (x-i, y))) {
					
					putPiece(piece, new Tuple (x-i, y));
					return true;
				}
				i++;
			}
			putPiece(piece, new Tuple (x-i, y));
			return true;
		} else if (direction == Command.CommandWords.DOWN && x+2<5 && isOccupied(name, new Tuple (x+1, y))) {
			//return new Tuple(x+1, y, x+2, y);
			int i=2;
			while (isOccupied(name, new Tuple (x+i, y))) {
				if (x+i >4) {
					return false;
				} else if (!isOccupied(name, new Tuple (x+i, y))) {
					putPiece(piece, new Tuple (x+i, y));
					return true;
				}
				i++;
			}
			putPiece(piece, new Tuple(x+i, y));
			return true;
		} else if (direction == Command.CommandWords.LEFT && y-2>=0 && isOccupied(name, new Tuple (x, y-1))) {
			//return new Tuple(x, y-1, x, y-2);
			int i=2;
			while (isOccupied(name, new Tuple (x, y-i))) {

				if (y-i <0) {
					return false;
				} else if (!isOccupied(name, new Tuple (x, y-i))) {
					putPiece(piece, new Tuple (x, y-i));
					return true;
				}
				i++;
			}
			putPiece(piece, new Tuple (x, y-i));
			return true;
			
		} else if (direction == Command.CommandWords.RIGHT && y+2<5 && isOccupied(name, new Tuple (x, y+1))) {
			//return new Tuple(x, y+1, x, y+2);
			int i=2;
			while (isOccupied(name, new Tuple (x, y+i))) {
			
				if (y+i >4) {
					return false;
				} else if (!isOccupied(name, new Tuple (x, y+i))) {
					putPiece(piece, new Tuple (x, y+i));
					return true;
				}
				i++;
			}
			putPiece(piece, new Tuple(x, y+i));
			return true;
			
		}
		return false;
	}
}
