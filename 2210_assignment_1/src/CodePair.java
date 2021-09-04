/**
 * This class represents an entry in the list of codes, associating a character
 * with a compression code
 * 
 * @author Matvey Skripchenko
 */
public class CodePair {

	/* Attribute declarations */
	public char specifiedCharacter; // specified character
	public String compressionCode; // compression code

	/**
	 * Constructor returns a new CodePair object storing the specified character and
	 * the compression code
	 * 
	 * @return character and compression code stored as Strings '0's and '1's
	 */
	public CodePair(char c, String code) {
		this.specifiedCharacter = c;
		this.compressionCode = code;
	}

	/**
	 * Getter method to get the compression code
	 * 
	 * @return compression code stored in CodePair
	 */
	public String getCode() {
		return this.compressionCode;
	}

	/**
	 * Getter method to get the specified character
	 * 
	 * @return specified character stored in CodePair
	 */
	public char getCharacter() {
		return this.specifiedCharacter;
	}

	/**
	 * Setter method to store the given character in CodePair
	 * 
	 * @param specified
	 *            character
	 */
	public void setCharacter(char c) {
		this.specifiedCharacter = c;
	}

	/**
	 * Setter method to store the given compression code in CodePair
	 * 
	 * @param compression
	 *            code
	 */
	public void setCode(String code) {
		this.compressionCode = code;
	}

	/**
	 * Boolean that compares the length of the characters stored in CodePair and
	 * AnotherPair
	 * 
	 * @param anotherPair
	 *            a different codePair object
	 * @return true if same characters in CodePair and AnotherPair
	 */
	public boolean equals(CodePair anotherPair) {
		if (this.specifiedCharacter == anotherPair.specifiedCharacter)
			return true;
		else
			return false;
	}

}
