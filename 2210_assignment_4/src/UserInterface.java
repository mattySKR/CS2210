
/**
 * 
 * @author Matvey Skripchenko
 * 
 *         This class implements a user interface, which allows users to enter 
 *         commands to work with the ordered dictionary.
 *
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;
import java.util.StringTokenizer;

//User Interface class
public class UserInterface {

	public static void main(String args[]) {

		OrderedDictionary dict = new OrderedDictionary();

		if (args.length != 1) {
			System.out.println("Error: enter the filename");
			System.exit(0);
		}

		try { // fills tree with nodes, which contain data from file

			BufferedReader input = new BufferedReader(new FileReader(args[0]));
			String word = input.readLine();
			String data, type;

			while (word != null) { // while file still contains data

				data = input.readLine();
				if (data.endsWith(".jpg") || data.endsWith(".gif")) {
					type = "image";

				} else if (data.endsWith(".wav") || data.endsWith(".mid")) {
					type = "audio";

				} else {
					type = "text";
				}

				dict.put(new Record(new Pair(word.toLowerCase(), type), data));
				word = input.readLine();

			}
		} catch (IOException e) {

			e.printStackTrace();
			System.out.println("Error, please try again!");

		} catch (DictionaryException e1) {

			e1.printStackTrace();
			System.out.println("Error, the record with the same key might already be in the dictionary!");

		}

		StringReader scan = new StringReader();
		String userCommand = "";
		String word = "", type = "", dat = "", command = "";
		int size, number = 0;
		boolean empty = false;

		while (!userCommand.equals("finish")) { // while user wants to keep entering commands (while user does not enter
												// finish)

			userCommand = scan.read("Enter next command: ");
			StringTokenizer line = new StringTokenizer(userCommand);

			Stack<String> stack = new Stack<>();

			while (line.hasMoreTokens()) {
				stack.push(line.nextToken());

			}

			size = stack.size();

			if (size == 0) {
				empty = true;

			} else {

				if (size == 1) {
					number = 1;
					command = stack.pop();

				} else if (size == 2) {
					number = 2;
					word = stack.pop();
					command = stack.pop();

				} else if (size == 3) {
					number = 3;
					type = stack.pop();
					word = stack.pop();
					command = stack.pop();

				} else {
					number = 4;
					while (!stack.isEmpty()) {

						String temp;
						temp = stack.pop();
						dat = dat + temp;
						dat = dat + " ";

						if (stack.size() == 3) {
							type = stack.pop();
							word = stack.pop();
							command = stack.pop();
						}
					}
				}
			}
			dat = dat.trim();
			String[] split = dat.split(" ");
			String data = " ";
			for (int i = split.length - 1; i >= 0; i--) {
				data += (split[i] + " ");
			}
			data = data.trim();

			if (!empty) { // if there is a command entered by the user that needs to run
				switch (command.toLowerCase()) { // switch for different user commands
				case "get":
					if (number == 2) {
						get(dict, word);
					} else {
						System.out.println("Error: please enter a valid command"); // incorrect input format
					}
					break;

				case "delete":
					if (number == 3) {
						delete(dict, word, type);
					} else {
						System.out.println("Error: please enter a valid command");
					}
					break;

				case "put":
					if (number == 4) {
						put(dict, word, type, data);
					} else {
						System.out.println("Error: please enter a valid command");
					}
					break;

				case "list":
					if (number == 2) {
						list(dict, word);
					} else {
						System.out.println("Error: please enter a valid command");

					}
					break;

				case "smallest":
					if (number == 1) {
						smallest(dict);
					} else {
						System.out.println("Error: please enter a valid command");
					}
					break;

				case "largest":
					if (number == 1) {
						largest(dict);
					} else {
						System.out.println("Error: please enter a valid command");
					}
					break;

				case "finish":
					if (number == 1) {
						System.out.println("Have a good day!");
					} else {
						System.out.println("Error: please enter a valid command");
					}
					break;

				default:
					System.out.println("Error: wrong command, please try again!");

				}

			} else {

				System.out.println("Error: please enter a valid command!");
			}
		}
	}

	/**
	 * Method allows the user to find nodes in the tree, which have the inputed word
	 * as their key word, and displays their data.
	 * 
	 * @param dict
	 * @param word
	 *            the word of the key pair of the node the user is searching for
	 */
	public static void get(OrderedDictionary dict, String word) {

		String type = " ";

		Record record1 = dict.get(new Pair(word, "image"));
		Record record2 = dict.get(new Pair(word, "audio"));
		Record record3 = dict.get(new Pair(word, "text"));

		if (record1 == null && record2 == null && record3 == null) { // case where word is not in dictionary

			System.out.println("The word " + word + " is not in the dictionary");

			Record successor = dict.successor(new Pair(word, type));
			Record predecessor = dict.predecessor(new Pair(word, type));

			if ((dict.largest().getKey().getWord().compareTo(word) < 0) // case where tree is empty
					&& (dict.smallest().getKey().getWord().compareTo(word) > 0)) {

				System.out.println("Preceding word: ");
				System.out.println("Following word: ");

			} else if (dict.largest().getKey().getWord().compareTo(word) < 0) { // case where word has no successor in
																				// tree

				System.out.println("Preceding word: " + predecessor.getKey().getWord());
				System.out.println("Following word: ");

			} else if (dict.smallest().getKey().getWord().compareTo(word) > 0) { // case where word has no predecessor
																					// in tree

				System.out.println("Preceding word: ");
				System.out.println("Following word: " + successor.getKey().getWord());

			} else {

				System.out.println("Preceding word: " + predecessor.getKey().getWord());
				System.out.println("Following word: " + successor.getKey().getWord());
			}

		} else if (record2 != null && record3 != null) {

			try {
				SoundPlayer sound = new SoundPlayer();
				sound.play(record2.getData());

			} catch (MultimediaException e2) {
				System.out.println("Error - audio file could not be opened");
			}

			System.out.println(record3.getData());

		} else if (record1 != null && record3 != null) {

			try {
				PictureViewer picture = new PictureViewer();
				picture.show(record1.getData());

			} catch (MultimediaException e3) {
				System.out.println("Error - image file could not be displayed");
			}

			System.out.println(record3.getData());

		} else if (record1 != null && record2 != null) {

			try {
				PictureViewer picture = new PictureViewer();
				picture.show(record1.getData());

			} catch (MultimediaException e4) {
				System.out.println("Error - image file could not be displayed");
			}

			try {
				SoundPlayer sound = new SoundPlayer();
				sound.play(record2.getData());

			} catch (MultimediaException e5) {
				System.out.println("Error - audio file could not be opened");
			}

		} else if (record1 != null) {

			try {
				PictureViewer picture = new PictureViewer();
				picture.show(record1.getData());

			} catch (MultimediaException e6) {
				System.out.println("Error - image file could not be displayed");
			}

		} else if (record2 != null) {

			try {
				SoundPlayer sound = new SoundPlayer();
				sound.play(record2.getData());

			} catch (MultimediaException e7) {
				System.out.println("Error - audio file could not be opened");
			}

		} else {

			System.out.println(record3.getData());
		}

	}

	/**
	 * Methods allows users to remove nodes from the ordered dictionary.
	 * 
	 * @param dict
	 * @param word
	 *            the word key of the node user would like to remove
	 * @param type
	 *            the type key of the node user would like to remove
	 */
	public static void delete(OrderedDictionary dict, String word, String type) {

		try {

			dict.remove(new Pair(word, type));

		} catch (DictionaryException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Method allows user to insert new nodes into the ordered dictionary.
	 * 
	 * @param dict
	 * @param word
	 *            the key word for the new node
	 * @param type
	 *            the key type for the new node
	 * @param data
	 *            the data for the new node
	 */
	public static void put(OrderedDictionary dict, String word, String type, String data) {

		try {

			dict.put(new Record(new Pair(word, type), data));

		} catch (DictionaryException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method allows users to see all the nodes, who's word keys start with inputed
	 * prefix, in the ordered dictionary.
	 * 
	 * @param dict
	 * @param word
	 *            the prefix
	 */
	public static void list(OrderedDictionary dict, String word) {

		String type = " ";

		if (dict.successor(new Pair(word, type)) == null) {

			System.out.println("No words in the ordered dictionary start with prefix " + word);

		} else {

			Record record = dict.successor(new Pair(word, type));

			while (record.getKey().getWord().startsWith(word)) {

				System.out.println(record.getKey().getWord());
				record = dict.successor(record.getKey());
			}
		}
	}

	/**
	 * Method prints the smallest nodes word key, type key and data, for the user.
	 * 
	 * @param dict
	 */
	public static void smallest(OrderedDictionary dict) {

		Record record = dict.smallest();

		if (record == null) {

			System.out.println("Error: the dictionary is empty!");

		} else {

			System.out.println(record.getKey().getWord() + ", " + record.getKey().getType() + ", " + record.getData());
		}

	}

	/**
	 * Method prints the largest nodes word key, type key and data, for the user.
	 * 
	 * @param dict
	 */
	public static void largest(OrderedDictionary dict) {

		Record record = dict.largest();

		if (record == null) {

			System.out.println("Error: the dictionary is empty!");
		} else {

			System.out.println(record.getKey().getWord() + ", " + record.getKey().getType() + ", " + record.getData());
		}
	}
}