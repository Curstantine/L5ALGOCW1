/*
 * Name: Rachala Ovin Gunawardana
 * UoW ID: 20522735
 */

public class Edge {
	private final int from;
	private final int to;
	private final int capacity;
	private int flow;

	/**
	 * Constructor for creating a directed edge
	 *
	 * @param from     The source vertex
	 * @param to       The destination vertex
	 * @param capacity The maximum capacity of the edge
	 */
	public Edge(int from, int to, int capacity) {
		this.from = from;
		this.to = to;
		this.capacity = capacity;
		this.flow = 0;
	}

	/**
	 * Get the source vertex
	 *
	 * @return The source vertex
	 */
	public int getFrom() {
		return from;
	}

	/**
	 * Get the destination vertex
	 *
	 * @return The destination vertex
	 */
	public int getTo() {
		return to;
	}

	/**
	 * Get the capacity of the edge
	 *
	 * @return The capacity
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * Get the current flow through the edge
	 *
	 * @return The current flow
	 */
	public int getFlow() {
		return flow;
	}

	/**
	 * Set the flow through the edge
	 *
	 * @param flow The new flow value
	 */
	public void setFlow(int flow) {
		this.flow = flow;
	}

	/**
	 * Calculate the residual capacity of the edge
	 *
	 * @return The residual capacity (capacity - flow)
	 */
	public int getResidualCapacity() {
		return capacity - flow;
	}

	/**
	 * Add additional flow to the edge
	 *
	 * @param additionalFlow The amount of flow to add
	 */
	public void addFlow(int additionalFlow) {
		if (additionalFlow > getResidualCapacity()) {
			throw new IllegalArgumentException("Cannot add flow greater than residual capacity");
		}

		this.flow += additionalFlow;
	}


	@Override
	public String toString() {
		return "Edge{" +
			"from=" + from +
			", to=" + to +
			", capacity=" + capacity +
			", flow=" + flow +
			'}';
	}
}
