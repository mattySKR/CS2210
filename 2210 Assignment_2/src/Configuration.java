/**
 * 
 * @author Matvey Skripchenko
 *
 *         This class stores the data that each entry of HashDictionary will
 *         contain. An object of this class stores a board configuration and its
 *         integer score.
 */

public class Configuration {

	/* Attribute declaration */
	private String config; // String that is used as a key attribute for every Configuration object
	private int score; // Integer storing the score for the entry

	/**
	 * Constructor for the class returning a new Configuration object with the
	 * specified configuration string and score.
	 */
	public Configuration(String config, int score) {
		this.config = config;
		this.score = score;

	}

	/**
	 * This method returns configuration string stored in a Configuration object
	 * 
	 * @return configuration string
	 */
	public String getStringConfiguration() {
		return config;

	}

	/**
	 * This method returns the score stored in a Configuration object
	 * 
	 * @return score
	 */
	public int getScore() {
		return score;

	}

}
