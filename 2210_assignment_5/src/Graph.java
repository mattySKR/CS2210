
/**
 * This class implements an undirected graph using adjacency matrix structure
 * 
 * @author Matvey Skripchenko 
 */

import java.util.Iterator;
import java.util.Stack;

public class Graph implements GraphADT {

	// Attribute Declaration
	private GraphNode[] nodes; // array that will store the GraphNodes
	private GraphEdge[][] edges; // adjacency matrix (2-D array) that will hold the reference to the GraphNodes,
									// where
									// each GraphNode is a reference to an array of GraphEdges

	/**
	 * Creates a graph with n nodes and no edges. This is the constructor for the
	 * class. The names of the nodes are 0, 1, . . . , n−1
	 * 
	 * @param n
	 *            graph size
	 */
	public Graph(int n) {

		this.nodes = new GraphNode[n];
		this.edges = new GraphEdge[n][n];

		for (int i = 0; i < n; i++) {

			nodes[i] = new GraphNode(i); // creating an array of the specified number of GraphNodes
		}
	}

	/**
	 * Adds an edge connecting u and v and belonging to the specified bus line
	 * 
	 * @param u
	 *            first node
	 * 
	 * @param v
	 *            second node
	 * 
	 * @param busLine
	 *            bus line type
	 * 
	 * @throws GraphException
	 *             if GraphNode DNE
	 */
	public void insertEdge(GraphNode u, GraphNode v, char busLine) throws GraphException {

		int firstNodeName = u.getName(); // variable holding the name of the GraphNode u
		int secondNodeName = v.getName(); // variable holding the name of the GraphNode v
		int nodeVectorSize = nodes.length; // variable holding the size of the array holding the GraphNodes
		GraphEdge newEdge = new GraphEdge(u, v, busLine); // edge connecting the specified GraphNodes
		GraphEdge newEdgeSymmetric = new GraphEdge(v, u, busLine); // symmetric edge in the matrix connecting the same
																	// GraphNodes

		// if the GraphNode does not exist, then throwing exception
		if ((firstNodeName >= nodeVectorSize) || (firstNodeName < 0) || (secondNodeName >= nodeVectorSize)
				|| (secondNodeName < 0)) {

			throw new GraphException("Error: the node does not exist!");

		} else if ((this.edges[firstNodeName][secondNodeName]) != null) { // if the there is already a GraphEdge in the
																			// graph connecting these 2 GraphNodes, then
																			// throwing exception

			throw new GraphException("Error: there is already an edge in the graph connecting these nodes!");

		} else { // inserting the edges connecting the given GraphNodes, otherwise

			edges[firstNodeName][secondNodeName] = newEdge; // inserting GraphEdge connecting the specified nodes
			edges[secondNodeName][firstNodeName] = newEdgeSymmetric; // inserting symmetric GraphEdge in the matrix
																		// connecting the same GraphNodes, needed for
																		// undirected graph
		}
	}

	/**
	 * Returns the node with the specified name
	 * 
	 * @param name
	 *            node name
	 * 
	 * @return node with specified name
	 * 
	 * @throws GraphException
	 *             if GraphNode with specified name DNE
	 */
	public GraphNode getNode(int name) throws GraphException {

		// if there is no GraphNode with the specified name, then throwing exception
		if ((nodes.length <= name) || (nodes[name] == null)) {

			throw new GraphException("Error: no node with this name exists!");

		} else { // if there is a GraphNode with the specified name

			return nodes[name]; // returning the GraphNode with the specified name
		}
	}

	/**
	 * Returns a Java Iterator storing all the edges incident on node u
	 * 
	 * @param u
	 *            node u
	 * 
	 * @return Java Iterator storing all the edges incident on u or null, otherwise
	 * 
	 * @throws GraphException
	 *             if GraphNode u DNE
	 */
	public Iterator<GraphEdge> incidentEdges(GraphNode u) throws GraphException {

		int firstNodeName = u.getName(); // variable holding the name of the GraphNode u

		if ((firstNodeName >= nodes.length) || (firstNodeName < 0)) { // if the GraphNode u does not exist, throwing
																		// exception

			throw new GraphException("Error: this node is not in the graph!");

		} else {

			Stack<GraphEdge> edgeStack = new Stack<>(); // initializing a stack that will hold the GraphEdges incident
														// on GraphNode u

			for (int i = 0; i < nodes.length; i++) {

				// making sure the GraphNode exists and its' respective GraphEdge also exists
				if ((u.getName() < nodes.length) && (u.getName() >= 0) && (edges[u.getName()][i] != null)) {

					GraphEdge incidentEdge = edges[u.getName()][i];
					edgeStack.push(incidentEdge);
				}
			}
			Iterator<GraphEdge> edgeIterator = edgeStack.iterator(); // obtaining the Java Iterator from the stack

			if (edgeStack.size() == 0) { // if there is no incident GraphEdges on GraphNode u, then returning null

				return null;

			} else { // returning the Java Iterator storing all GraphEdges incident on GraphNode u,
						// otherwise

				return edgeIterator;
			}
		}
	}

	/**
	 * Returns the edge connecting nodes u and v
	 * 
	 * @param u
	 *            first node
	 * 
	 * @param v
	 *            second node
	 * 
	 * @return edge connecting nodes u and v
	 * 
	 * @throws GraphException
	 *             if there is no edge between u and v
	 */
	public GraphEdge getEdge(GraphNode u, GraphNode v) throws GraphException {

		int firstNodeName = u.getName(); // variable holding the name of the GraphNode u
		int secondNodeName = v.getName(); // variable holding the name of the GraphNode v
		int nodeVectorSize = nodes.length; // variable holding the size of the array holding the GraphNodes

		// if there is no GraphEdge connecting GraphNodes u and v, then throwing
		// exception
		if ((firstNodeName >= nodeVectorSize) || (firstNodeName < 0) || (secondNodeName >= nodeVectorSize)
				|| (secondNodeName < 0)) {

			throw new GraphException("Error: the node does not exist!");

		} else { // returning the GraphEdge connecting GraphNodes u and v, otherwise

			return edges[u.getName()][v.getName()];
		}
	}

	/**
	 * Returns true if nodes u and v are adjacent; it returns false otherwise
	 * 
	 * @param u
	 *            first node
	 * 
	 * @param v
	 *            second node
	 * 
	 * @return true or false
	 * 
	 * @throws GraphException
	 *             if GraphNodes u and v DNE
	 */
	public boolean areAdjacent(GraphNode u, GraphNode v) throws GraphException {

		int firstNodeName = u.getName(); // variable holding the name of the GraphNode u
		int secondNodeName = v.getName(); // variable holding the name of the GraphNode v
		int nodeVectorSize = nodes.length; // variable holding the size of the array holding the GraphNodes

		// if the GraphNodes u and v do not exist, then throwing exception
		if ((firstNodeName >= nodeVectorSize) || (firstNodeName < 0) || (secondNodeName >= nodeVectorSize)
				|| (secondNodeName < 0)) {

			throw new GraphException("Error: these nodes are not in the graph!");

		} else if ((edges[firstNodeName][secondNodeName] != null)) {
			// if u and v are adjacent and exist, then returning true
			return true;

		} else { // returning false, otherwise

			return false;
		}
	}
}
