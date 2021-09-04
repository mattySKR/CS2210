
/**
 * This class represents the city map with bus lines
 * 
 * @author Matvey Skripchenko
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Stack;

public class BusLines {

	// Attribute declaration
	private Graph busMap; // The graph/map that will be initialized after data extraction from the file
	private int mapWidth; // Width of the map
	private int mapLength; // Length/height of the map
	private int busChanges; // Number of the allowed bus line changes
	private int startingPoint; // Starting point S on the map
	private int destinationPoint; // Destination point D on the map

	/**
	 * Constructor for building a city map with its bus lines from the input file
	 * 
	 * @param inputFile
	 *            file
	 * @throws MapException
	 *             if file DNE
	 * @throws GraphException
	 *             based on Graph class exceptions
	 */
	public BusLines(String inputFile) throws MapException, GraphException {

		try {

			BufferedReader in = new BufferedReader(new FileReader(inputFile)); // reading the input file
			String thisLine = in.readLine(); // reading the first line of the input file that has the needed map
												// parameters
			String[] first = thisLine.split("\\s+"); // splitting with whitespace and storing contents in the array of
														// strings
			int[] firstLineToInt = new int[first.length]; // creating an array of integers of size equal to the
															// firstLine string array

			for (int i = 0; i < first.length; i++) {

				String stringInteger = first[i]; // extracting the string one position at a time
				firstLineToInt[i] = Integer.parseInt(stringInteger); // converting the string to integer and storing at
																		// the respective position of integer array

			}

			// assigning the extracted values from the input file's first line to respective
			// variables. Also, skipping the position 0 in the array (map scale factor), as
			// the scale factor is not needed here
			mapWidth = firstLineToInt[1];
			mapLength = firstLineToInt[2];
			busChanges = firstLineToInt[3];

			busMap = new Graph(mapLength * mapWidth); // creating the graph (bus map) of the specified dimension (size
														// n)

			int c = 0; // counter / positions for edge placement

			// variable storing the next every 2 lines from file
			String evenLine;
			String oddLine;

			while ((evenLine = in.readLine()) != null) {

				oddLine = in.readLine(); // repeatedly reading 2 lines from file (while first line is not null)

				int lineWidth = mapWidth * 2 - 1; // line width of the inputFile

				for (int i = 0; i < lineWidth; i++) {

					if (i % 2 == 0) { // if even index

						if (evenLine.charAt(i) == 'S') { // if i-th character is the starting Node

							startingPoint = c; // assigning the name

						} else if (evenLine.charAt(i) == 'D') { // if the i-th character is the destination Node

							destinationPoint = c; // assigning the name

						} else {

							if (oddLine != null) {

								if (oddLine.charAt(i) != ' ') { // making sure the i-th character is not a whitespace /
																// house

									// inserting edges between node c and node c + mapWidth
									busMap.insertEdge(busMap.getNode(c), busMap.getNode(c + mapWidth),
											oddLine.charAt(i));
								}
							}
						}

					} else if ((i % 2) != 0) { // if odd index

						if (evenLine.charAt(i) != ' ') { // making sure the i-th character is not a whitespace / house

							busMap.insertEdge(busMap.getNode(c), busMap.getNode(c + 1), evenLine.charAt(i));
						}
						c++; // incrementing counter
					}

				}
				c++; // incrementing counter

			}
			in.close();

		} catch (GraphException e) {

			throw new GraphException("Error: the graph cannot be created!");

		} catch (IOException e) {

			throw new MapException("Error: the map could not be created --> potential inputFile issue!");

		}

	}

	/**
	 * Returns the graph representing the map
	 * 
	 * @return graph
	 * @throws MapException
	 *             if the graph could not be created
	 */
	public Graph getGraph() throws MapException {

		if (busMap == null) {

			throw new MapException("Error: the graph cannot be created!");

		} else {

			return busMap;

		}
	}

	/**
	 * Returns a Java Iterator containing the nodes along the path from the starting
	 * point to the destination, if such a path exists. If the path does not exist,
	 * this method returns the value null
	 * 
	 * @return null or Java Iterator with nodes
	 * @throws GraphException
	 *             based on Graph class exceptions
	 */
	public Iterator<GraphNode> trip() throws GraphException {

		Stack<GraphNode> trip = new Stack<>();

		if (findPath(trip, busMap.getNode(startingPoint), busMap.getNode(destinationPoint), ' ', 0)) {

			return trip.iterator();

		} else {

			return null;
		}
	}

	/**
	 * Helper method for finding the path. Returns true if the path to destination
	 * was found or false, otherwise
	 * 
	 * @param nodeStack
	 *            stack holding the path nodes
	 * @param st
	 *            starting node
	 * @param fin
	 *            destination node
	 * @param currentBusLine
	 *            current bus line
	 * @param currentBusChanges
	 *            number of bus changes made
	 * @return true or false
	 * 
	 * @throws GraphException
	 *             based on Graph class exceptions
	 */
	private boolean findPath(Stack<GraphNode> nodeStack, GraphNode st, GraphNode fin, char currentBusLine,
			int currentBusChanges) throws GraphException {

		st.setMark(true); // marking the node true (visited) before pushing onto stack
		nodeStack.push(st); // pushing the node onto stack
		Iterator<GraphEdge> inEdgeStack = busMap.incidentEdges(st); // getting all the incident edges on the node st

		char realBusLine; // char variable that will later hold the bus line type
							// between two nodes for comparison

		if (currentBusChanges > busChanges) { // making sure that the allowed number of bus changes is not exceeded

			return false;

		} else if (st.getName() == fin.getName()) { // checking if the destination point has been reached

			return true;
		}

		while (inEdgeStack.hasNext()) { // while there are more path possibilities

			GraphEdge newEdge = inEdgeStack.next(); // edge connecting the 2 nodes
			GraphNode connector = newEdge.secondEndpoint(); // marking the new node as a second endpoint that the edge
															// will connect to
			realBusLine = newEdge.getBusLine(); // getting the bus line type between these 2 nodes

			if (!connector.getMark()) { // checking if the next node has yet been visited

				if (startingPoint == st.getName()) { // checking if the trip starts at the startingPoint

					currentBusLine = newEdge.getBusLine(); // if so, then we have the assigned bus line ready to go

				}

				// making sure the bus is on the same bus line, because the bus line
				// change would happen only if the adjacent edges are of different type
				if (currentBusLine == realBusLine) {

					// using the recursion to find the path. If not found, then there is no solution
					// or the node will be popped of the stack (then marked false/visited) and the
					// search process will be repeated or bus line change will happen, returns true
					// otherwise
					if (findPath(nodeStack, connector, fin, currentBusLine, currentBusChanges)) {

						return true;
					}

				} else { // if not on the same bus line

					if (currentBusChanges < busChanges) { // checking if the allowed number of bus line changes is not
															// exceeded

						// using same recursive call, but with an incremented number of bus line changes
						if (findPath(nodeStack, connector, fin, realBusLine, currentBusChanges + 1)) {

							return true;
						}
					}
				}
			}
		}
		st.setMark(false); // marking the node as visited if no current solution was found
		nodeStack.pop(); // getting rid of the incorrect path

		return false; // if path to destination was not found
	}
}