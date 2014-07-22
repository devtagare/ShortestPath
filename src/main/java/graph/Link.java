package graph;

public class Link<E> implements Comparable<Link<E>> {
	private Edge<E> source, sink;
	private int distance;

	public Link(Edge<E> source, Edge<E> sink, int distance) {
		this.source = source;
		this.sink = sink;
		this.distance = distance;
	}

	public Edge<E> getSource() {
		return source;
	}

	public Edge<E> getSink() {
		return sink;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	@Override
	public String toString() {
		return "Link [source=" + source + ", sink=" + sink + ", distance=" + distance + "]";
	}

	public int compareTo(Link<E> other) {
		return this.distance - other.distance;
	}
}
