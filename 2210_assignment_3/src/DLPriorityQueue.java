/**
 * 
 * @author Matvey Skripchenko
 *
 *         Represents the data items of the priority queue in a doubly linked
 *         list
 */

public class DLPriorityQueue<T> implements PriorityQueueADT<T> {

	/* Attribute declarations */
	private PriorityNode<T> front; // reference to the first node of the doubly linked list
	private PriorityNode<T> rear; // reference to the last node of the doubly linked list
	private int count; // the value of the variable is the number of data items in the priority queue

	/**
	 * Method creating an empty priority queue
	 */
	public DLPriorityQueue() {
		PriorityNode<T> priorityQueue = new PriorityNode<T>(); // empty priority queue
		count = 0;
		front = null;
		rear = null;

	}

	/**
	 * Method adding the dataItem to the priority queue
	 * 
	 * @param dataItem
	 *            data item
	 * 
	 * @param priority
	 *            priority
	 */
	public void enqueue(T dataItem, double priority) {
		PriorityNode<T> newNode = new PriorityNode<T>(dataItem, priority);
		if (isEmpty()) {
			front = newNode;
		} else {
			rear.setNext(newNode);
			newNode.setPrevious(rear);
			// adding the new dataItem to the rear of the doubly linked list
		}
		rear = newNode;
		count++; // incrementing the counter

	}

	/**
	 * Method removing the dataItem from the priority queue
	 * 
	 * @return data item at the front of the priority queue
	 * @throws EmptyPriorityQueueException
	 */
	public T dequeue() throws EmptyPriorityQueueException {
		if (isEmpty())
			throw new EmptyPriorityQueueException("The priority queue is empty, please try again");
		T removedNode = front.getDataItem();
		front = front.getNext(); // setting to the front of the priority queue
		count--; // decrementing the counter
		if (isEmpty())
			rear = null;
		return removedNode;
	}

	/**
	 * Method getting the priority from the priority queue
	 * 
	 * @param dataItem
	 *            data item
	 * @return priority of the specified data item
	 * @throws InvalidDataItemException
	 */
	public double getPriority(T dataItem) throws InvalidDataItemException {
		PriorityNode<T> current = front;
		for (int i = 0; i < count; i++) { // linear search
			if (current.getDataItem().equals(dataItem)) {
				return current.getPriority();
			}
			current = current.getNext();
		}
		throw new InvalidDataItemException("The specified data item is not in the list, please try again");

	}

	/**
	 * Method changing the priority of the specified data item
	 * 
	 * @param dataItem
	 *            data item
	 * @param newPriority
	 *            new priority
	 * @throws InvalidDataItemException
	 */
	public void changePriority(T dataItem, double newPriority) throws InvalidDataItemException {
		PriorityNode<T> current = front;
		for (int i = 0; i < count; i++) {
			if (current.getDataItem().equals(dataItem)) {
				current.setPriority(newPriority);
				break; // if the above is found, the loop breaks
			}
			current = current.getNext();
		}
		if (current == null) {
			throw new InvalidDataItemException("The specified data item is not in the list, please try again");
		}

	}

	/**
	 * Method removing and returning the data item in the priority queue with
	 * smallest priority
	 * 
	 * @return data item with the smallest priority
	 * @throws EmptyPriorityQueueException
	 */
	public T getSmallest() throws EmptyPriorityQueueException {
		if (isEmpty())
			throw new EmptyPriorityQueueException("The priority queue is empty, please try again");
		PriorityNode<T> currentDataItem = front;
		PriorityNode<T> smallestDataItem = front;
		T smallestElement;
		for (int i = 0; i < count; i++) {
			if (smallestDataItem.getPriority() > currentDataItem.getPriority()) // if the smallest priority is greater
																				// than the current
				// priority of the specified elements
				smallestDataItem = currentDataItem;
			currentDataItem = currentDataItem.getNext();

		}
		smallestElement = smallestDataItem.getDataItem();
		if (smallestDataItem.getNext() != null) {
			smallestDataItem.getNext().setPrevious(smallestDataItem.getPrevious());
		} else {
			rear = smallestDataItem.getPrevious();
		}
		if (smallestDataItem.getPrevious() != null) {
			smallestDataItem.getPrevious().setNext(smallestDataItem.getNext());
		} else {
			front = smallestDataItem.getNext();
		}
		count--;
		return smallestElement;
	}

	/**
	 * Method returning true if the priority queue is empty and false otherwise
	 * 
	 * @return boolean
	 */
	public boolean isEmpty() {
		if (count == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Method returning the number of data items in the priority queue
	 * 
	 * @return count
	 */
	public int numItems() {
		return count;
	}

	/**
	 * Method returning a String representation of the priority queue
	 * 
	 * @return string
	 */
	public String toString() {
		String string = ""; // empty string
		PriorityNode<T> current = front;
		for (int i = 0; i < count; i++) {
			string += current.getDataItem().toString(); // concatenating
			current = current.getNext();
		}
		return string;
	}
}
