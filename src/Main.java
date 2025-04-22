import java.io.IOException;

public class Main {
	public static void main(String[] args) {
		try {
			String filePath = "benchmarks/bridge_19.txt";
			Parser.GraphData graphData = Parser.parseFile(filePath);
			
			System.out.println("Parsed graph data from " + filePath + ":");
			System.out.println("Number of nodes: " + graphData.getNumNodes());
			System.out.println("Number of edges: " + graphData.getEdges().size());
			System.out.println("Edges:");

			for (Edge edge : graphData.getEdges()) {
				System.out.println("  " + edge);
			}
		} catch (IOException e) {
			System.err.println("Error reading file: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
