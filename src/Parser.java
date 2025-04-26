/*
 * Name: Rachala Ovin Gunawardana
 * UoW ID: 20522735
 */

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
	 * Parses a file containing a graph.
	 *
	 * @param filePath Path to the file to be parsed
	 * @return A Graph object containing the number of nodes and list of edges
	 * @throws IOException If an I/O error occurs
	 */
	public static DataGraph parseFile(String filePath) throws IOException {
		List<Edge> edges = new ArrayList<>();
		int numNodes;

		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line = reader.readLine();
			numNodes = Integer.parseInt(line.trim());

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

		return new DataGraph(numNodes, edges);
	}

	public record DataGraph(int numNodes, List<Edge> edges) {
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

}
