package assembly;

import graph.Edge;

import java.util.ArrayList;

/**
 * @author dev
 * @param <E>
 */
public class Graph<E> {

	/**
	 * ArrayList to hold edges for random access
	 */
	private ArrayList<Edge<E>> edges;

	public Graph() {
		edges = new ArrayList<Edge<E>>();
	}

	/**
	 * @param vertex
	 * @return - boolean Add Edge to the List
	 */
	public boolean addEdge(Edge<E> vertex) {
		if (edges.contains(vertex))
			return false;
		edges.add(vertex);
		return true;
	}

	public boolean contains(Edge<E> vertex) {
		return edges.contains(vertex);
	}

	public Edge<E> get(int index) {
		return edges.get(index);
	}

	/**
	 * @return : number of Edges in Graph
	 */
	public int count() {
		return edges.size();
	}

	/**
	 * Checks if two graphs are equal. Store all of Edges of larger Graph & Graphs are equal only if temp is unchanged *
	 */
	public boolean equals(Graph<E> other) {

		if (other.edges.size() != edges.size())
			return false;

		ArrayList<Edge<E>> temp = new ArrayList<Edge<E>>(other.edges);

		return temp.retainAll(edges);
	}

	public void reset() {
		for (int i = 0; i < count(); i++)
			get(i).setDistance(Integer.MAX_VALUE);
	}
}
