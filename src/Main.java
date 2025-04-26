/*
 * Name: Rachala Ovin Gunawardana
 * UoW ID: 20522735
 */

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Main {
	static final String folderPath = "./benchmarks";

	public static void main(String[] args) throws IOException {
		if (args.length > 0) {
			final File file = new File(String.format("%s/%s.txt", folderPath, args[0].replaceFirst("\\.txt$", "")));

			if (!file.exists()) {
				System.err.format("File %s does not exist in %s", args[0], folderPath);
				return;
			}

			runBenchmark(file);
			return;
		}


		File folder = new File(folderPath);
		File[] files = folder.listFiles();

		if (!folder.exists() || !folder.isDirectory() || files == null) {
			System.err.println("Invalid folder path: " + folderPath);
			return;
		}

		Arrays.sort(files, (a, b) -> {
			String aName = a.getName();
			String bName = b.getName();
			if (aName.startsWith("bridge") && !bName.startsWith("bridge")) return -1;
			if (!aName.startsWith("bridge") && bName.startsWith("bridge")) return 1;
			return extractTestNumber(aName) - extractTestNumber(bName);
		});


		for (File file : files) {
			runBenchmark(file);
		}
	}

	static void runBenchmark(File filePath) throws IOException {
		System.out.println("\n\n########## Benchmark Starting ##########");
		System.out.println("Loading graph from: " + filePath);
		Parser.DataGraph data = Parser.parseFile(filePath.getPath());
		Graph graph = new Graph(data.numNodes(), data.edges());
		System.out.println("Graph loaded with " + graph.getNumNodes() + " nodes and " + graph.getNumEdges() + " edges.");

		final int source = 0;
		final int sink = graph.getNumNodes() - 1;
		final MaxFlow maxFlow = new MaxFlow(graph, source, sink);

		System.out.println("\n--- Running Ford-Fulkerson Algorithm ---");
		System.out.println("Source: " + source + ", Sink: " + sink);
		final long startTime = System.nanoTime();
		final int maxFlowValue = maxFlow.computeMaxFlow();

		System.out.println("\n--- Summary ---");
		System.out.println("Total iterations: " + maxFlow.getIterationCount());
		System.out.println("Maximum flow from node " + source + " to node " + sink + ": " + maxFlowValue);
		System.out.println("Elapsed time: " + toHumanTime(System.nanoTime() - startTime));
	}

	static String toHumanTime(long nano) {
		if (nano >= 1_000_000_000) {
			return String.format("%.2fs", nano / 1_000_000_000.0);
		}

		if (nano >= 1_000_000) {
			return String.format("%.2fms", nano / 1_000_000.0);
		}

		if (nano >= 1_000) {
			return String.format("%.2fµs", nano / 1_000.0);
		}

		return nano + "ns";
	}

	static int extractTestNumber(String filename) {
		final String replaced = filename.replaceFirst("(bridge|ladder)_", "").replaceFirst(".txt", "");
		return Integer.parseInt(replaced);
	}
}
