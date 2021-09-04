/**
 * 
 * @author matveyskripchenko
 *
 */
public class LinkedList {

	/* Attribute Declaration */
	private Node first;
	private int listSize;

	/**
	 * 
	 */
	public LinkedList() { // Empty linked list
		this.first = null;
		this.listSize = 0;
	}

	/**
	 * 
	 * @param first
	 */
	public LinkedList(Node first) {
		this.first = first;
		this.listSize = 1;
	}

	/**
	 * 
	 * @param config
	 * @return
	 */
	public Configuration sortNodes(String config) {

		if (listSize == 0) {
			return null;
		}

		Node sortNode = first;

		while (sortNode != null) {

			if (sortNode.getEntry().getStringConfiguration().equals(config)) {
				Configuration found = sortNode.getEntry();
				return found;

			} else {
				sortNode = sortNode.getNext();
			}
		}
		return null; // Null if the node with the specified string configuration was not found in the
						// list

	}

	/**
	 * 
	 * @param newData
	 * @return
	 */
	public int insertNode(Configuration newData) {

		int collision = 1;
		int emptyEntry = 0;

		if (listSize != 0) {
			return collision; // Collision occurred
		}

		Node current = new Node(newData);
		current.setNext(first);
		first = current;
		return emptyEntry; // No collisions occurred

	}

	/**
	 * 
	 * @param config
	 */
	public void delete(String config) {

	}
}
