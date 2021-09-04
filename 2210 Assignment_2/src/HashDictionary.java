/**
 * 
 * @author Matvey Skripchenko
 * 
 *         This class implements a dictionary using a hash table with separate
 *         chaining.
 */

public class HashDictionary implements DictionaryADT {

	/* Attribute Declaration */
	private LinkedList[] hashTable;
	private int size;

	// Might create the int value for the number of nodes in the dictionary in case
	// it might help in the BoardGame class

	/**
	 * 
	 * @param size
	 */
	public HashDictionary(int size) {
		this.size = size;
		hashTable = new LinkedList[size];
		LinkedList emptyList = new LinkedList();
		for (int i = 0; i < size; i++) {
			hashTable[i] = emptyList; // Each index of the hash table will obtain an empty linked list
		}
	}

	/**
	 * For now, will create an approximate hash function and will change it after
	 * seeing the amount of collisions created when tested
	 * 
	 * @param congiguration
	 * @return
	 */
	private int hashFun(String configuration) {
		int entryIndex = 0;
		int factor = 7;

		for (int i = 0; i < configuration.length(); i++) {

			if ((configuration.length() % size) == 0) {
				entryIndex = (((int) configuration.charAt(i) + factor) % size);

			} else if (((int) configuration.charAt(i) % 2) == 0) {
				entryIndex = ((configuration.length() + factor) % size);

			} else {
				entryIndex = (configuration.charAt(i) % size) + factor;

			}

		}

		if (entryIndex > size) {
			entryIndex = entryIndex - size; // if the entry index turns out to be larger than the table size, it will be
											// decreased below the table size
		}

		return entryIndex;
	}

	/**
	 * 
	 * @param -
	 * @return -
	 * @throws -
	 */
	public int put(Configuration data) throws DictionaryException {

		String configurationKey = data.getStringConfiguration();

		if (sortNodes(configurationKey) == null) { // If the list is empty or configuration string stored in
													// data is NOT in the dictionary
			int collisionResult = hashTable[hashFun(configurationKey)].insertNode(data); // Inserts the configuration
																							// object in the dictionary
																							// and returns the collision
																							// result (from insertNode
																							// method)
			return collisionResult;

		} else {
			throw new DictionaryException("Error: the object you are trying to add is already in the dictionary!");

		}

	}

	/**
	 * 
	 * @param config
	 * @return
	 */
	private Configuration sortNodes(String config) {
		LinkedList hashPosition = hashTable[hashFun(config)];
		return hashPosition.sortNodes(config);
	}

}
