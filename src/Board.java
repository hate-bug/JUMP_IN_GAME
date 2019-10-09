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
	 * Initialize the board, add everything to the board including hole
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
	
	public void putPiece (Piece piece, Tuple location) {
		this.pieceLocations.put(piece.getName(), location);
		piece.setLocation(location);
		
	}
	
	public void changePiece (Piece piece, Tuple location) {
		this.pieceLocations.remove(piece.getName());
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
	
	public boolean isOccupied (String name, Tuple location) {
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
}
