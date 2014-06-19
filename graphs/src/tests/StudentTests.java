package tests;
import graphs.CallBack;
import graphs.Graph;
import graphs.PrintCallBack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;

import junit.framework.TestCase;

/**
 * Please put your own test cases into this file.
*/

public class StudentTests extends TestCase {

	public void testDoBreadthFirstSearch(){
		Graph<Double> myGraph = createGraph();
		StringBuffer answer = new StringBuffer();
		PrintCallBack<Double> cb = new PrintCallBack<Double>();
		myGraph.doBreadthFirstSearch("ST", cb);
		answer.append(cb.getResult());
		assertTrue(TestingSupport.correctResults("pubTestBFSGoodFaithAttempt.txt", answer.toString()));
	}
	
	public void testDoDepthFirstSearch(){
		Graph<Double> graph = createGraph();
		StringBuffer results = new StringBuffer();
		PrintCallBack<Double> printCallBack = new PrintCallBack<Double>();
		graph.doDepthFirstSearch("ST", printCallBack);
		results.append(printCallBack.getResult());
		assertTrue(TestingSupport.correctResults("pubTestDFS.txt", results.toString()));
	}
	
	public void testDoDijkstras(){
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
	
	public void testGetAdjacentCost(){
		Graph<Double> graph = createGraph();
		assertTrue(graph.getAdjacentCost("ST", "B") == 6);
	}
	
	public void testGetAdjacentVertices(){
		Graph<Double> myGraph = createGraph();
		Map<String, Integer> hash = myGraph.getAdjacentVertices("ST");
		assertTrue(hash.keySet().contains("A"));
		assertTrue(hash.keySet().contains("B"));
	}
	
	public void testGetData(){
		Graph<Double> myGraph = createGraph();
		assertTrue(myGraph.getData("ST") == 1000.5);
	}
	
	public void testGetVertices(){
		Graph<Double> myGraph = createGraph();
		assertTrue(myGraph.getVertices().contains("A"));
		assertTrue(myGraph.getVertices().contains("B"));
		assertTrue(myGraph.getVertices().contains("ST"));
		assertTrue(myGraph.getVertices().contains("C"));
		assertTrue(myGraph.getVertices().contains("D"));

	}
	
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
