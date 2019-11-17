import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Tree_Test {

	private HashMap<Piece.pieceName, Tuple> data;  
	private Tree testTree;
	private Node root;
	
	@BeforeEach
	void setUp() throws Exception {
		this.data = new HashMap<Piece.pieceName, Tuple>();
		data.put(Piece.pieceName.F1, new Tuple (1, 1, 2, 1));
		data.put(Piece.pieceName.F2, new Tuple (3, 2, 4, 2));
		data.put(Piece.pieceName.R1, new Tuple (1, 0));
		data.put(Piece.pieceName.R2, new Tuple (3, 3));
		data.put(Piece.pieceName.R3, new Tuple (2, 4));
		data.put(Piece.pieceName.M1, new Tuple (4, 3));
		data.put(Piece.pieceName.M2, new Tuple (1, 2));
		this.testTree = new Tree(new Node(data));
		this.root = new Node (data);
	}

	@Test 
	public void addRedundentData () {
		assertEquals(false, this.testTree.addChild(root, new Node(data)));
	}
	
	@Test
	public void addChildren () {
		HashMap<Piece.pieceName, Tuple> tempData = new HashMap<Piece.pieceName, Tuple>();
		tempData.putAll(this.data);
		tempData.put(Piece.pieceName.R1, new Tuple (4, 4));
		Node tempNode = new Node (tempData);
		assertEquals(true, this.testTree.addChild(root, tempNode));
	}
	
	@Test 
	public void checkExist () {
		assertEquals(true, this.testTree.ifExist(this.root));
		HashMap<Piece.pieceName, Tuple> tempData = new HashMap<Piece.pieceName, Tuple>();
		tempData.putAll(this.data);
		tempData.put(Piece.pieceName.R1, new Tuple (4, 4));
		Node tempNode = new Node (tempData);
		assertEquals(false, this.testTree.ifExist(tempNode));
		this.testTree.addChild(root, tempNode);
		assertEquals(true, this.testTree.ifExist(tempNode));
	}
		
}
