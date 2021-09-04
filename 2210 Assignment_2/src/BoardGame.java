import java.util.Stack;

/**
 * 
 * @author Matvey Skripchenko
 *
 *         This class implements all the support methods needed by the algorithm
 *         that plays the board game
 *
 */
public class BoardGame {

	/* Attribute Declaration */
	private char[][] gameBoard;
	private final static int HASHDICT_SIZE = 6000;

	/**
	 * Constructor for the class storing 3 parameters and creating the game board
	 * 
	 * @param board_size
	 *            size of the board
	 * @param empty_positions
	 *            empty positions
	 * @param max_levels
	 *            max tree height
	 */
	public BoardGame(int board_size, int empty_positions, int max_levels) {
		gameBoard = new char[board_size][board_size];
		for (int vert = 0; vert < board_size; vert++) {
			for (int hor = 0; hor < board_size; hor++) {
				gameBoard[vert][hor] = 'g'; // storing 'g' at every entry of the game board
			}
		}

	}

	/**
	 * Method returning an empty HashDictionary of the selected size
	 * 
	 * @return dictionary
	 */
	public HashDictionary makeDictionary() {
		HashDictionary dictionary = new HashDictionary(HASHDICT_SIZE);
		return dictionary;
	}

	/**
	 * Method representing the content of the gameBoard as a string, then checks
	 * whether the string is in the dictionary
	 * 
	 * @param dict
	 *            hash dictionary
	 * @return the score of the string or -1 otherwise
	 */
	public int isRepeatedConfig(HashDictionary dict) {
		String strConfig = "";
		for (int vert = 0; vert < gameBoard.length; vert++) {
			for (int hor = 0; hor < gameBoard.length; hor++) {
				strConfig += gameBoard[vert][hor]; // representing gameBoard as a string
			}
		}
		int score = dict.getScore(strConfig); // getting the score of the string from the dictionary
		return score;
	}

	/**
	 * Method representing the content of the gameBoard as a string, the inserting
	 * the string + score in the dictionary
	 * 
	 * @param dict
	 *            hash dictionary
	 * @param score
	 *            score of the string
	 */
	public void putConfig(HashDictionary dict, int score) {
		String strConfig = "";
		for (int vert = 0; vert < gameBoard.length; vert++) {
			for (int hor = 0; hor < gameBoard.length; hor++) {
				strConfig += gameBoard[vert][hor];
			}
		}
		Configuration data = new Configuration(strConfig, score); // creating a configuration object storing the string
																	// and score

		try {
			dict.put(data);
		} catch (DictionaryException e) { // might throw exception if data is already in the dictionary

		}
	}

	/**
	 * Method storing the symbol in the gameBoard[row][col]
	 * 
	 * @param row
	 * @param col
	 * @param symbol
	 */
	public void savePlay(int row, int col, char symbol) {
		gameBoard[row][col] = symbol;

	}

	/**
	 * Method returning true if 'g' in gameBoard[row][col]
	 * 
	 * @param row
	 * @param col
	 * @return true if 'g' in gameBoard[row][col], false otherwise
	 */
	public boolean positionIsEmpty(int row, int col) {
		boolean success = true;
		if (gameBoard[row][col] == 'g') {
			return success;
		} else {
			return (!success);
		}
	}

	/**
	 * Method returning true if 'o' in gameBoard[row][col]
	 * 
	 * @param row
	 * @param col
	 * @return true if 'o' in gameBoard[row][col], false otherwise
	 */
	public boolean tileOfComputer(int row, int col) {
		boolean success = true;
		if (gameBoard[row][col] == 'o') {
			return success;
		} else {
			return (!success);
		}
	}

	/**
	 * Method returning true if 'b' in gameBoard[row][col]
	 * 
	 * @param row
	 * @param col
	 * @return true if 'b' in gameBoard[row][col], false otherwise
	 */
	public boolean tileOfHuman(int row, int col) {
		boolean success = true;
		if (gameBoard[row][col] == 'b') {
			return success;
		} else {
			return (!success);
		}
	}

