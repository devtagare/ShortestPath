package graph;

public class Link<E> implements Comparable<Link<E>> {

  private Edge<E> source, sink;
  private int distance;

  /*
   * constructor- creates a Link object to Connect two Nodes together with a standard distance of 0
   * , it is assumed That distance is either weighted through the Edges or otherwise is irrelevant
   */
  public Link(Edge<E> source, Edge<E> sink) {
    this(source, sink, 0);
  }

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

  /*
   * Two connectors are equal if the two Edges are equal and the distance is equal
   */
  public boolean equals(Link<E> other) {
    return source.equals(other.getSource()) && sink.equals(other.getSink())
        && distance == other.getDistance();
  }

  @Override
  public String toString() {
    return "Link [source=" + source + ", sink=" + sink + ", distance=" + distance + "]";
  }

  public int compareTo(Link<E> other) {
    return this.distance - other.distance;
  }
}
