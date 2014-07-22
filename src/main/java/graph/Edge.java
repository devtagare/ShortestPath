package graph;

import java.util.LinkedList;

/**
 * @author dev
 * @param <E>
 *            Base class for creating an edge.Stores the number of edges,an Element <E> - used in weighted graphs and game theory
 *            in-case of incoming gains,this is a provision for the change in fuel prices or new routes. It has individual edge
 *            identifier,weight and pointers for reference to other edges.
 */
public class Edge<E> {
	private static int ID = 0;
	private E element;
	private int id;
	private int weight;
	private LinkedList<Link<E>> pointers;

	public Edge(E elem, int distance) {
		this.element = elem;
		id = ID++;
		pointers = new LinkedList<Link<E>>();
		this.weight = distance;
	}

	public int getDistance() {
		return weight;
	}

	public void setDistance(int dist) {
		weight = dist;
	}

	public void connectTo(Edge<E> other, int distance) {
		Link<E> c = new Link<E>(this, other, distance);
		if (!pointers.contains(c))
			pointers.add(c);
	}

	public LinkedList<Link<E>> getConnections() {
		return pointers;
	}

	@Override
	public String toString() {
		return this.element.toString();
	}
}
