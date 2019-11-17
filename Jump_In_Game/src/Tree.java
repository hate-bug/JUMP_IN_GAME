import java.util.ArrayList;
import java.util.Collections;

public class Tree {

	private Node root; 
	
	public Tree (Node root) {
		this.root = root;
	}
	
	/**
	 * Add child nodes to parent node
	 * @param parent
	 * @param children
	 * @return
	 */
	public boolean addChild (Node parent, Node children) {
		//if child is existing or parent does not exist, return false
		if (this.getNode(this.root, children)!=null && this.getNode(this.root, parent)==null) {
			return false;
		} else {
			this.getNode(this.root, parent).addChild(children);
			return true;
		}
	}
	
	private Node getNode (Node parentNode, Node targetNode) {
		
		if ((parentNode.getData()).equals(targetNode.getData())) {
			return parentNode;
		} else if (parentNode.getChildren().size()>0) {
			for (Node node: parentNode.getChildren()) {
				if (getNode (node, targetNode) != null && (getNode(node, targetNode).getData()).equals(targetNode.getData())) {
					return node;
				}
			}
		}
		return null;
	}
	
	/**
	 * Check if a node exists in a tree
	 * @param node
	 * @return
	 */
	public boolean ifExist (Node node) {
		
		return getNode(this.root, node)!=null;
		
	}
	/**
	 * Retuen the next move from the path which contain the speecific node
	 * @param node
	 * @return
	 */
	public Node getNextMove (Node node) {
		Node dummyNode = node;
		while (dummyNode.getParent().hasParent()) {
			dummyNode = dummyNode.getParent();
		}
		return dummyNode;
	}
	
	/**
	 * Rretrieve a list of node which in the level
	 * @param level
	 * @return
	 */
	public ArrayList<Node> getLevelNodes (int level) {
		ArrayList<Node> nodeList = new ArrayList<Node>();
		ArrayList<Node> tempList = new ArrayList<Node>();
		nodeList.add(this.root);
		
		for (int i=0 ; i<level; i++) {
			for (Node node: nodeList) {
				tempList.addAll(node.getChildren());
			}
			nodeList.clear();
			nodeList.addAll(tempList);
			tempList.clear();
		}
		return nodeList;
	}
	
	/**
	 * Get a path that start from root and end in the final node
	 * @param finalNode
	 * @return
	 */
	public ArrayList<Node> getPath (Node finalNode) {
		ArrayList <Node> path = new ArrayList<Node>();
		while (finalNode.hasParent()) {
			path.add(finalNode);
			finalNode = finalNode.getParent();
		}
		Collections.reverse(path);
		return path;
	}
	
}