/**
 * The board is the model of the game, stores all pieces on it and decide if player can move the piece to desired place 
 * @author zhe Ji, XiLing Wang, Junyuan Chen, Defa Hu, Jiawei Ma 
 */
import java.util.HashMap;
import java.util.Map;


public class Jump_IN_Model {
	private final static int row=5;
	private final static int column=5;
	private final static String hole = "O";
	private String[][] grid;
	private HashMap<Piece.pieceName, Tuple> pieceLocations;
	private Piece.pieceName selected=null;
	public Jump_IN_Model () {
		
		pieceLocations = new HashMap<Piece.pieceName, Tuple>();
		
	}
	
	/**
	 * Initialize the board, add everything to the board including hole, dots and pieces
	 */
	public String[][] setupBoard () {
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
		
		return this.grid;
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
		
	/**
	 * Set the selected piece 
	 * @param piece
	 */
	public void setSelectedPiece (Piece.pieceName piece) {
		this.selected = piece;
	}
	
	/**
	 * Check if selected piece is null
	 */
	public Piece.pieceName hasSelected () {
		
		if (this.selected != null) {
			return this.selected;
		} else {
			return null;
		}
	}
	
	/**
	 * Determine if the game is finished or not
	 * @return true for finished, false for not finished
	 */
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
	 * Return the selected piece name
	 * @param location
	 * @return
	 */
	public Piece.pieceName getPiece (Tuple location) {
		int x = location.getRowNum();
		int y = location.getColNum();
		String pieceName = this.grid[x][y]; 
		if (pieceName.startsWith("F") || pieceName.startsWith("R")) {
			return Piece.pieceName.valueOf(pieceName);
		} else {
			return null;
		}
	}
	
	public boolean movePiece (Piece piece, Tuple destination) {
		String pieceName = this.selected.toString();
		destination = new Tuple (destination.getRowNum(), destination.getColNum());
		if (!isOccupied(pieceName, destination)) {//destination is not occupied
			if (pieceName.startsWith("F")) {
				Tuple currentLocation = this.pieceLocations.get(this.selected);
				int fromx1 = currentLocation.getRowNum();
				int fromy1 = currentLocation.getColNum();
				int fromx2 = currentLocation.getRow1Num();
				int fromy2 = currentLocation.getCol1Num();
				int tox1 = destination.getRowNum();
				int toy1 = destination.getColNum();
				if ((fromx1==tox1 && fromy1==toy1) || (fromx2==tox1 && fromy2==toy1)) {
					return true;
				}
				
				if ((fromy1 == fromy2) && (toy1 == fromy1)) { // vertical move
					for (int x = Math.min(Math.min(fromx1, fromx2), tox1); x< Math.max(Math.max(fromx1, fromx2), tox1)+1; x++) {
						if (isOccupied(pieceName, new Tuple(x, fromy1))) {
							return false;
						}
					}
					if (tox1 > fromx1) {//move down
						int tox2 = tox1-1; 
						int toy2 = toy1;
						destination.setRow1Num(tox2);
						destination.setCol1Num(toy2);
						putPiece(piece, destination);
						return true;
					}
					if (tox1 < fromx1) { // move up
						int tox2 = tox1+1; 
						int toy2 = toy1;
						destination.setRow1Num(tox2);
						destination.setCol1Num(toy2);
						putPiece(piece, destination);
						return true;
					}
		
					
				} else if ((fromx1 == fromx2) && (tox1 == fromx1)) { // horizontal move
					for (int x = Math.min(Math.min(fromy1, fromy2), toy1); x< Math.max(Math.max(fromy1, fromy2)+1, toy1); x++) {
						if (isOccupied(pieceName, new Tuple(fromx1,x))) {
							return false;
						}
					}
					if (toy1 > fromy1) { // move right
						int toy2 = toy1 -1; 
						int tox2 = tox1; 
						destination.setRow1Num(tox2);
						destination.setCol1Num(toy2);
						putPiece(piece, destination);
						return true;
					}
					
					if (toy1 < fromy1) { //move left
						int toy2 = toy1 + 1; 
						int tox2 = tox1; 
						destination.setRow1Num(tox2);
						destination.setCol1Num(toy2);
						putPiece(piece, destination);
						return true;
					}
				}
				
				
				
			} else if (pieceName.startsWith("R")) { //move a rabbit
				Tuple currentLocation = this.pieceLocations.get(this.selected);
				int fromx = currentLocation.getRowNum();
				int fromy = currentLocation.getColNum();
				int tox = destination.getRowNum();
				int toy = destination.getColNum();
				if (fromx==tox && fromy==toy) {
					return true;
				}
				if (fromy == toy && (Math.abs(fromx-tox)>1)) { // vertical jump
					for (int x=Math.min(fromx, tox)+1; x<Math.max(fromx, tox); x++) {
						if (!isOccupied(pieceName, new Tuple (x, fromy))){ // if no piece in between, invalid move
							return false;
						}
					}
					putPiece(piece, destination);
					return true;
				} 
				if (fromx == tox && (Math.abs(fromy-toy)>1)) { // horizontal jump
					for (int y=Math.min(fromy, toy)+1; y<Math.max(fromy, toy); y++) {
						if (!isOccupied(pieceName, new Tuple (fromx, y))){ // if no piece in between, invalid move
							return false;
						}
					}
					putPiece(piece, destination);
					return true;
				}
			}
		}
		
		return false;
	}

}