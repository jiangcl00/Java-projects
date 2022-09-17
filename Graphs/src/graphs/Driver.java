package graphs;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

import tests.TestingSupport;

public class Driver {
	private static Graph<Double> createGraph() {
		Graph<Double> graph = new Graph<Double>();

		/* Adding vertices to the graph */
		String[] vertices = new String[] { "ST", "A", "B", "C", "D", "M" };
		for (int i = 0; i < vertices.length; i++) {
			graph.addVertex(vertices[i], new Double(i + 1000.50));
		}
			
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
	private static String runDijkstras(Graph<Double> graph, String startVertex) {
		ArrayList<String> shortestPath = new ArrayList<String>();
		StringBuffer results = new StringBuffer();
		
		Set<String> vertices = graph.getVertices();
		TreeSet<String> sortedVertices = new TreeSet<String>(vertices);
		for (String endVertex : sortedVertices) {
			int shortestPathCost = graph.doDijkstras(startVertex, endVertex,
					shortestPath);
			results.append("Shortest path cost between " + startVertex + " " + endVertex
					+ ": " + shortestPathCost);
			results.append(", Path: " + shortestPath + "\n");
			shortestPath.clear();
		}
		
		return results.toString();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Graph<Double> graph = createGraph();
		String startVertex = "ST";
		String answer = runDijkstras(graph, startVertex);
		
		answer += TestingSupport.hardCodingPrevention;
		System.out.print(answer);
	}

}
