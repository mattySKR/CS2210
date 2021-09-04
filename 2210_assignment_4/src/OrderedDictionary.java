/**
 * 
 * @author Matvey Skripchenko
 * 
 *         This class implements an ordered dictionary using a binary search
 *         tree.
 *
 */

public class OrderedDictionary implements OrderedDictionaryADT {

	// Attribute Declaration
	private Node root_node;

	/**
	 * Constructor for the class initializing the root node
	 */
	public OrderedDictionary() {

		this.root_node = new Node();
	}

	/**
	 * Returns the Record object with key k, or it returns null if such a record is
	 * not in the dictionary
	 * 
	 * @param k
	 *            pair
	 * 
	 * @return Record object with key k, or it returns null if such a record is not
	 *         in the dictionary
	 */
	public Record get(Pair k) {

		Node target = root_node;

		if (target.isLeaf()) { // if target is a leaf node

			return null;

		} else {

			while (!(target.isLeaf()) && (target.getRecord().getKey().compareTo(k) != 0)) { // while not leaf and
																							// records
																							// are not equal

				if (target.getRecord().getKey().compareTo(k) < 0) {

					target = target.getRightChild();

				} else {

					target = target.getLeftChild();

				}
			}

			if (target.isLeaf()) {

				return null;

			} else if (target.getRightChild().isLeaf() && target.getLeftChild().isLeaf()) { // if target node's children
																							// are leaves

				return target.getRecord();

			} else if (target.getLeftChild().isLeaf()) { // case where target node's left child is a leaf, but right
															// child is not

				if (target.getRecord().getKey().compareTo(k) == 0) {

					return target.getRecord();

				} else {

					if (target.getRecord().getKey().compareTo(k) > 0) {

						return null;

					} else {

						target = target.getRightChild();

						while (!(target.isLeaf()) && (target.getRecord().getKey().compareTo(k) != 0)) {

							if (target.getRecord().getKey().compareTo(k) < 0) {

								target = target.getRightChild();

							} else {

								target = target.getLeftChild();

							}
						}
						if (target.getRecord().getKey().compareTo(k) == 0) {

							return target.getRecord();

						} else {

							return null;
						}
					}
				}

			} else { // case where target node's right child is a leaf, but left child is not

				if (target.getRecord().getKey().compareTo(k) == 0) {

					return target.getRecord();

				} else {

					if (target.getRecord().getKey().compareTo(k) < 0) {

						return null;

					} else {

						target = target.getLeftChild();

						while (!(target.isLeaf()) && (target.getRecord().getKey().compareTo(k) != 0)) {

							if (target.getRecord().getKey().compareTo(k) < 0) {

								target = target.getRightChild();

							} else {

								target = target.getLeftChild();

							}
						}
						if (target.getRecord().getKey().compareTo(k) == 0) {

							return target.getRecord();

						} else {

							return null;
						}
					}
				}
			}
		}
	}

	/**
	 * Method inserting r into the ordered dictionary.
	 * 
	 * @param r
	 *            record
	 * 
	 */
	public void put(Record r) throws DictionaryException {

		Node target = root_node;
		Node newLeftChild = new Node(null);
		Node newRightChild = new Node(null);

		if (target.isLeaf()) { // case where root is a leaf node

			target.setRecord(r);
			target.setLeftChild(newLeftChild);
			newLeftChild.setParent(target);
			target.setRightChild(newRightChild);
			newRightChild.setParent(target);

		} else if (get(r.getKey()) != null) { // case where node with same key already exist in tree

			throw new DictionaryException("Error: the key in the record you are trying to insert already exists!");

		} else {

			while (!(target.isLeaf())) {

				if (target.getRecord().getKey().compareTo(r.getKey()) < 0) {

					target = target.getRightChild();

				} else {

					target = target.getLeftChild();
				}
			}

			target.setRecord(r);
			target.setLeftChild(newLeftChild);
			newLeftChild.setParent(target);
			target.setRightChild(newRightChild);
			newRightChild.setParent(target);
		}
	}

