/**
 * This class implements a node of the graph
 * 
 * @author Matvey Skripchenko
 *
 */

public class GraphNode {

	// Attribute Declaration
	private int nodeName;
	private boolean nodeMark;

	/**
	 * This is the constructor for the class and it creates an unmarked node with
	 * the given name. The name of a node is an integer value between 0 and n−1,
	 * where n is the number of nodes in the graph
	 * 
	 * @param name
	 *            node name (position)
	 */
	public GraphNode(int name) {

		this.nodeName = name;
	}

	/**
	 * Marks the node with the specified value
	 * 
	 * @param Mark
	 *            true or false
	 */
	public void setMark(boolean Mark) {

		this.nodeMark = Mark;

	}

	/**
	 * Returns the value with which the node has been marked
	 * 
	 * @return true or false
	 */
	public boolean getMark() {

		return this.nodeMark;
	}

	/**
	 * Returns the name of the node
	 * 
	 * @return name of the node
	 */
	public int getName() {

		return this.nodeName;
	}
}
