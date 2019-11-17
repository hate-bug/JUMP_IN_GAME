import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Solver {
	private final static int row=5;
	private final static int column=5;
	private final static String hole = "O";
	private Tree solutionTree; 
	private Node rootNode;
	private Node endNode;
	private HashMap<Piece.pieceName, Tuple> rootBoard;
	public Solver (HashMap<Piece.pieceName, Tuple> rootBoard) {
		this.rootBoard = rootBoard;
		this.rootNode = new Node (rootBoard);
		this.solutionTree = new Tree (rootNode);
		this.endNode = appendTree();
	}
	
	/**
	 * Get the next move string from the current board status
	 * @return
	 */
	public String getNextStep () {
		if (endNode == null) {
			return "Please Undo. There is no solution";
		}
		String hint = "";
		//Node solutionNode = this.solutionTree.getNextMove(endNode);
		ArrayList<Node> path = this.solutionTree.getPath(endNode);
		HashMap<Piece.pieceName, Tuple> lastBoard = this.rootBoard;
		for (Node step: path) {
			HashMap<Piece.pieceName, Tuple> nextStep = step.getData();
			for (Map.Entry<Piece.pieceName, Tuple> element: nextStep.entrySet()) {
				if (element.getKey().toString().startsWith("F") || element.getKey().toString().startsWith("R")) {
					if (!element.getValue().equals(lastBoard.get(element.getKey()))) {
						hint= hint + getMoveString (lastBoard.get(element.getKey()), element.getValue(), element.getKey().toString());
					}
				}
			}
			lastBoard.putAll(step.getData());
		}
		
		return hint;
	}
	
	private String getMoveString (Tuple source, Tuple destination, String name) {
		if (source.isValidfor2() && destination.isValidfor2()) {
			if (source.getColNum() == destination.getColNum() && destination.getCol1Num() == source.getCol1Num()) {
				String index = String.valueOf(Math.abs(Math.max(destination.getRowNum(), destination.getRow1Num())- Math.max(source.getRowNum(), source.getRow1Num())));
				if (source.getRowNum() + source.getRow1Num() < destination.getRowNum() + destination.getRow1Num()) {
					
					return "Move " + name + " down " + index + "position" +".";
				} else {
					return "Move " + name + " up " + index + "positions" +".";
				}
			} else if (source.getRowNum() == destination.getRowNum() && destination.getRow1Num() == source.getRow1Num()) {
				String index = String.valueOf(Math.abs(Math.max(destination.getColNum(), destination.getCol1Num())- Math.max(source.getCol1Num(), source.getColNum())));
				if (source.getColNum() + source.getCol1Num() < destination.getColNum() + destination.getCol1Num()) {
					return "Move " + name + " right " + index + "positions" +".";
				} else {
					return "Move " + name + " left " + index + "Positions" +".";
				}
			}
		} else {
			if (source.getColNum() == destination.getColNum()) {
				if (source.getRowNum() < destination.getRowNum()) {
					return "Move " + name + " down.";
				} else {
					return "Move " + name + " up.";
				}
			} else if (source.getRowNum() == destination.getRowNum()) {
				if (source.getColNum() < destination.getColNum()) {
					return "Move " + name + " right.";
				} else {
					return "Move " + name + " left. ";
				}
			}

		}
		return "Don't know. Darn...";
	}
	
	/**
	 * Append the tree based on current board status
	 * Using breath-first search 
	 * @return the win node
	 */
	private Node appendTree () {
		
		int level = 0;
		ArrayList<Node> levelNodeList;
		ArrayList<Node> possibleMoves;
		boolean ifGoing = true;
		while (ifGoing) {
			levelNodeList = new ArrayList<Node>();
			possibleMoves = new ArrayList<Node>();
			levelNodeList = this.solutionTree.getLevelNodes(level);
			for (Node node : levelNodeList) {
				ifGoing = false;
				if (node.isFinished()) {
					return node;
				} else {
					possibleMoves = getChildNodes(node.getData());
					for (Node move: possibleMoves) {
						if (!solutionTree.ifExist(move)) {
							node.addChild(move);
							ifGoing = true;
							if (move.isFinished()) {
								return move;
							}
						}
					}
				}
			}
			level++;
		}
		return null;
	}
		
	/**
	 * Based on current boardstate, given all possible moves 
	 * @param boardState
	 * @return list of possible moves
	 */
	private ArrayList<Node> getChildNodes (HashMap<Piece.pieceName, Tuple> boardState){
		ArrayList<Node> possibleMoves = new ArrayList<Node>();
		HashMap<Piece.pieceName, Tuple> temp;
		String [][]grid = setupBoard(boardState);
		for (Map.Entry<Piece.pieceName, Tuple> element: boardState.entrySet()) {
			if (element.getKey().toString().startsWith("R")|| element.getKey().toString().startsWith("F")) { // for R1 location
				String name = element.getKey().toString();
				Tuple R1location = element.getValue();
				for (int x=0; x<5; x++) {
					for (int y=0; y<5; y++) {
						Tuple destination = new Tuple (x, y);
						if (movePiece(name, R1location, destination, grid)) {
							temp = new HashMap<Piece.pieceName, Tuple>();
							temp.putAll(boardState);
							temp.put(element.getKey(), destination);
							possibleMoves.add(new Node(temp));
						}
					}
				}
			}
			
		}
		return possibleMoves;
	}
	
	/**
	 * Determine if a valid move
	 * @param pieceName
	 * @param currentLocation
	 * @param destination
	 * @param grid
	 * @return
	 */
	private static boolean movePiece(String pieceName,Tuple currentLocation , Tuple destination, String[][] grid) {
		
		if (currentLocation.equals(destination)) {
			return false;
		}
		//destination = new Tuple (destination.getRowNum(), destination.getColNum());
		if (!isOccupied(pieceName, destination, grid)) {//destination is not occupied
			if (pieceName.startsWith("F")) {
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
						if (isOccupied(pieceName, new Tuple(x, fromy1), grid)) {
							return false;
						}
					}
					if (tox1 > fromx1) {//move down
						int tox2 = tox1-1; 
						int toy2 = toy1;
						destination.setRow1Num(tox2);
						destination.setCol1Num(toy2);
						return true;
					}
					if (tox1 < fromx1) { // move up
						int tox2 = tox1+1; 
						int toy2 = toy1;
						destination.setRow1Num(tox2);
						destination.setCol1Num(toy2);
						return true;
					}
		
					
				} else if ((fromx1 == fromx2) && (tox1 == fromx1)) { // horizontal move
					for (int x = Math.min(Math.min(fromy1, fromy2), toy1); x< Math.max(Math.max(fromy1, fromy2)+1, toy1); x++) {
						if (isOccupied(pieceName, new Tuple(fromx1,x), grid)) {
							return false;
						}
					}
					if (toy1 > fromy1) { // move right
						int toy2 = toy1 -1; 
						int tox2 = tox1; 
						destination.setRow1Num(tox2);
						destination.setCol1Num(toy2);
						return true;
					}
					
					if (toy1 < fromy1) { //move left
						int toy2 = toy1 + 1; 
						int tox2 = tox1; 
						destination.setRow1Num(tox2);
						destination.setCol1Num(toy2);
						return true;
					}
				}
				
				
				
			} else if (pieceName.startsWith("R")) { //move a rabbit
	
				int fromx = currentLocation.getRowNum();
				int fromy = currentLocation.getColNum();
				int tox = destination.getRowNum();
				int toy = destination.getColNum();
				if (fromx==tox && fromy==toy) {
					return true;
				}
				if (fromy == toy && (Math.abs(fromx-tox)>1)) { // vertical jump
					for (int x=Math.min(fromx, tox)+1; x<Math.max(fromx, tox); x++) {
						if (!isOccupied(pieceName, new Tuple (x, fromy), grid)){ // if no piece in between, invalid move
							return false;
						}
					}
					return true;
				} 
				if (fromx == tox && (Math.abs(fromy-toy)>1)) { // horizontal jump
					for (int y=Math.min(fromy, toy)+1; y<Math.max(fromy, toy); y++) {
						if (!isOccupied(pieceName, new Tuple (fromx, y), grid)){ // if no piece in between, invalid move
							return false;
						}
					}
					return true;
				}
			}
		}
		
		return false;
		
	}
	
	public String[][] setupBoard (HashMap<Piece.pieceName, Tuple> boardState) {
		
		String[][] grid = new String[row][column]; 
		//adding all holes
		grid[0][0] = hole;
		grid[0][4] = hole;
		grid[2][2] = hole;
		grid[4][0] = hole;
		grid[4][4] = hole;
		
		for (Map.Entry<Piece.pieceName, Tuple> element: boardState.entrySet()) {
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
					grid[x][y] = "\u2022";
				}
			}
		}
		
		return grid;
	}
	
	/**
	 * Determine if the grid is occupied
	 * @param name
	 * @param location
	 * @param grid
	 * @return
	 */
	private static boolean isOccupied (String name, Tuple location, String[][] grid) {
		if (location.has4Value()) {
			boolean aOccupied = true;
			boolean bOccupied = true;
			int x1 = location.getRowNum();
			int y1 = location.getColNum();
			int x2 = location.getRow1Num();
			int y2 = location.getCol1Num();
			String dot1 = grid[x1][y1]; 
			String dot2 = grid[x2][y2];
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
			String dot1 = grid[x1][y1];
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