	/**
	 * Method removing the record with key k from the dictionary
	 * 
	 * @param k
	 *            pair
	 */
	public void remove(Pair k) throws DictionaryException {

		Node target = root_node;

		if (get(k) == null) { // case where node to remove does not exist in tree

			throw new DictionaryException("Error: node with the key you are trying does not exist!");

		} else {

			while (target.getRecord().getKey().compareTo(k) != 0) {

				if (target.getRecord().getKey().compareTo(k) > 0) {

					target = target.getLeftChild();

				} else {

					target = target.getRightChild();
				}
			}
			Node current = target;

			if (current == root_node) { // case where node to remove is the root

				if (current.getRightChild().isLeaf() && current.getLeftChild().isLeaf()) {

					current.setRecord(null);
					current.setLeftChild(null);
					current.setRightChild(null);

				} else if (current.getRightChild().isLeaf()) {

					root_node = current.getLeftChild();
					root_node.setParent(null);

				} else if (current.getLeftChild().isLeaf()) {

					root_node = current.getRightChild();
					root_node.setParent(null);

				} else {

					current = root_node.getRightChild();
					root_node.setRecord(smallest(current));

					if (!(smaller(current).getRightChild().isLeaf())) {

						smaller(current).getRightChild().setParent(smaller(current).getParent());
						smaller(current).getParent().setLeftChild(smaller(current).getRightChild());
					}
				}

			} else { // case where node to remove is not the root

				if (current.getRightChild().isLeaf() && current.getLeftChild().isLeaf()) {

					current.setRecord(null);
					current.setLeftChild(null);
					current.setRightChild(null);

				} else if (current.getRightChild().isLeaf()) {

					current.getParent().setLeftChild(current.getLeftChild());
					current.getLeftChild().setParent(current.getParent());

				} else if (current.getLeftChild().isLeaf()) {

					current.getParent().setRightChild(current.getRightChild());
					current.getRightChild().setParent(current.getParent());

				} else {

					Node temp = current.getRightChild();
					current.setRecord(smallest(temp));

					if (!(smaller(temp).getRightChild().isLeaf())) {

						smaller(temp).getRightChild().setParent(smaller(temp).getParent());
						smaller(temp).getParent().setLeftChild(smaller(temp).getRightChild());

					} else {

						smaller(temp).setRecord(null);
						smaller(temp).setLeftChild(null);
						smaller(temp).setRightChild(null);

					}
				}
			}
		}
	}

	/**
	 * Method returning the successor of k (the record from the ordered dictionary
	 * with smallest key larger than k); it returns null if the given key has no
	 * successor. The given key DOES NOT need to be in the dictionary
	 * 
	 * @param k
	 *            pair
	 * 
	 * @return the successor of k (the record from the ordered dictionary with
	 *         smallest key larger than k); it returns null if the given key has no
	 *         successor. The given key DOES NOT need to be in the dictionary
	 */
	public Record successor(Pair k) {

		Record successor = largest();

		if (successor == null) { // case where tree is empty

			return null;

		} else if (k.compareTo(successor.getKey()) == 0) { // case where pair k is the largest in the tree
															// there for has no successor in tree
			return null;
		} else {

			return successor_recursion(root_node, k, successor);
		}

	}

	/**
	 * Recursive method for the successor
	 * 
	 * @param node
	 *            node
	 * @param k
	 *            pair
	 * @param successor
	 *            record
	 * @return successor
	 */
	private Record successor_recursion(Node node, Pair k, Record successor) {

		if (node.isLeaf()) {
			return successor;
		}

		Record successor1 = successor_recursion(node.getLeftChild(), k, successor);
		Record successor2 = successor_recursion(node.getRightChild(), k, successor);

		if ((successor1.getKey().compareTo(k) > 0) && (successor1.getKey().compareTo(successor.getKey()) < 0)) {

			successor = successor1;
		}

		if ((successor2.getKey().compareTo(k) > 0) && (successor2.getKey().compareTo(successor.getKey())) < 0) {

			successor = successor2;
		}

		if ((node.getRecord().getKey().compareTo(k) > 0)
				&& (node.getRecord().getKey().compareTo(successor.getKey()) < 0)) {

			successor = node.getRecord();
		}

		return successor;
	}

