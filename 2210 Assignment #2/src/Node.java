/**
 * 
 * @author Matvey Skripchenko
 * 
 *         This class stores an object of type Configuration.
 *
 */

public class Node {

	/* Attribute Declaration */
	private Node next;
	private Configuration configNode;

	/**
	 * Constructor for the class string the object of type Configuration
	 * 
	 * @param tableEntry
	 */

	public Node(Configuration configNode) {
		this.configNode = configNode;
		this.next = null;

	}

	/**
	 * Method returning the next node
	 * 
	 * @return next node
	 */
	public Node getNext() {
		return next;

	}

	/**
	 * Method setting the node stored in this one
	 * 
	 * @param nextNode
	 */
	public void setNext(Node nextNode) {
		next = nextNode;

	}

	/**
	 * Method returning the configuration object stored in the node
	 * 
	 * @return entry node
	 */
	public Configuration getEntry() {
		return configNode;

	}

}
