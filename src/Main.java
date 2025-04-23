import java.io.IOException;

public class Main {
	public static void main(String[] args) {
		try {
			String filePath = "benchmarks/bridge_19.txt";
			Graph graph = Parser.parseFile(filePath);

			System.out.println("Parsed graph from " + filePath + ":");
			System.out.println("Number of nodes: " + graph.numNodes());
			System.out.println("Number of edges: " + graph.edges().size());
			System.out.println("Edges:");

			for (Edge edge : graph.edges()) {
				System.out.println("  " + edge);
			}
		} catch (IOException e) {
			System.err.println("Error reading file: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
