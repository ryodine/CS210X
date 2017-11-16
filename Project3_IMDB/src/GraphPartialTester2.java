
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.*;
import java.io.*;

/**
 * Code to test Project 3; you should definitely add more tests!
 */
public class GraphPartialTester2 {
	Graph actorsGraph, moviesGraph;
	GraphSearchEngine searchEngine;

	@Test(timeout=5000)
	/**
	 * Verifies that there is no shortest path between a specific and actor and actress.
	 */
	public void findShortestPath () {
		final Node actor1 = actorsGraph.getNodeByName("a");
		final Node actress2 = actorsGraph.getNodeByName("n");
		searchEngine.findShortestPath(actor1, actress2);
		System.out.println(searchEngine.findShortestPath(actor1, actress2));
		final List<Node> shortestPath = searchEngine.findShortestPath(actor1, actress2);
		
		//assertNull(shortestPath);  // there is no path between these people
	}

	@Before
	/**
	 * Instantiates the actors and movies graphs
	 */
	public void setUp () throws IOException {
		//actorsGraph = new IMDBActorsGraph("TestActor2.txt", "testActresses.txt");
		//moviesGraph = new IMDBMoviesGraph("TestActor2.txt", "testActresses.txt");
		actorsGraph = new IMDBActorsGraph("actors_first_10000_lines2.list", "actresses_first_10000_lines2.list");
		moviesGraph = new IMDBMoviesGraph("actors_first_10000_lines2.list", "actresses_first_10000_lines2.list");
		searchEngine = new GraphSearchEngineImpl();

	}
	
	@Test
	/**
	 * Just verifies that the graphs could be instantiated without crashing.
	 */
	public void finishedLoading () {
		assertTrue(true);
		// Yay! We didn't crash
	}

	@Test
	/**
	 * Verifies that a specific movie has been parsed.
	 */
	public void testSpecificMovie () {
		testFindNode(moviesGraph, "(11)");
		assertEquals("(11)", moviesGraph.getNodeByName("(11)").getName());
	}

	@Test
	/**
	 * Verifies that a specific actress has been parsed.
	 */
	public void testSpecificActress () {
		testFindNode(actorsGraph, "c");
		assertEquals("c", actorsGraph.getNodeByName("c").getName());
		
		testFindNode(actorsGraph, "a");
		assertEquals("a", actorsGraph.getNodeByName("a").getName());
		
		testFindNode(actorsGraph, "b");
		assertEquals("b", actorsGraph.getNodeByName("b").getName());
		
		testFindNode(actorsGraph, "d");
		assertEquals("d", actorsGraph.getNodeByName("d").getName());
		
		testFindNode(actorsGraph, "f");
		assertEquals("f", actorsGraph.getNodeByName("f").getName());
		
		testFindNode(actorsGraph, "g");
		assertEquals("g", actorsGraph.getNodeByName("g").getName());
		
		testFindNode(actorsGraph, "h");
		assertEquals("h", actorsGraph.getNodeByName("h").getName());
		
		// This test should fail
		//testFindNode(actorsGraph, "w");
		//assertEquals("w", actorsGraph.getNodeByName("w").getName());
		
		testFindNode(actorsGraph, "a");
		assertEquals("a", actorsGraph.getNodeByName("a").getName());
		
		testFindNode(actorsGraph, "k");
		actorsGraph.getNodeByName("k");
		System.out.println(actorsGraph.getNodeByName("k").getName());
		assertEquals("k", actorsGraph.getNodeByName("k").getName());
		
		testFindNode(actorsGraph, "l");
		actorsGraph.getNodeByName("l");
		System.out.println(actorsGraph.getNodeByName("l").getName());
		assertEquals("l", actorsGraph.getNodeByName("l").getName());
		
		testFindNode(actorsGraph, "v");
		actorsGraph.getNodeByName("v");
		System.out.println(actorsGraph.getNodeByName("v").getName());
		assertEquals("v", actorsGraph.getNodeByName("v").getName());
		
		// This test shows that if the line contains "", then it automatically kick out the whole line -- bug fixed
		//testFindNode(actorsGraph, "\"t\"");
		//actorsGraph.getNodeByName("\"t\"");
		//System.out.println(actorsGraph.getNodeByName("\"t\"").getName());
		//assertEquals("\"t\"", actorsGraph.getNodeByName("\"t\"").getName());
		
		// This test should fail
		//testFindNode(actorsGraph, "why");
		//actorsGraph.getNodeByName("why");
		//System.out.println(actorsGraph.getNodeByName("why").getName());
		//assertEquals("why", actorsGraph.getNodeByName("why").getName());
		
		// this test should fail
		testFindNode(actorsGraph, "haha");
		//actorsGraph.getNodeByName("haha");
		//System.out.println(actorsGraph.getNodeByName("haha").getName());
		//assertEquals("haha", actorsGraph.getNodeByName("haha").getName());
		
		//System.out.println(actorsGraph.getNodeByName("haha").getNeighbors());
	}

	/**
	 * Verifies that the specific graph contains a node with the specified name
	 * @param graph the Graph to search for the node
	 * @param name the name of the Node
	 */
	private static void testFindNode (Graph graph, String name) {
		final Collection<? extends Node> nodes = graph.getNodes();
		boolean found = false;

		for (Node node : nodes) {
			if (node.getName().trim().equals(name)) {
				found = true;
			}
		}
		assertTrue(found);
	}
}
