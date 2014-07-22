package greedy;

import graph.Edge;
import graph.Link;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;

import assembly.Graph;

/**
 * @author dev
 * @param <E>
 *            : The type to store in the Graph
 */
public class Dijkstra<E> {
	private static final int INITIAL_CAPACITY = 10;
	public Graph<E> graph;
	private PriorityQueue<Link<E>> heap;
	private Map<Edge<E>, Edge<E>> learnedPath = new LinkedHashMap<Edge<E>, Edge<E>>();

	/**
	 * @param graph
	 *            : The Graph to traverse Constructor - initializes each Node's distance to infinity,used for initial comparison
	 */

	public Dijkstra(Graph<E> graph) {
		graph.reset();
		this.graph = graph;
		heap = new PriorityQueue<Link<E>>(INITIAL_CAPACITY, new Comparator<Link<E>>() {
			public int compare(Link<E> source, Link<E> sink) {
				return source.getDistance() + source.getSource().getDistance()
						- (sink.getDistance() + sink.getSource().getDistance());
			}
		});
	}

	/**
	 * @param start
	 * @param end
	 * @return - shortest distance from source to destination Starting point is the start vertex.Initial distance is 0.
	 *         PriorityQueue is used to determine which Edge to visit next. Nodes are evaluated in a breadth-first search, and
	 *         pushed onto the PriorityQueue. The PriorityQueue is then polled and the process is repeated until the PriorityQueue
	 *         is empty. This method also keeps a track of the vertex being set and its parent from the source to destination.A
	 *         linkedHashMap is used to maintain a combination of <current node(being set),parent>
	 */
	public int heapPath(Edge<E> start, Edge<E> end) {
		start.setDistance(0);

		Edge<E> evaluate = start;

		do {
			LinkedList<Link<E>> links = evaluate.getConnections();

			for (Link<E> link : links)
				heap.add(link);

			Link<E> temp = null;
			if (heap.isEmpty())
				break;
			while (learnedPath.containsKey((temp = heap.peek()).getSource())
					&& learnedPath.containsKey((temp = heap.peek()).getSink()))
				temp = heap.poll();

			temp = heap.poll();

			evaluate = temp.getSink();

			int distance = evaluate.getDistance();
			int newDist = temp.getSource().getDistance() + temp.getDistance();

			if (newDist < distance) {
				evaluate.setDistance(newDist);
				if (learnedPath.containsKey(temp.getSink()))
					break;
				learnedPath.put(temp.getSink(), temp.getSource());
			}

		} while (!heap.isEmpty());

		return end.getDistance();
	}

	/**
	 * @param start
	 * @param end
	 * @return path- shortest path from source to destination input - source edge,destination edge This is an orchestration method
	 *         that invokes the method that calculates the shortest distance - heapPath and also invokes the method to plot the
	 *         path.- plotPath .Returns the shortest path from source to destination.
	 */

	public String getPath(Edge<E> start, Edge<E> end) {
		heapPath(start, end);
		return plotPath(start, end, graph.count());
	}

	/**
	 * @param start
	 * @param end
	 * @param length
	 * @return - shortest path from source to destination is it is a valid path,"Sink unreachable" otherwise. This method iterates
	 *         over the learnedPath , from sink to source to capture the shortest path.Map is of the form <vertex_set,parent>,the
	 *         method goes from the destination,picking up its parent and using the parent as the next vertex.A path reversal is
	 *         used at the end.
	 */

	public String plotPath(Edge<E> start, Edge<E> end, int length) {
		StringBuffer sb = new StringBuffer();
		Edge<E> sink = end;
		int i = 0;

		while (learnedPath.get(end) != start && i++ < length) {
			sb.append(learnedPath.get(end));
			end = learnedPath.get(end);
		}

		if (learnedPath.get(end) == start && learnedPath.get(start) != end) {
			sb.reverse();
			sb.insert(0, start);
			sb.append(sink);
			return sb.toString();
		}
		return "Sink Unreachable";
	}
}
