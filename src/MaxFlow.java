import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Implementation of the Ford-Fulkerson algorithm with BFS for finding maximum flow in a flow network.
 */
public class MaxFlow {
	private final Graph graph;
	private final int source;
	private final int sink;
	private int iterationCount;

	public MaxFlow(Graph graph, int source, int sink) {
		this.graph = graph;
		this.source = source;
		this.sink = sink;
		this.iterationCount = 0;
	}

	/**
	 * Computes the maximum flow from source to sink using the Ford-Fulkerson algorithm with BFS.
	 *
	 * @return The maximum flow value
	 */
	public int computeMaxFlow() {
		graph.resetFlows();
		iterationCount = 0;

		int maxFlow = 0;
		int[] parent = new int[graph.getNumNodes()];
		Graph residualGraph = createResidualGraph();
		
		while (bfs(residualGraph, parent)) {
			iterationCount++;

			// Note: MAX_VALUE is being used as the resolution code depends on the minimum
			// value of path flow and residual capacity to find the bottleneck
			int pathFlow = Integer.MAX_VALUE;

			for (int v = sink; v != source; v = parent[v]) {
				int u = parent[v];

				Edge edge = residualGraph.getEdge(u, v);
				if (edge != null) pathFlow = Math.min(pathFlow, edge.getResidualCapacity());
			}

			// Update the flows in the original graph
			for (int v = sink; v != source; v = parent[v]) {
				int u = parent[v];

				// Check if this is a forward edge or a backward edge in the original graph
				Edge originalEdge = graph.getEdge(u, v);
				if (originalEdge != null) {
					// Forward-edge - increase flow
					originalEdge.addFlow(pathFlow);
				} else {
					// Backward edge - decrease flow in the reverse direction
					Edge reverseEdge = graph.getEdge(v, u);
					if (reverseEdge != null) {
						reverseEdge.setFlow(reverseEdge.getFlow() - pathFlow);
					}
				}
			}

			maxFlow += pathFlow;
			residualGraph = createResidualGraph();
		}

		return maxFlow;
	}

	/**
	 * Creates a residual graph from the current flow state.
	 *
	 * @return The residual graph
	 */
	private Graph createResidualGraph() {
		Graph residualGraph = new Graph(graph.getNumNodes());

		// Add forward edges with residual capacity
		for (Edge edge : graph.getAllEdges()) {
			int from = edge.getFrom();
			int to = edge.getTo();
			int residualCapacity = edge.getResidualCapacity();

			if (residualCapacity > 0) {
				residualGraph.addEdge(new Edge(from, to, residualCapacity));
			}

			// Add backward edge with flow as capacity
			int flow = edge.getFlow();
			if (flow > 0) {
				residualGraph.addEdge(new Edge(to, from, flow));
			}
		}

		return residualGraph;
	}

	/**
	 * Performs BFS to find an augmenting path from source to sink.
	 *
	 * @param residualGraph The residual graph
	 * @param parent        Array to store the parent of each node in the path
	 * @return True if an augmenting path exists, false otherwise
	 */
	private boolean bfs(Graph residualGraph, int[] parent) {
		boolean[] visited = new boolean[residualGraph.getNumNodes()];
		Arrays.fill(parent, -1);

		Queue<Integer> queue = new LinkedList<>();
		queue.add(source);
		visited[source] = true;

		while (!queue.isEmpty()) {
			int currentNode = queue.poll();

			for (Edge edge : residualGraph.getOutgoingEdges(currentNode)) {
				int nextNode = edge.getTo();

				if (!visited[nextNode] && edge.getResidualCapacity() > 0) {
					queue.add(nextNode);
					parent[nextNode] = currentNode;
					visited[nextNode] = true;
				}
			}
		}

		// If we reached the sink in BFS, then there is an augmenting path
		return visited[sink];
	}


	/**
	 * Get the iteration count of this run.
	 *
	 * @return Integer of the iterations
	 */
	public int getIterationCount() {
		return iterationCount;
	}
}
