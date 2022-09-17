package graphs;

import java.util.*;

/**
 * Implements a graph. We use two maps: one map for adjacency properties
 * (adjancencyMap) and one map (dataMap) to keep track of the data associated
 * with a vertex.
 * 
 * @author cmsc132
 * 
 * @param <E>
 */
public class Graph<E> {
	/* You must use the following maps in your implementation */
	private HashMap<String, HashMap<String, Integer>> adjacencyMap;
	private HashMap<String, E> dataMap;

	public Graph() {
		this.adjacencyMap = new HashMap<>();
		this.dataMap = new HashMap<>();
	}

	public void addVertex(String vertexName, E data) {
		if (dataMap.containsKey(vertexName)) {
			throw new IllegalArgumentException(
					"The vertex already exists in the graph");
		}

		dataMap.put(vertexName, data);
		adjacencyMap.put(vertexName, new HashMap<String, Integer>());
	}

	public void addDirectedEdge(String startVertexName, String endVertexName,
			int cost) {
		if (!dataMap.containsKey(startVertexName)
				|| !dataMap.containsKey(endVertexName)) {
			throw new IllegalArgumentException();
		}

		adjacencyMap.get(startVertexName).put(endVertexName, cost);
	}

	public String toString() {
		TreeSet<String> vertexNames = new TreeSet<String>();
		TreeSet<String> adjNames = new TreeSet<String>();
		vertexNames.addAll(dataMap.keySet());
		adjNames.addAll(adjacencyMap.keySet());

		String result = "Vertices: " + vertexNames + "\n";

		result += "Edges:" + "\n";
		for (String element : adjNames) {
			result += "Vertex(" + element + ")--->"
					+ this.adjacencyMap.get(element) + "\n";
		}

		return result;
	}

	public Map<String, Integer> getAdjacentVertices(String vertexName) {
		return adjacencyMap.get(vertexName);
	}

	public int getCost(String startVertexName, String endVertexName) {
		if (!dataMap.containsKey(startVertexName)
				|| !dataMap.containsKey(endVertexName)) {
			throw new IllegalArgumentException();
		}

		return adjacencyMap.get(startVertexName).get(endVertexName);
	}

	public Set<String> getVertices() {
		return dataMap.keySet();
	}

	public E getData(String vertex) {
		return dataMap.get(vertex);
	}

	public void doDepthFirstSearch(String startVertexName,
			CallBack<E> callback) {
		if (!dataMap.containsKey(startVertexName)) {
			throw new IllegalArgumentException();
		}
		HashSet<String> visited = new HashSet<String>();
		Stack<String> discovered = new Stack<String>();
		discovered.push(startVertexName);
		while (!discovered.isEmpty()) {
			String X = discovered.pop();
			if (!visited.contains(X)) {
				visited.add(X);
				callback.processVertex(X, dataMap.get(X));
				for (String element : adjacencyMap.get(X).keySet()) {
					if (!visited.contains(element)) {
						discovered.push(element);
					}
				}
			}

		}
	}

	public void doBreadthFirstSearch(String startVertexName,
			CallBack<E> callback) {
		if (!dataMap.containsKey(startVertexName)) {
			throw new IllegalArgumentException();
		}
		HashSet<String> visited = new HashSet<String>();
		LinkedList<String> discovered = new LinkedList<String>();
		discovered.add(startVertexName);
		while (!discovered.isEmpty()) {
			String X = discovered.poll();
			if (!visited.contains(X)) {
				visited.add(X);
				callback.processVertex(X, dataMap.get(X));
				for (String element : adjacencyMap.get(X).keySet()) {
					if (!visited.contains(element)) {
						discovered.add(element);
					}
				}
			}

		}
	}

	public int doDijkstras(String startVertexName, String endVertexName,
			ArrayList<String> shortestPath) {
		if (!dataMap.containsKey(startVertexName)) {
			throw new IllegalArgumentException();
		}
		HashSet<String> S = new HashSet<String>(); // S is the set of Node visited
		HashMap<String, Integer> costMap = new HashMap<String, Integer>(); // cost from startVertex to key
		HashMap<String, String> pre = new HashMap<String, String>(); // shortest from key to previous
		PriorityQueue<String> q = new PriorityQueue<String>(); // smallest c

		for (String element : dataMap.keySet()) {
			costMap.put(element, Integer.MAX_VALUE);
			pre.put(element, "None");
		}
		costMap.replace(startVertexName, 0);
		q.add(startVertexName);

		while (!S.contains(endVertexName) && q.size() > 0) {
			String minCost = "None";
			minCost = q.poll(); // take smallest from the q
			if (minCost == "None") {
				return -1;
			}
			Map<String, Integer> minCostMap = adjacencyMap.get(minCost);
			for (String element : minCostMap.keySet()) {
				if (!S.contains(element)) {
					int cost = this.getCost(minCost, element);
					int oldC = costMap.get(element);
					int newC = cost + costMap.get(minCost);
					if (oldC > newC) {
						costMap.put(element, newC);
						pre.put(element, minCost);
						if (!q.contains(element)) {
							q.add(element);
						}
					}
				}
			}
		}
		if (costMap.get(endVertexName) == Integer.MAX_VALUE) {
			shortestPath.add("None");
			return -1;
		}

		// put the shortest path in reverse order to path stack
		Stack<String> path = new Stack<>();
		path.push(endVertexName);
		String curr = endVertexName;

		while (!(pre.get(curr) == "None")) {
			curr = pre.get(curr);
			path.push(curr);
		}
		// take the path in the correct order
		while (path.size() > 0) {
			shortestPath.add(path.pop());
		}

		return costMap.get(endVertexName);
	}
}