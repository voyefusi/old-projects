package tests;
import graphs.Graph;
import graphs.PrintCallBack;

import java.util.*;
import junit.framework.TestCase;

public class PublicTests extends TestCase {
	
	public void testBFSGoodFaithAttempt() {
		Graph<Double> graph = createGraph();
		StringBuffer results = new StringBuffer();
		PrintCallBack<Double> printCallBack = new PrintCallBack<Double>();
		graph.doBreadthFirstSearch("ST", printCallBack);
		results.append(printCallBack.getResult());
		
		assertTrue(TestingSupport.correctResults("pubTestBFSGoodFaithAttempt.txt", results.toString()));
	}
	
	public void testDFS() {
		Graph<Double> graph = createGraph();
		StringBuffer results = new StringBuffer();
		PrintCallBack<Double> printCallBack = new PrintCallBack<Double>();
		graph.doDepthFirstSearch("ST", printCallBack);
		results.append(printCallBack.getResult());
		assertTrue(TestingSupport.correctResults("pubTestDFS.txt", results.toString()));
	}
	
	public void testDijkstras() {
		Graph<Double> graph = createGraph();
		StringBuffer results = new StringBuffer();
		
		ArrayList<String> shortestPath = new ArrayList<String>();
		
		Set<String> vertices = graph.getVertices();
		TreeSet<String> sortedVertices = new TreeSet<String>(vertices);
		for (String endVertex : sortedVertices) {
			int shortestPathCost = graph.doDijkstras("ST", endVertex, shortestPath);
			results.append("Shortest path cost between " + "ST " + endVertex + ": " + shortestPathCost);
			results.append(", Path: " + shortestPath + "\n");
			shortestPath.clear();
		}
				assertTrue(TestingSupport.correctResults("pubTestDijkstras.txt", results.toString()));
	}

	/* Graph where the data of each vertex is a Double value */
	private Graph<Double> createGraph() {
		Graph<Double> graph = new Graph<Double>();
		
		/* Adding vertices to the graph */
		String[] vertices = new String[]{"ST","A", "B", "C", "D"};
		for (int i=0; i < vertices.length; i++)
			graph.addVertex(vertices[i], new Double(i + 1000.50));
		
		/* Adding directed edges */
		graph.addDirectedEdge("ST", "A", 11);  
		graph.addDirectedEdge("ST", "B", 6);
		graph.addDirectedEdge("A", "C", 2);
		graph.addDirectedEdge("B", "A", 4);
		graph.addDirectedEdge("B", "D", 3);
		graph.addDirectedEdge("C", "D", 5);
		graph.addDirectedEdge("D", "C", 7);
		
		return graph;
	}
}