package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;

public class Graph<E> {
	HashMap<String,HashMap<String,Integer>> adjacencyMap; //represents the adjacency properties of the graph. A vertex is identified by using a String object. We will assume the edge costs are integer values.
	//First String is FromNode, second String is ToNode, which is mapped to the cost of getting to it
	HashMap<String, E> dataMap;// represents the data associated with the vertex. A vertex is identified by using a String object.
	//vertex = node. E = cost
	PrintCallBack<E> vertexInfo;
	static final int infinity = 1000;

	public Graph(){
		adjacencyMap = new HashMap<String,HashMap<String,Integer>>();
		dataMap = new HashMap<String, E>();	
		vertexInfo = new PrintCallBack<E>();
	}

	public void addDirectedEdge(String startVertexName, String endVertexName, int cost){
		//		Adds a directed edge with the specified cost between the designated vertices only if the vertices already exist in the graph.
		HashMap<String, Integer> that;
		if((dataMap.containsKey(startVertexName) && dataMap.containsKey(endVertexName))){
			if(adjacencyMap.get(startVertexName) == null){
				that = new HashMap<String,Integer>();
			}else{
				that = adjacencyMap.get(startVertexName);
			}

			if(that.get(endVertexName) == null){
				that.put(endVertexName, cost);
			}else{
				throw new IllegalArgumentException("Start vertex not in graph");
			}
		}else if(!dataMap.containsKey(startVertexName)){
			throw new IllegalArgumentException("Start vertex not in graph");
		}else{
			throw new IllegalArgumentException("End vertex not in graph");
		}
	}



	public void addVertex(String vertexName, E data){
		//Creates a vertex for the specified parameters and adds it to the graph.
		dataMap.put(vertexName, data);
		adjacencyMap.put(vertexName, new HashMap<String, Integer>());
	}

	public void doBreadthFirstSearch(String startVertexName, CallBack<E> callback){
		//Computes Breadth-First Search of the specified graph.
		Set<String> container = new HashSet<String>();
		Queue<String> queue = new PriorityQueue<String>();
		queue.add(startVertexName);	
	
			while(!queue.isEmpty()){
			String curr = queue.poll();
			if (!container.contains(curr)){
				callback.processVertex(curr, dataMap.get(curr));
				container.add(curr);

				TreeMap<String, Integer> allVertices = new TreeMap<String, Integer>(getAdjacentVertices(curr));
				Iterator<String> it = allVertices.keySet().iterator();
				while(it.hasNext()){
					String next = it.next();
					if(!(container.contains(next))){
						queue.add(next);
					}
				
				}

			}

		}


	}
	public void doDepthFirstSearch(String startVertexName, CallBack<E> callback){
		//Computes Depth-First Search of the specified graph.
		//Everyhting that has queue change to Stack<E>.class push for adding to top 


		Set<String> container = new HashSet<String>();
		Stack<String> queue = new Stack<String>();

		queue.add(startVertexName);	
		//		Set<String> allVertices = dataMap.keySet();
		//		Iterator it = allVertices.iterator();

		while(!queue.isEmpty()){
			String curr = queue.pop();
			if (!container.contains(curr)){
				callback.processVertex(curr, dataMap.get(curr));
				container.add(curr);

				TreeMap<String, Integer> allVertices = new TreeMap<String, Integer>(getAdjacentVertices(curr));
				Iterator<String> it = allVertices.keySet().iterator();
				while(it.hasNext()){
					String next = it.next();
					if(!(container.contains(next))){
						queue.push(next);
					}
				}

			}

		}
	
	}
	public int doDijkstras(String startVertexName, String endVertexName, ArrayList<String> shortestPath){
		//Computes the shortest path and shortest path cost using Dijkstras's algorithm.
		/**
		 *  S = @//
		 *  P[ ] = none for all nodes//
		 	C[start] = 0, C[ ] = % for all other nodes//
			while ( not all nodes in S )//
			find node K not in S with smallest C[K]
			add K to S
			for each node J not in S adjacent to K
			if ( C[K] + cost of (K,J) < C[J] )
			C[J] = C[K] + cost of (K,J)
			P[J] = K
		 */
		Integer min = 9999;
		String minKey = "";
		String next = "";
		
		HashMap<String, Integer> c = new HashMap<String, Integer>();
		HashMap<String, String> p = new HashMap<String, String>();
		
		Set<String> allVertices = getVertices();
		Iterator<String> it = allVertices.iterator();
	//	Iterator<String> it2 = allVertices.iterator();
		while(it.hasNext()){
			next = it.next();
			if(next == startVertexName){//startVertex gets mapped to 0
				c.put(next, 0);
			}else{
				c.put(next, infinity);//all other vertices mapped to 1000 which represents infinity
			}
			p.put(next, "");
		}

		Queue<String> s = new PriorityQueue<String>();
		
		while(!s.containsAll(allVertices)){
			//find node K not in S with smallest C[K]
			for(String curr:c.keySet()){
				if(s.contains(curr)){
					
				}else{
					if(min > c.get(curr)){
						min = c.get(curr);
						minKey = curr;
					}
				}
			}
			
			s.add(minKey);
			min = 9999;
			for(String curr: getAdjacentVertices(minKey).keySet()){// for each node J not in S adjacent to K
				//if ( C[K] + cost of (K,J) < C[J] )
				if((c.get(minKey) + getAdjacentCost(minKey, curr) < c.get(curr))){
					c.put(curr, c.get(minKey) + getAdjacentCost(minKey, curr));
					p.put(curr, minKey);
				}
			}
		}
		//get shortest path to EndVertex and Update ArrayList
		Stack<String> path = new Stack<String>();
		String curr = endVertexName;
		while(!path.contains(startVertexName)){
			path.push(curr);
			curr = p.get(curr);
		}
		while(!path.isEmpty()){
			shortestPath.add(path.pop());
		}
		//get Cost of Shortest Path
		return c.get(endVertexName);
	}
	public int getAdjacentCost(String startVertexName, String endVertexName){
		return adjacencyMap.get(startVertexName).get(endVertexName);

	}
	public Map<String, Integer> getAdjacentVertices(String vertexName){
		//Returns a map with information about vertices adjacents to vertexName
		return adjacencyMap.get(vertexName);
	}
	public E getData(String vertex){
		if(dataMap.containsKey(vertex)){
			return dataMap.get(vertex);
		}else{
			return null;
		}			
	}
	public Set<String> getVertices(){
		//Returns a Set with all the graph vertices.
		return dataMap.keySet();

	}

}
