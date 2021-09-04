/**
 * 
 * @author Matvey Skripchenko
 *
 *         This class represents a record in the ordered dictionary
 */

public class Record {

	// Attribute Declaration
	private Pair key;
	private String data; // should it hold an empty string??

	/**
	 * Constructor for the class
	 * 
	 * @param key
	 *            key
	 * @param data
	 *            data
	 */
	public Record(Pair key, String data) {
		this.key = key;
		this.data = data;
	}

	/**
	 * Getter method for the record getting its key
	 * 
	 * @return key
	 */
	public Pair getKey() {
		return key;
	}

	/**
	 * Getter method for the record getting its data
	 * 
	 * @return data
	 */
	public String getData() {
		return data;
	}

}
