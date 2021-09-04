/**
 * 
 * @author Matvey Skripchenko
 * 
 *         This class represents the nodes of the doubly linked list used to
 *         implement the priority queue.
 */
public class PriorityNode<T> {

	/* Attribute declarations */
	private T dataItem; // reference to the data item stored in this node
	private PriorityNode<T> next; // reference to next node in the linked list
	private PriorityNode<T> previous; // reference to the previous node in the linked list
	private double priority; // priority of the data item stored in this node

	/**
	 * Method creating a node storing the given data and priority
	 * 
	 * @param data
	 *            given data
	 * @param prio
	 *            given priority
	 */
	public PriorityNode(T data, double prio) {
		dataItem = data;
		priority = prio;
	}

	/**
	 * Method creating an empty node, with null data and priority zero
	 */
	public PriorityNode() {
		dataItem = null;
		priority = 0.0; // setting priority to zero

	}

	/**
	 * The method getting the priority
	 * 
	 * @return priority
	 */
	public double getPriority() {
		return priority;
	}

	/**
	 * Method getting the data item
	 * 
	 * @return the data item
	 */
	public T getDataItem() {
		return dataItem;

	}

	/**
	 * Method getting the next node
	 * 
	 * @return the next node
	 */
	public PriorityNode<T> getNext() {
		return next;
	}

	/**
	 * Method getting the previous node
	 * 
	 * @return the previous node
	 */
	public PriorityNode<T> getPrevious() {
		return previous;
	}

	/**
	 * Method setting the dataItem stored in this one
	 * 
	 * @param data
	 *            the data item
	 */
	public void setDataItem(T data) {
		dataItem = data;
	}

	/**
	 * Method setting the next node
	 * 
	 * @param node
	 *            the next node to be set in the doubly linked list
	 */
	public void setNext(PriorityNode<T> node) {
		next = node;

	}

	/**
	 * Method setting the priority
	 * 
	 * @param prio
	 *            priority
	 */
	public void setPriority(double prio) {
		priority = prio;
	}

	/**
	 * Method setting the previous node
	 * 
	 * @param node
	 *            the previous node to be set in the doubly linked list
	 */
	public void setPrevious(PriorityNode<T> node) {
		previous = node;
	}
}
