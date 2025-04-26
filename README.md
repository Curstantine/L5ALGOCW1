# MaxFlow

Ford-Fulkerson BFS (aka, Edmonds–Karp algorithm) implementation to find the max flow of a graph.

## Benchmark files

The main file references a benchmarks folder, which should include `.txt` files following the below format where node 0
is the source, n-1 is sink.

```text
# benchmarks/bridge_1.txt

6               <- <no_of_nodes>
0 1 4           <- <node_from> <node_two> <edge_capacity>
0 4 1
1 2 2
1 3 1
2 3 1
2 4 1
3 4 2
1 5 1
4 5 4
```