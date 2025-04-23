import java.util.List;

public record Graph(int numNodes, List<Edge> edges) {
	/**
	 * Get the number of nodes in the graph.
	 *
	 * @return The number of nodes
	 */
	@Override
	public int numNodes() {
		return numNodes;
	}

	/**
	 * Get the list of edges in the graph.
	 *
	 * @return The list of edges
	 */
	@Override
	public List<Edge> edges() {
		return edges;
	}


	@Override
	public String toString() {
		return "Graph{" +
			"numNodes=" + numNodes +
			", edges=" + edges +
			'}';
	}
}
