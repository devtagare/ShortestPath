package greedy;

import graph.Edge;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import assembly.Graph;


public class TestShortestPath {

  Edge<Integer> i, j, k, l, m, n;
  Graph<Integer> list;

  @Before
  public void assembleGraph() {
    list = new Graph<Integer>();
    i = new Edge<Integer>(1, 0);
    j = new Edge<Integer>(2, 0);
    k = new Edge<Integer>(3, 0);
    l = new Edge<Integer>(4, 0);
    m = new Edge<Integer>(5, 0);
    n = new Edge<Integer>(6, 0);

    list.addEdge(i);
    list.addEdge(j);
    list.addEdge(k);
    list.addEdge(l);
    list.addEdge(m);
    list.addEdge(n);

    i.connectTo(j, 7);

    i.connectTo(k, 9);
    i.connectTo(n, 14);

    k.connectTo(j, 11);
    j.connectTo(k, 10);
    j.connectTo(l, 15);
    l.connectTo(j, 11);

    k.connectTo(n, 2);
    k.connectTo(l, 11);

    l.connectTo(m, 6);
    n.connectTo(m, 9);

  }

  @Test
  public void testDistance() {
    Dijkstra<Integer> test = new Dijkstra<Integer>(list);
    Assert.assertEquals(20, test.heapPath(i, l));
  }

  @Test
  public void testIncorrectDistance() {
    Dijkstra<Integer> test = new Dijkstra<Integer>(list);
    Assert.assertNotEquals(22, test.heapPath(i, l));
  }

  @Test
  public void testPath() {
    Dijkstra<Integer> test = new Dijkstra<Integer>(list);
    Assert.assertEquals("134", test.getPath(i, l));
  }

  @Test
  public void testUnreachablePath() {
    Dijkstra<Integer> test = new Dijkstra<Integer>(list);
    Assert.assertEquals("Sink Unreachable", test.getPath(m, i).trim());
  }

}
