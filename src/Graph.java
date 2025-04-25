import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A directed graph implementation optimized for flow networks.
 * Uses adjacency lists to represent the graph structure.
 */
public class Graph {
	private final int numNodes;
	private final List<List<Edge>> adjacencyList;
	private final List<Edge> allEdges;

	/**
	 * Constructs a new directed graph with the specified number of nodes.
	 *
	 * @param numNodes The number of nodes in the graph
	 */
	public Graph(int numNodes) {
		this.numNodes = numNodes;
		this.adjacencyList = new ArrayList<>(numNodes);
		this.allEdges = new ArrayList<>();

		for (int i = 0; i < numNodes; i++) {
			adjacencyList.add(new ArrayList<>());
		}
	}

	/**
	 * Constructs a new directed graph from a list of edges and number of nodes.
	 *
	 * @param numNodes The number of nodes in the graph
	 * @param edges    The list of edges to add to the graph
	 */
	public Graph(int numNodes, List<Edge> edges) {
		this(numNodes);
		for (Edge edge : edges) {
			addEdge(edge);
		}
	}

	/**
	 * Adds an edge to the graph.
	 *
	 * @param edge The edge to add
	 */
	public void addEdge(Edge edge) {
		int from = edge.getFrom();
		if (from < 0 || from >= numNodes) {
			throw new IllegalArgumentException("Source node index out of bounds: " + from);
		}

		int to = edge.getTo();
		if (to < 0 || to >= numNodes) {
			throw new IllegalArgumentException("Destination node index out of bounds: " + to);
		}

		adjacencyList.get(from).add(edge);
		allEdges.add(edge);
	}

	/**
	 * Gets all outgoing edges from a node.
	 *
	 * @param node The node index
	 * @return List of outgoing edges
	 */
	public List<Edge> getOutgoingEdges(int node) {
		if (node < 0 || node >= numNodes) {
			throw new IllegalArgumentException("Node index out of bounds: " + node);
		}
		return Collections.unmodifiableList(adjacencyList.get(node));
	}
 
	/**
	 * Gets all edges in the graph.
	 *
	 * @return List of all edges
	 */
	public List<Edge> getAllEdges() {
		return Collections.unmodifiableList(allEdges);
	}

	/**
	 * Gets the number of nodes in the graph.
	 *
	 * @return The number of nodes
	 */
	public int getNumNodes() {
		return numNodes;
	}

	/**
	 * Gets the number of edges in the graph.
	 *
	 * @return The number of edges
	 */
	public int getNumEdges() {
		return allEdges.size();
	}

	/**
	 * Checks if there is an edge from source to destination.
	 *
	 * @param from Source node
	 * @param to   Destination node
	 * @return true if the edge exists, false otherwise
	 */
	public boolean hasEdge(int from, int to) {
		if (from < 0 || from >= numNodes || to < 0 || to >= numNodes) {
			return false;
		}

		for (Edge edge : adjacencyList.get(from)) {
			if (edge.getTo() == to) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Gets the edge from source to destination if it exists.
	 *
	 * @param from Source node
	 * @param to   Destination node
	 * @return The edge if it exists, null otherwise
	 */
	public Edge getEdge(int from, int to) {
		if (from < 0 || from >= numNodes || to < 0 || to >= numNodes) {
			return null;
		}

		for (Edge edge : adjacencyList.get(from)) {
			if (edge.getTo() == to) {
				return edge;
			}
		}
		return null;
	}

	/**
	 * Resets all flows in the graph to zero.
	 */
	public void resetFlows() {
		for (Edge edge : allEdges) {
			edge.setFlow(0);
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Graph with ").append(numNodes).append(" nodes and ").append(allEdges.size()).append(" edges:\n");

		for (int i = 0; i < numNodes; i++) {
			sb.append("Node ").append(i).append(" -> ");
			List<Edge> edges = adjacencyList.get(i);
			if (edges.isEmpty()) {
				sb.append("No outgoing edges");
			} else {
				for (Edge edge : edges) {
					sb.append(edge.getTo()).append("(capacity=").append(edge.getCapacity())
						.append(", flow=").append(edge.getFlow()).append(") ");
				}
			}
			sb.append("\n");
		}

		return sb.toString();
	}
}