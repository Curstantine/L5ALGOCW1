import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		final String filePath = "benchmarks/bridge_2.txt";

		Parser.DataGraph data = Parser.parseFile(filePath);
		Graph graph = new Graph(data.numNodes(), data.edges());

		printGraphData(graph, filePath);

	}

	public static void printGraphData(Graph graph, String filePath) {
		System.out.println("\nDirected Graph of " + filePath);
		System.out.println(graph);

		int nodeToCheck = 1;
		if (graph.getNumNodes() > 0) {
			System.out.println("Outgoing edges from node " + nodeToCheck + ":");
			for (Edge edge : graph.getOutgoingEdges(nodeToCheck)) {
				System.out.println("  " + nodeToCheck + " -> " + edge.getTo() +
					" (capacity: " + edge.getCapacity() + ")");
			}

			System.out.println("Incoming edges to node " + nodeToCheck + ":");
			for (Edge edge : graph.getIncomingEdges(nodeToCheck)) {
				System.out.println("  " + edge.getFrom() + " -> " + nodeToCheck +
					" (capacity: " + edge.getCapacity() + ")");
			}
		}
	}
}
