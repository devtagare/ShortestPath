package graph;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

public class Edge<E> {

  // # - edges
  private static int ID = 0;

  // used in weighted graphs and game theory in-case of incoming gains,this is a provision for the
  // change in fuel prices or new routes
  private E element;

  // the individual Edge identifier
  private int id;

  private int weight;

  // pointers for reference to other Edges
  private LinkedList<Link<E>> pointers;

  // constructors
  public Edge() {
    // invoke constructor to initialize elem to null pointer
    this(null, Integer.MAX_VALUE);
  }

  public Edge(E elem, int distance) {
    this.element = elem;
    id = ID++;
    pointers = new LinkedList<Link<E>>();
    this.weight = distance;
  }

  // accessors and mutators
  public int getId() {
    return id;
  }

  public E getElem() {
    return element;
  }

  public void setElem(E elem) {
    this.element = elem;
  }

  public int getDistance() {
    return weight;
  }

  public void setDistance(int dist) {
    weight = dist;
  }

  // add a connection
  public void connectTo(Edge<E> other) {
    Link<E> c = new Link<E>(this, other);

    // check for duplicates
    if (!pointers.contains(c))
      pointers.add(c);

    // reference Connector in other Edge as well
    LinkedList<Link<E>> conn = other.getConnections();
    if (!conn.contains(c))
      conn.add(c);
  }

  public void connectTo(Edge<E> other, int distance) {
    Link<E> c = new Link<E>(this, other, distance);
    if (!pointers.contains(c))
      pointers.add(c);
  }

  public LinkedList<Link<E>> getConnections() {
    return pointers;
  }

  public void sortConnections() {
    Collections.sort(pointers);
  }

  public Iterator<Link<E>> iterator() {
    return pointers.iterator();
  }

  // one Edge is equal to another if the two elems are equal to each other
  // and they have the same Connections
  public boolean equals(Edge<E> other) {

    if (other.pointers.size() != pointers.size())
      return false;

    LinkedList<Link<E>> temp = new LinkedList<Link<E>>(pointers);

    // edges are order agnostic
    // if the elements are equal and the Lists are equal, regardless of order
    // then the Edges are equal
    return element.equals(other.getElem()) && temp.retainAll(other.pointers);
  }

  public String toString() {
    return this.element.toString();
  }
}
