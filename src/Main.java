import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		final String filePath = "benchmarks/bridge_1.txt";

		Parser.DataGraph data = Parser.parseFile(filePath);
		Graph graph = new Graph(data.numNodes(), data.edges());

		final int source = 0;
		final int sink = graph.getNumNodes() - 1;
		final MaxFlow maxFlow = new MaxFlow(graph, source, sink);
		final int maxFlowValue = maxFlow.computeMaxFlow();

		System.out.println("\nMaximum flow from node " + source + " to node " + sink + ": " + maxFlowValue);
	}
}
