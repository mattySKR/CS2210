/**
 * 
 * @author Matvey Skripchenko
 * 
 *         This class implements the node storing different information as well
 *         as accessor methods for the node
 *
 */

public class Node {

	// Attribute Declaration
	private Record record;
	private Node rightChild;
	private Node leftChild;
	private Node parent;

	/**
	 * Constructor for the class
	 * 
	 * @param record
	 *            record
	 * @param parent
	 *            parent
	 * @param rightChild
	 *            right child
	 * @param leftChild
	 *            left child
	 */
	public Node(Record record, Node parent, Node rightChild, Node leftChild) {

		this.record = record;
		this.parent = parent;
		this.rightChild = rightChild;
		this.leftChild = leftChild;

	}

	/**
	 * Constructor creating a node storing a record
	 * 
	 * @param record
	 *            record
	 */
	public Node(Record record) {

		this.record = record;
		this.rightChild = null;
		this.leftChild = null;
		this.parent = null;
	}

	/**
	 * Constructor creating an empty node
	 */
	public Node() {

		this.record = null;
		this.rightChild = null;
		this.leftChild = null;
		this.parent = null;
	}

	/**
	 * Getter method for the node getting the record
	 * 
	 * @return record
	 */
	public Record getRecord() {
		return record;
	}

	/**
	 * Getter method for the node returning its right child
	 * 
	 * @return rightChild
	 */
	public Node getRightChild() {
		return rightChild;
	}

	/**
	 * Getter method for the node returning its left child
	 * 
	 * @return leftChild
	 */
	public Node getLeftChild() {
		return leftChild;
	}

	/**
	 * Getter method for the node returning its parent
	 * 
	 * @return parent
	 */
	public Node getParent() {
		return parent;
	}

	/**
	 * Setter method for the node setting its record
	 * 
	 * @param record
	 *            record
	 */
	public void setRecord(Record record) {
		this.record = record;
	}

	/**
	 * Setter method for the node setting its right child
	 * 
	 * @param rightChild
	 *            right child
	 */
	public void setRightChild(Node rightChild) {
		this.rightChild = rightChild;
	}

	/**
	 * Setter method for the node setting its left child
	 * 
	 * @param leftChild
	 *            left child
	 */
	public void setLeftChild(Node leftChild) {
		this.leftChild = leftChild;
	}

	/**
	 * Setter method for the node setting its parent
	 * 
	 * @param parent
	 *            parent
	 */
	public void setParent(Node parent) {
		this.parent = parent;
	}

	/*
	 * Boolean method returning true if the node is the leaf or false otherwise
	 */
	public boolean isLeaf() {

		if (this.getRecord() == null) {
			return true;
		} else {
			return false;
		}
	}
}
