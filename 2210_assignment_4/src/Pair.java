/**
 * 
 * @author Matvey Skripchenko
 *
 *         This class represents the key attribute of a record in the ordered
 *         dictionary
 */

public class Pair {

	// Attribute declaration
	private String word;
	private String type;

	/**
	 * A constructor which initializes a new Pair object with the specified word and
	 * type
	 * 
	 * @param word
	 *            word
	 * @param type
	 *            type
	 */
	public Pair(String word, String type) {
		this.word = word;
		this.type = type;
	}

	/**
	 * Getter method for Pair
	 * 
	 * @return word
	 */
	public String getWord() {
		return word;
	}

	/**
	 * Getter method for Pair
	 * 
	 * @return type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Compare method for Pair
	 * 
	 * @param k
	 *            pair
	 * 
	 * @return 0 if the key stored in this Pair object is equal to k, returns -1 if
	 *         the key stored in this Pair object is smaller than k, and it returns
	 *         1 otherwise
	 */
	public int compareTo(Pair k) {

		int word_result;
		int type_result;
		word_result = word.toLowerCase().compareTo(k.getWord().toLowerCase());
		type_result = type.compareTo(k.getType());

		if ((word_result < 0) || (word.toLowerCase().equals(k.getWord().toLowerCase()) && type_result < 0)) {
			return -1;

		} else if ((word_result > 0) || (word.toLowerCase().equals(k.getWord().toLowerCase()) && type_result > 0)) {
			return 1;

		} else {
			return 0;
		}
	}

}
