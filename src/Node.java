/**
 * Author: Zhe Ji, Jiawei Ma
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
	
	
	/**
	 * The default constructor for Node class
	 */
	public Node () {
		
	}
	
	/**
	 * The constructor for Node class.Put the constructed node into the HashMap. And determine whether the game is Finished.
	 * @param data, the data in the HashMap to store the nodes
	 */
	public Node (HashMap<Piece.pieceName, Tuple> data) {
		this.children = new ArrayList<Node>();
		this.data = data;
		isFinished = determineFinished();
	}
	
	/**
	 * Set parent nodes for the node.
	 * @param parent, the parent node to be set.
	 */
	public void setParent (Node parent) {
		this.hasParent = true;
		this.parent = parent;
	}
	
	/**
	 * Add the child node for the node.
	 * @param children, the child node to be added.
	 */
	public void addChild (Node children) {
		this.children.add(children);
		children.setParent(this);
	}
	
	/**
	 * Add the list of child node as the children nodes of the node.
	 * @param children, the list of child nodes to be added.
	 */
	public void addChildren (ArrayList<Node> children) {
		this.children.addAll(children);
		for (Node node: children) {
			node.setParent(this);
		}
	}
	
	/**
	 * Determine whether the node is a leaf(no child node)
	 * @return true if the node is a leaf.
	 */
	public boolean isLeaf () {
		return this.children.size() == 0;
	}
	/**
	 * Get all children nodes of the node
	 * @return the children node.
	 */
	public ArrayList<Node> getChildren () {
		return this.children;
	}
	
	/**
	 * Determine whether the size of the children node of the node is greater than zero
	 * @return true if the node has a child node
	 */
	public boolean hasChild () {

 		return this.children.size()>0;
 	
	}
	
	/**
	 * Get the data of the HashMap
	 * @return the data of the HashMap
	 */
	public HashMap<Piece.pieceName, Tuple> getData(){
		return this.data;
	}
	
	/**
	 * Determine whether the data in the HashMap in the node is finished.
	 * @return true if the data of the node is finished.
	 */
	public boolean isFinished () {
		return this.isFinished;
	}
	
	/**
	 * determine whether the game is finished.
	 * @return true if the game is finished.
	 */
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
	
	/**
	 * Get the parent node of the node
	 * @return its parent node
	 */
	public Node getParent () {
		return this.parent;
	}
	
	/**
	 * determine whether the node has parent node.
	 * @return true if it has the parent node.
	 */
	public boolean hasParent () {
		return this.hasParent;
	}
	
}
