package elec_circuit;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;
import data_structure.AdjacencyMapGraph;
import data_structure.HashTable;
import data_structure.PositionList;
import data_structure.ProbeHashMap;
import Interfaces.IEdge;
import Interfaces.IGraph;
import Interfaces.IMap;
import Interfaces.IVertex;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

public class Circuit {
	
	// Attributes
	public Resistor[] resistors;
	private Current[] current;
	public NodalPath nodal_path[];
	private AdjacencyMapGraph<Resistor, NodalPath> graph;
	private Controller components;
		
	Hashtable<Resistor, Boolean> visited = new Hashtable<>();
	HashTable<Resistor, IVertex<Resistor>> vertices = new HashTable<>(2);
		
	public Circuit() {
		// Initialize
		components.initializeVars();
		
		// Resistors name and values
		resistors = new Resistor[10];
		resistors[0] = new Resistor("R0", components.resistor_0, 14.3);
		resistors[1] = new Resistor("R1", components.resistor_1, 22.6);
		resistors[2] = new Resistor("R2", components.resistor_2, 3);
		resistors[3] = new Resistor("R3", components.resistor_3, 30.9);
		resistors[4] = new Resistor("R4", components.resistor_4, 11.7);
		resistors[5] = new Resistor("R5", components.resistor_5, 12.3);
		resistors[6] = new Resistor("R6", components.resistor_6, 9.2);
		resistors[7] = new Resistor("R7", components.resistor_7, 4.5);
		resistors[8] = new Resistor("R8", components.resistor_8, 7.4);
		resistors[9] = new Resistor("R9", components.resistor_9, 1.5);
		
		// NodalPaths to resistors
		nodal_path = new NodalPath[15];
		nodal_path[0] = new NodalPath(resistors[0].getName() + " to " + resistors[1].getName());
		nodal_path[1] = new NodalPath(resistors[1].getName() + " to " + resistors[8].getName());
		nodal_path[2] = new NodalPath(resistors[7].getName() + " to " + resistors[1].getName());
		nodal_path[3] = new NodalPath(resistors[9].getName() + " to " + resistors[5].getName());
		nodal_path[4] = new NodalPath(resistors[3].getName() + " to " + resistors[0].getName());
		nodal_path[5] = new NodalPath(resistors[2].getName() + " to " + resistors[4].getName());
		nodal_path[6] = new NodalPath(resistors[7].getName() + " to " + resistors[3].getName());
		nodal_path[7] = new NodalPath(resistors[6].getName() + " to " + resistors[9].getName());
		nodal_path[8] = new NodalPath(resistors[5].getName() + " to " + resistors[3].getName());
		nodal_path[9] = new NodalPath(resistors[8].getName() + " to " + resistors[2].getName());
		nodal_path[10] = new NodalPath(resistors[0].getName() + " to " + resistors[8].getName());
		nodal_path[11] = new NodalPath(resistors[4].getName() + " to " + resistors[5].getName());
		nodal_path[12] = new NodalPath(resistors[7].getName() + " to " + resistors[3].getName());
		nodal_path[13] = new NodalPath(resistors[2].getName() + " to " + resistors[9].getName());
		nodal_path[14] = new NodalPath(resistors[4].getName() + " to " + resistors[6].getName());
		
		// Current Flow
		current = new Current[8];
		current[0] = new Current("C1", 1, 15.3);
		current[1] = new Current("C2", 2, 23.2);
		current[2] = new Current("C3", 3, 34.4);
		current[3] = new Current("C4", 4, 11.5);
		current[4] = new Current("C5", 5, 1.3);
		current[5] = new Current("C6", 6, 5.23);
		current[6] = new Current("C7", 7, 9.7);
		current[7] = new Current("C8", 8, 7.7);
		
		// Call Graph for adjacency - instantiate
		graph = new AdjacencyMapGraph<>(true);
		formation();
	}
	
	// Print Depth for search
	public void printDepthForSearch(Resistor vertices) {
		IVertex<Resistor> vertex = getVertex(vertices);
		visited = new Hashtable<>();
	}
	
	private IVertex<Resistor> getVertex(Resistor vertices) {
		// Go through vertex list to find vertex
		for(IVertex<Resistor> ret : graph.vertices()) {
			if(ret.getElement() == vertices) {
				return ret;
			}
		}
		
		return null;
	}
	
	void print() {
		System.out.println("Vertices: " + graph.numVertices() + " \nEdges: " + graph.numEdges());
		
		// Resistor for vertices
		for(IVertex<Resistor> res : graph.vertices()) {
			System.out.println(res.getElement());
		}
		
		// NodalPath for Edges
		for(IEdge<NodalPath> nodal : graph.edges()) {
			System.out.println(nodal.getElement());
		}
	}
	