	/**
	 * Method returning the predecessor of k (the record from the ordered dictionary
	 * with largest key smaller than k; it returns null if the given key has no
	 * predecessor. The given key DOES NOT need to be in the dictionary.
	 * 
	 * @param k
	 *            pair
	 * 
	 * @return the predecessor of k (the record from the ordered dictionary with
	 *         largest key smaller than k; it returns null if the given key has no
	 *         predecessor. The given key DOES NOT need to be in the dictionary.
	 */
	public Record predecessor(Pair k) {

		Record predecessor = smallest();

		if (predecessor == null) { // case where tree is empty

			return null;

		} else if (k.compareTo(predecessor.getKey()) == 0) { // case where pair k is the smallest node in the tree
			// there for has no predecessor in tree
			return null;

		} else {

			return predecessor_recursion(root_node, k, predecessor);
		}

	}

	/**
	 * Method recursively finds predecessor for predecessor method.
	 * 
	 * @param node
	 *            node
	 * @param k
	 *            pair
	 * @param predecessor
	 *            record
	 * @return predecessor
	 */
	private Record predecessor_recursion(Node node, Pair k, Record predecessor) {

		if (node.isLeaf()) {
			return predecessor;
		}

		Record predecessor1 = predecessor_recursion(node.getLeftChild(), k, predecessor);
		Record predecessor2 = predecessor_recursion(node.getRightChild(), k, predecessor);

		if ((predecessor1.getKey().compareTo(k) < 0) && (predecessor1.getKey().compareTo(predecessor.getKey()) > 0)) {

			predecessor = predecessor1;
		}

		if ((predecessor2.getKey().compareTo(k) < 0) && (predecessor2.getKey().compareTo(predecessor.getKey()) > 0)) {

			predecessor = predecessor2;
		}

		if ((node.getRecord().getKey().compareTo(k) < 0)
				&& (node.getRecord().getKey().compareTo(predecessor.getKey()) > 0)) {

			predecessor = node.getRecord();
		}

		return predecessor;

	}

	/**
	 * Method returns the record with smallest key in the ordered dictionary.
	 * Returns null if the dictionary is empty.
	 * 
	 * @return the record with smallest key in the ordered dictionary. Returns null
	 *         if the dictionary is empty.
	 */
	public Record smallest() {

		return smallest(root_node);
	}

	/**
	 * Finds smallest node record for smallest method.
	 * 
	 * @param node
	 *            node
	 * @return smallest record
	 */
	private Record smallest(Node node) {

		Node target = node;
		Node prev = target;

		if (target.isLeaf()) { // case where tree is empty
			return null;

		} else {

			while (!(target.isLeaf())) {

				prev = target;
				target = target.getLeftChild();

			}
			return prev.getRecord();
		}
	}

	/**
	 * Just like the smallest function but returns the smallest node, rather than
	 * its record.
	 * 
	 * @param node
	 *            node
	 * @return smallest node
	 */
	private Node smaller(Node node) {

		Node target = node;
		Node prev = target;

		while (!(target.isLeaf())) {

			prev = target;
			target = target.getLeftChild();
		}
		return prev;
	}

	/**
	 * Method returns the record with largest key in the ordered dictionary. Returns
	 * null if the dictionary is empty.
	 * 
	 * @return the record with largest key in the ordered dictionary. Returns null
	 *         if the dictionary is empty.
	 */
	public Record largest() {

		return largest(root_node);
	}

	/**
	 * Finds the largest nodes record for the largest method.
	 * 
	 * @param node
	 *            node
	 * @return smallest record
	 */
	private Record largest(Node node) {

		Node target = node;
		Node prev = target;

		if (target.isLeaf()) { // case where tree is empty
			return null;

		} else {

			while (!(target.isLeaf())) {

				prev = target;
				target = target.getRightChild();
			}
			return prev.getRecord();
		}
	}
}