	/**
	 * Method returning true if there are n adjacent tiles of type symbol in the
	 * same row, column, or diagonal of gameBoard
	 * 
	 * @param symbol
	 * @return true if as described above, false otherwise
	 */
	public boolean wins(char symbol) {
		int boardSize = gameBoard.length;
		Stack<Character> columnCharStack = new Stack<Character>(); // creating a stack holding characters

		// checking every column of the gameBoard
		for (int vert = 0; vert < boardSize; vert++) {
			for (int hor = 0; hor < boardSize; hor++) {
				columnCharStack.push(gameBoard[vert][hor]);

			}
			boolean won = true;
			for (int temp = 0; temp < boardSize; temp++) {
				if (columnCharStack.pop() != symbol) { // emptying the stack and comparing the characters to the symbol
					won = false;
				}

			}
			if (won == true) {
				return true;
			}
		}
		// checking every row of the gameBoard
		for (int vert = 0; vert < boardSize; vert++) {
			for (int hor = 0; hor < boardSize; hor++) {
				columnCharStack.push(gameBoard[hor][vert]); // switching hor and vert values in order to check the rows

			}
			boolean won = true;
			for (int temp = 0; temp < boardSize; temp++) {
				if (columnCharStack.pop() != symbol) {
					won = false;
				}

			}
			if (won == true) {
				return true;
			}
		}
		// diagonal checking (top left to bottom right diagonal)
		for (int vert = 0; vert < boardSize; vert++) {
			for (int hor = 0; hor < boardSize; hor++) {
				if (vert == hor) {
					columnCharStack.push(gameBoard[vert][hor]);
				}
			}
			boolean won = true;
			for (int temp = 0; temp < boardSize; temp++) {
				if (columnCharStack.pop() != symbol) {
					won = false;
				}

			}
			if (won == true) {
				return true;
			}
		}
		// diagonal checking (top right to bottom left diagonal)
		for (int vert = 0; vert < boardSize; vert++) {
			for (int hor = (boardSize - 1); hor >= 0; hor--) {
				columnCharStack.push(gameBoard[vert][hor]);
			}
			boolean won = true;
			for (int temp = 0; temp < boardSize; temp++) {
				if (columnCharStack.pop() != symbol) {
					won = false;
				}
			}
			if (won == true) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Method returning true if the game configuration corresponding to the
	 * gameBoard is a draw assuming that the player that will perform the next move
	 * uses tiles of the type specified by symbol
	 * 
	 * @param symbol
	 * @param empty_positions
	 * @return true if as described above, false otherwise
	 */
	public boolean isDraw(char symbol, int empty_positions) {
		int boardSize = gameBoard.length;
		if (empty_positions == 0) {
			return true;

		}
		if (empty_positions > 0) {
			for (int vert = 0; vert < boardSize; vert++) {
				for (int hor = 0; hor < boardSize; hor++) {
					if (vert == 0 && hor == 0 && symbol == 'g') {
						if (vert == 0 && hor + 1 == 1 && symbol == 'b') {
							if (vert + 1 == 1 && hor == 0 && symbol == 'b') {
								if (vert + 1 == 1 && hor + 1 == 1 && symbol == 'b') {
									return true;
								}
							}
						}
						if (vert == 0 && hor + 1 == 1 && symbol == 'o') {
							if (vert + 1 == 1 && hor == 0 && symbol == 'o') {
								if (vert + 1 == 1 && hor + 1 == 1 && symbol == 'o') {
									return true;
								}
							}
						}

					} else if (vert == (boardSize - 1) && hor == 0 && symbol == 'g') {
						if (vert - 1 == (boardSize - 2) && hor == 0 && symbol == 'b') {
							if (vert == (boardSize - 1) && hor + 1 == 1 && symbol == 'b') {
								if (vert - 1 == (boardSize - 2) && hor + 1 == 1 && symbol == 'b') {
									return true;
								}
							}
						}
						if (vert - 1 == (boardSize - 2) && hor == 0 && symbol == 'o') {
							if (vert == (boardSize - 1) && hor + 1 == 1 && symbol == 'o') {
								if (vert - 1 == (boardSize - 2) && hor + 1 == 1 && symbol == 'o') {
									return true;
								}
							}
						}

					} else if (vert == 0 && hor == (boardSize - 1)) {
						if (vert == 0 && hor == (boardSize - 1) && symbol == 'b') {
							if (vert + 1 == 1 && hor == (boardSize - 1) && symbol == 'b') {
								if (vert + 1 == 1 && hor - 1 == (boardSize - 2) && symbol == 'b') {
									return true;
								}
							}
						}
						if (vert == 0 && hor == (boardSize - 1) && symbol == 'o') {
							if (vert + 1 == 1 && hor == (boardSize - 1) && symbol == 'o') {
								if (vert + 1 == 1 && hor - 1 == (boardSize - 2) && symbol == 'o') {

								}
							}
						}

					} else if (vert == (boardSize - 1) && hor == (boardSize - 1) && symbol == 'g') {
						if (vert - 1 == (boardSize - 2) && hor == (boardSize - 1) && symbol == 'b') {
							if (vert == (boardSize - 1) && hor - 1 == (boardSize - 2) && symbol == 'b') {
								if (vert - 1 == (boardSize - 2) && hor - 1 == (boardSize - 2) && symbol == 'b') {
									return true;
								}
							}
						}
						if (vert - 1 == (boardSize - 2) && hor == (boardSize - 1) && symbol == 'o') {
							if (vert == (boardSize - 1) && hor - 1 == (boardSize - 2) && symbol == 'o') {
								if (vert - 1 == (boardSize - 2) && hor - 1 == (boardSize - 2) && symbol == 'o') {
									return true;
								}
							}
						}

					} else {

					}
				}
			}
		}
		return false; // !!!!! I know that the isDraw method is obviously not made the right away as I
						// was struggling a little bit with the logic. Besides that, all other methods
						// should be correct !!!!!!!!
	}

	/**
	 * Method returning the values from 0 to 3 depending on the outcome (see
	 * comments)
	 * 
	 * @param symbol
	 * @param empty_positions
	 * @return value of the outcome
	 */
	public int evalBoard(char symbol, int empty_positions) {
		if (wins(symbol)) {
			if (symbol == 'b') {
				return 0; // if the player has won
			} else {
				return 3; // if the computer has won
			}

		} else if (isDraw(symbol, empty_positions)) {
			return 2; // if the game is a draw
		} else {
			return 1; // if the game is still undecided
		}
	}

}