	public void assignEdge(Resistor init_resistor, Resistor dest_resistor, NodalPath current_path) {
		IVertex<Resistor> vertex = vertices.get(init_resistor);
		
		if(vertex == null) {
			// Source vertex not in graph -- insert
			vertex = graph.insertVertex(init_resistor);
			vertices.put(init_resistor, vertex);
		}
		
		IVertex<Resistor> vertex_1 = vertices.get(dest_resistor);
		
		if(vertex_1 == null) {
			vertex_1 = graph.insertVertex(dest_resistor);
			vertices.put(dest_resistor, vertex_1);
		}
		
		// Check if edge is already in graph
		if(graph.getEdge(vertex, vertex_1) == null) {
			// Edge not in the graph thus insert
			graph.insertEdge(vertex, vertex_1, current_path);
		}
	}
	
	private void formation() {
		assignEdge(resistors[0], resistors[1], nodal_path[0]);
		assignEdge(resistors[1], resistors[8], nodal_path[1]);
		assignEdge(resistors[7], resistors[1], nodal_path[2]);
		assignEdge(resistors[9], resistors[5], nodal_path[3]);
		assignEdge(resistors[3], resistors[0], nodal_path[4]);
		assignEdge(resistors[2], resistors[4], nodal_path[5]);
		assignEdge(resistors[7], resistors[3], nodal_path[6]);
		assignEdge(resistors[6], resistors[9], nodal_path[7]);
		assignEdge(resistors[5], resistors[3], nodal_path[8]);
		assignEdge(resistors[8], resistors[2], nodal_path[9]);
		assignEdge(resistors[0], resistors[8], nodal_path[10]);
		assignEdge(resistors[4], resistors[5], nodal_path[11]);
		assignEdge(resistors[7], resistors[3], nodal_path[12]);
		assignEdge(resistors[2], resistors[9], nodal_path[13]);
		assignEdge(resistors[4], resistors[6], nodal_path[14]);
	}
	
	private void startVisit(IVertex<Resistor> visit) {
		System.out.println(" : " + visit.getElement().getName());
	}
	
	private void finishVisit(IVertex<Resistor> visit) {
		System.out.println(" : " + visit.getElement().getName());
	}
	
	private void DepthForSearch(IGraph<Resistor, NodalPath> graph, IVertex<Resistor> vertex) {
		if(!visited.containsKey(vertex.getElement())) {
			visited.put(vertex.getElement(), true);
			startVisit(vertex);
			
			for(IEdge<NodalPath> edge: graph.outgoingEdges(vertex)) {
				IVertex<Resistor> opposite = graph.opposite(vertex, edge);
				
				if(!visited.containsKey(opposite.getElement())) {
					DepthForSearch(graph, opposite);
				}
			}
			
			finishVisit(vertex);
		}
	}
	
	public static <V, E> void DepthForSearch(IGraph<V, E> g, IVertex<V> u, Set<IVertex<V>> known, IMap<IVertex<V>, IEdge<E>> forest) {
		known.add(u);
		
		for(IEdge<E> edge : g.outgoingEdges(u)) {
			// For every outgoing edge from u
			IVertex<V> vertex = g.opposite(u,  edge);
			if(!known.contains(vertex)) {
				forest.put(vertex, edge);
				DepthForSearch(g, vertex, known, forest); // Recursively explore from vertex
			}
		}
	}
	
	public static <V, E> PositionList<IEdge<E>> buildPath(IGraph<V, E> g, IVertex<V> u, IVertex<V> v, IMap<IVertex<V>, IEdge<E>> forest){
		PositionList<IEdge<E>> path = new PositionList<>();
		
		if(forest.get(v) != null) {
			IVertex<V> vertex_walk = v;
			
			while(vertex_walk != u) {
				IEdge<E> edge = forest.get(vertex_walk);
				path.addFirst(edge);
				vertex_walk = g.opposite(vertex_walk, edge);
			}
		}
		
		return path;
	}
	
	public static <V, E> IMap<IVertex<V>, IEdge<E>> DFSComplete(IGraph<V, E> graph){
		Set<IVertex<V>> known = new HashSet<>();
		IMap<IVertex<V>, IEdge<E>> forest = new ProbeHashMap<>();
		
		for(IVertex<V> u : graph.vertices()) 
			if(!known.contains(u))
				DepthForSearch(graph, u, known, forest);
		
		return forest;
	}
		
	public Current[] getCurrent() {
		return current;
	}
}
