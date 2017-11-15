import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.*;
import java.io.*;

/**
 * Code to test Project 3; you should definitely add more tests!
 */
public class GraphPartialTester {
	Graph actorsGraph, moviesGraph;
	GraphSearchEngine searchEngine;

	@Test(timeout=5000)
	/**
	 * Verifies that there is no shortest path between a specific and actor and actress.
	 */
	public void findShortestPath () {
		final Node actor1 = actorsGraph.getNodeByName("Actor1");
		final Node actress2 = actorsGraph.getNodeByName("Actress2");
		final List<Node> shortestPath = searchEngine.findShortestPath(actor1, actress2);
		assertNull(shortestPath);  // there is no path between these people
	}

	@Before
	/**
	 * Instantiates the actors and movies graphs
	 */
	public void setUp () throws IOException {
		actorsGraph = new IMDBActorsGraph("TestActor2.txt", "testActresses.txt");
		moviesGraph = new IMDBMoviesGraph("TestActor2.txt", "testActresses.txt");
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
		testFindNode(moviesGraph, "Echoes (2010/III)");
	}

	@Test
	/**
	 * Verifies that a specific actress has been parsed.
	 */
	public void testSpecificActress () {
		testFindNode(actorsGraph, "A, Isabelle");
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
			System.out.println(node.getName().trim());
			if (node.getName().trim().equals(name)) {
				found = true;
			}
		}
		assertTrue(found);
	}
}
