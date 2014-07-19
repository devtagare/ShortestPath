package greedy;

import graph.Edge;
import graph.Link;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;

import assembly.Graph;

/**
 * @param E: The type to store in the Graph
 * */

public class Dijkstra<E> {

  // the Graph to traverse
  private Graph<E> graph;

  // the PriorityQueue for the heap-based sort
  private PriorityQueue<Link<E>> heap;

  Map<Edge<E>, Edge<E>> learnedPath;
  String path;
  Boolean isPathValid;
  Integer distance;

  private static final int INITIAL_CAPACITY = 10;


  /**
   * @param graph: The Graph to traverse
   * 
   *        Constructor - initializes each Node's distance to infinity,used for initial comparison
   * */

  public Dijkstra(Graph<E> graph) {
    this.graph = graph;
    learnedPath = new LinkedHashMap<Edge<E>, Edge<E>>();
    resetGraph();
    setIsPathValid(false);
    distance = new Integer(0);

    heap = new PriorityQueue<Link<E>>(INITIAL_CAPACITY, new Comparator<Link<E>>() {
      public int compare(Link<E> source, Link<E> sink) {
        return (source.getDistance() + source.getSource().getDistance())
            - (sink.getDistance() + sink.getSource().getDistance());
      }
    });
  }

  // initializes the Graph Nodes to infinity
  public void resetGraph() {
    for (int i = 0; i < this.graph.count(); i++) {
      this.graph.get(i).setDistance(Integer.MAX_VALUE);
      this.graph.get(i);
    }
  }

  /***
   * input - starting vertex,end vertex
   * 
   * PriorityQueue is used to determine which Edge to visit next. Nodes are evaluated in a
   * breadth-first search, and pushed onto the PriorityQueue. The PriorityQueue is then polled and
   * the process is repeated until the PriorityQueue is empty.
   * */

  public int heapPath(Edge<E> start, Edge<E> end) {
    start.setDistance(0); // initialize the start distance to 0

    // the Edge to evaluate. We evaluate the start Node first
    Edge<E> evaluate = start;

    // as long as we have elements in the Graph to traverse
    do {
      // push evaluate's children onto the PriorityQueue
      LinkedList<Link<E>> links = evaluate.getConnections();
      Iterator<Link<E>> iterate = links.iterator();

      while (iterate.hasNext()) {
        Link<E> conn = iterate.next();
        heap.add(conn);
      }

      // then poll the PriorityQueue to determine
      // which Node to visit next
      Link<E> temp = null;
      if (!heap.isEmpty()) {
        temp = heap.poll();
        if (learnedPath.containsKey(temp.getSource()) && learnedPath.containsKey(temp.getSink())
            || temp.getSink().getElem().equals(start.getElem())) {

          temp = heap.poll();
        }

      } else {
        break;
      }

      evaluate = temp.getSink();

      // and update that Node's distance if a shorter path is found
      int distance = evaluate.getDistance();
      int newDist = temp.getSource().getDistance() + temp.getDistance();

      // set the distance and also make an entry in the map of the form <current node,parent>

      if (newDist < distance) {
        evaluate.setDistance(newDist);
        if (!learnedPath.containsKey(temp.getSink())) {
          learnedPath.put(temp.getSink(), temp.getSource());
          System.out.println("Learned" + temp.getSink());
        } else {
          System.out.println("Inside else");
          break;
        }
      }

    } while (!heap.isEmpty());

    return end.getDistance();
  }

  public String getPath(Edge<E> start, Edge<E> end) {
    distance = heapPath(start, end);
    path = plotPath(start, end, graph.count());
    return path;
  }

  public String plotPath(Edge<E> start, Edge<E> end, int length) {

    StringBuffer sb = new StringBuffer();
    Edge<E> sink = end;
    int i = 0;
    // break the loop when you can successfully traverse from the sink to the source or you exceed
    // the number of edges assembled
    while (learnedPath.get(end) != start && i++ < length) {
      sb.append(learnedPath.get(end));
      end = learnedPath.get(end);
    }

    if (learnedPath.get(end) == start && learnedPath.get(start) != end) {
      setIsPathValid(true);

      // we have the order in which vertices need to be visited from the sink to source,so we
      // reverse them
      sb.reverse();

      // adding start and end vertices for the sake of intuitiveness & completeness
      sb.insert(0, start);
      sb.append(sink);
      return sb.toString();
    } else {
      sb = new StringBuffer();
      return sb.append("Sink Unreachable").toString();
    }

  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public Graph<E> getGraph() {
    return graph;
  }

  public void setGraph(Graph<E> graph) {
    this.graph = graph;
  }

  public PriorityQueue<Link<E>> getHeap() {
    return heap;
  }

  public void setHeap(PriorityQueue<Link<E>> heap) {
    this.heap = heap;
  }

  public Map<Edge<E>, Edge<E>> getLearnedPath() {
    return learnedPath;
  }


  public Boolean getIsPathValid() {
    return isPathValid;
  }

  public void setIsPathValid(Boolean isPathValid) {
    this.isPathValid = isPathValid;
  }

}
