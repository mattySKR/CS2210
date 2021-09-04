/**
 * This class implements an edge of the graph
 * 
 * @author Matvey Skripchenko
 *
 */

public class GraphEdge {

	// Attribute Declaration
	private GraphNode first;
	private GraphNode second;
	private char streetType;

	/**
	 * The constructor for the class
	 * 
	 * @param u
	 *            first end point
	 * @param v
	 *            second end point
	 * @param busLine
	 *            bus line type
	 */
	public GraphEdge(GraphNode u, GraphNode v, char busLine) {

		this.first = u;
		this.second = v;
		this.streetType = busLine;
	}

	/**
	 * Returns the first end point of the edge
	 * 
	 * @return first end point
	 */
	public GraphNode firstEndpoint() {

		return this.first;
	}

	/**
	 * Returns the second end point of the edge
	 * 
	 * @return second end point
	 */
	public GraphNode secondEndpoint() {

		return this.second;
	}

	/**
	 * Returns the type of the busLine to which the edge belongs
	 * 
	 * @return bus line type
	 */
	char getBusLine() {

		return this.streetType;
	}
}
