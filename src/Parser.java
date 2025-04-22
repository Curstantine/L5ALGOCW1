import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Parser for reading graph data from files.
 * The expected file format is:
 * - First line: number of nodes n (nodes are numbered 0 to n-1)
 * - Following lines: triples "a b c" representing edges from node a to node b with capacity c
 */
public class Parser {

	/**
	 * Parses a file containing graph data.
	 *
	 * @param filePath Path to the file to be parsed
	 * @return A GraphData object containing the number of nodes and list of edges
	 * @throws IOException If an I/O error occurs
	 */
	public static GraphData parseFile(String filePath) throws IOException {
		List<Edge> edges = new ArrayList<>();
		int numNodes;

		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			// Read the first line to get the number of nodes
			String line = reader.readLine();
			numNodes = Integer.parseInt(line.trim());

			// Read each subsequent line to create Edge objects
			while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
				String[] parts = line.trim().split("\\s+");
				if (parts.length == 3) {
					int from = Integer.parseInt(parts[0]);
					int to = Integer.parseInt(parts[1]);
					int capacity = Integer.parseInt(parts[2]);

					edges.add(new Edge(from, to, capacity));
				}
			}
		}

		return new GraphData(numNodes, edges);
	}

	/**
	 * Inner class to hold the parsed graph data.
	 */
	public static class GraphData {
		private final int numNodes;
		private final List<Edge> edges;

		public GraphData(int numNodes, List<Edge> edges) {
			this.numNodes = numNodes;
			this.edges = edges;
		}

		/**
		 * Get the number of nodes in the graph.
		 *
		 * @return The number of nodes
		 */
		public int getNumNodes() {
			return numNodes;
		}

		/**
		 * Get the list of edges in the graph.
		 *
		 * @return The list of edges
		 */
		public List<Edge> getEdges() {
			return edges;
		}

		@Override
		public String toString() {
			return "GraphData{" +
				"numNodes=" + numNodes +
				", edges=" + edges +
				'}';
		}
	}
}
