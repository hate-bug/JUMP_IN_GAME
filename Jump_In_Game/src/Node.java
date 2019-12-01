/**
 * Author: Zhe Ji 
 * A node that only takes hashmap 
 * Node can have multiple children and a single parent 
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Node {
	
	private HashMap<Piece.pieceName, Tuple> data; 
	private Node parent; 
	private boolean hasParent = false;
	private ArrayList<Node> children;
	private boolean isFinished;
	
	public Node () {
		
	}
	public Node (HashMap<Piece.pieceName, Tuple> data) {
		this.children = new ArrayList<Node>();
		this.data = data;
		isFinished = determineFinished();
	}
	
	public void setParent (Node parent) {
		this.hasParent = true;
		this.parent = parent;
	}
	
	public void addChild (Node children) {
		this.children.add(children);
		children.setParent(this);
	}
	
	public void addChildren (ArrayList<Node> children) {
		this.children.addAll(children);
		for (Node node: children) {
			node.setParent(this);
		}
	}
	
	public boolean isLeaf () {
		return this.children.size() == 0;
	}
	
	public ArrayList<Node> getChildren () {
		return this.children;
	}
	
	public boolean hasChild () {

 		return this.children.size()>0;
 	
	}
	
	public HashMap<Piece.pieceName, Tuple> getData(){
		return this.data;
	}
	
	public boolean isFinished () {
		return this.isFinished;
	}
	
	private boolean determineFinished () {
		ArrayList<Tuple> holes = new ArrayList<Tuple>();
		holes.add(new Tuple (0, 0));
		holes.add(new Tuple (0, 4));
		holes.add(new Tuple (2, 2));
		holes.add(new Tuple (4, 0));
		holes.add(new Tuple (4, 4));
		for (Map.Entry<Piece.pieceName, Tuple> element: this.data.entrySet()) {
			if (element.getKey().toString().startsWith("R") && !holes.contains(element.getValue())) {
				return false;
			}
		}
		return true;
	}
	
	public Node getParent () {
		return this.parent;
	}
	
	public boolean hasParent () {
		return this.hasParent;
	}
	
}