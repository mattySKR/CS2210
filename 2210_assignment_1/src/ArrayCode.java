/**
 * This class stores a list of characters and their compression codes
 * 
 * @author Matvey Skripchenko
 */
public class ArrayCode {

	/* Attribute declarations */
	public int numObjects; // number of code pairs in the array
	public int arraySize; // size of an array
	public CodePair[] codeList; // array of code pairs (list of codes)

	/**
	 * Constructor creates CodePair array of specified size
	 * 
	 * @param arraySize
	 *            size of an array
	 */
	public ArrayCode(int arraySize) {
		codeList = new CodePair[arraySize];
		numObjects = 0;
	}

	/**
	 * Add method adds the given pair to the array
	 * 
	 * @param pair
	 *            code pair
	 */
	public void add(CodePair pair) {
		if (numObjects == codeList.length && codeList.length <= 100) {
			CodePair[] largerList = new CodePair[codeList.length * 2]; // increasing the size of an array by a factor
																		// of 2
			for (int i = 0; i < codeList.length; i++) {
				largerList[i] = codeList[i];
			}
			codeList = largerList;

		} else if (numObjects == codeList.length && codeList.length > 100) {
			CodePair[] largerList = new CodePair[codeList.length + 20]; // increasing the size of an array by 20
			for (int i = 0; i < codeList.length; i++) {
				largerList[i] = codeList[i];
			}
			codeList = largerList;
		}
		codeList[numObjects] = pair;
		numObjects++;

	}

	/**
	 * Remove method that removes a pair from the array
	 * 
	 * @param pairToRemove
	 *            pair to remove
	 */
	public void remove(CodePair pairToRemove) {
		final int NOT_FOUND = -1;
		int search = NOT_FOUND;
		CodePair target = pairToRemove;
		for (int i = 0; i < numObjects && search == NOT_FOUND; i++)
			if (codeList[i].equals(target))
				search = i;

		codeList[search] = codeList[numObjects - 1];
		codeList[numObjects - 1] = null;
		numObjects--;

		if (numObjects < codeList.length / 4) {
			CodePair[] smallerList = new CodePair[codeList.length / 2]; // shrinking the size of an array by a factor of 2
			for (int i = 0; i < numObjects; i++) {
				smallerList[i] = codeList[i];
			}
			codeList = smallerList;
		}
	}

	/**
	 * This method looks for the given code in the array
	 * 
	 * @param code
	 *            code to be found
	 * 
	 * @return the position of this object in the array or value -1 if not found
	 */
	public int findCode(String code) {
		final int NOT_FOUND = -1;
		int search = NOT_FOUND;
		String target = code;
		for (int i = 0; i < numObjects && search == NOT_FOUND; i++)
			if (codeList[i].getCode().equals(target)) {
				search = i;
			}
		return search;

	}

	/**
	 * This method looks for the given character in the array
	 * 
	 * @param c
	 *            character to be found
	 * 
	 * @return the position of this object in the array or value -1 if not found
	 */
	public int findCharacter(char c) {
		final int NOT_FOUND = -1;
		int search = NOT_FOUND;
		char target = c;
		for (int i = 0; i < numObjects && search == NOT_FOUND; i++)
			if (codeList[i].getCharacter() == (target)) {
				search = i;
			}
		return search;
	}

	/**
	 * Getter method to get compression code in the CodePair object
	 * 
	 * @param i
	 *            position of the CodePair object
	 * 
	 * @ return compression code in the CodePair object or null if not found
	 */
	public String getCode(int i) {
		if (i < 0 || i > codeList.length) {
			return null;
		} else {
			return codeList[i].getCode();
		}
	}

	/**
	 * Getter method to get the character in the CodePair object
	 * 
	 * @param i
	 *            position of the CodePair object
	 * 
	 * @return character in the CodePair object or value -1 if not found
	 */
	public char getCharacter(int i) {
		if (i < 0 || i > codeList.length) {
			return 0;
		} else {
			return codeList[i].getCharacter();
		}
		
	}

	/**
	 * Getter method to get the size or length of an array
	 * 
	 * @return size or length of an array
	 */
	public int getSize() {
		return codeList.length;
	}

	/**
	 * Getter method to get the number of CodePair objects in the array
	 * 
	 * @return number of CodePair object in the array
	 */
	public int getNumPairs() {
		return numObjects;
	}

}
