package greedy;

import graph.Edge;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import assembly.Graph;

public class TestShortestPath {

	Edge<Integer> i, j, k, l, m, n, x;
	Graph<Integer> list;
	Dijkstra<Integer> test;

	@Before
	public void assembleGraph() {
		list = new Graph<Integer>();
		i = new Edge<Integer>(1, 0);
		j = new Edge<Integer>(2, 0);
		k = new Edge<Integer>(3, 0);
		l = new Edge<Integer>(4, 0);
		m = new Edge<Integer>(5, 0);
		n = new Edge<Integer>(6, 0);
		x = new Edge<Integer>(7, 0);

		list.addEdge(i);
		list.addEdge(j);
		list.addEdge(k);
		list.addEdge(l);
		list.addEdge(m);
		list.addEdge(n);
		list.addEdge(x);

		i.connectTo(j, 7);
		i.connectTo(k, 9);
		i.connectTo(n, 14);
		i.connectTo(x, 200);

		k.connectTo(j, 11);
		k.connectTo(n, 2);
		k.connectTo(l, 11);

		j.connectTo(k, 10);
		j.connectTo(l, 15);

		l.connectTo(j, 11);
		l.connectTo(m, 6);

		n.connectTo(m, 9);

		m.connectTo(i, 9);

		test = new Dijkstra<Integer>(list);
	}

	@Test
	public void testDistance() {
		Assert.assertEquals(20, test.heapPath(i, l));
	}

	@Test
	public void testIncorrectDistance() {
		Assert.assertNotEquals(22, test.heapPath(i, l));
	}

	@Test
	public void testPath() {
		Assert.assertEquals("134", test.getPath(i, l));
	}

	@Test
	public void testUnreachablePath() {
		Assert.assertEquals("Sink Unreachable", test.getPath(m, l).trim());
	}

}
