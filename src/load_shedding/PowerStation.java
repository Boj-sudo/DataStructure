package load_shedding;
import java.util.HashSet;
import java.util.Set;
import Interfaces.IEdge;
import Interfaces.IGraph;
import Interfaces.IMap;
import Interfaces.IVertex;
import data_structure.AdjacencyMapGraph;
import data_structure.Graph;
import data_structure.HashTable;
import data_structure.PositionList;
import data_structure.ProbeHashMap;

public class PowerStation {
	
	public Vehicles[] vehicles;
	public Electricity[] units;
	public NodePath nodal_path[];
	private Controller controller;
	private AdjacencyMapGraph<Vehicles, NodePath> graph;
	private Graph<? extends Comparable> _graph;
	
	HashTable<Vehicles, Boolean> visited = new HashTable<>();
	HashTable<Vehicles, IVertex<Vehicles>> vertices = new HashTable<>(2);
	
	public PowerStation() {
		controller.initializeValues();
		
		vehicles = new Vehicles[7];
		vehicles[0] = new Vehicles("Truck_0", controller.vehicle_0, 13.2);
		vehicles[1] = new Vehicles("Truck_1", controller.vehicle_1, 14.3);
		vehicles[2] = new Vehicles("Truck_2", controller.vehicle_2, 10.3);
		vehicles[3] = new Vehicles("Truck_3", controller.vehicle_3, 4);
		vehicles[4] = new Vehicles("Truck_4", controller.vehicle_4, 14);
		vehicles[5] = new Vehicles("Truck_5", controller.vehicle_5, 9.3);
		vehicles[6] = new Vehicles("Truck_6", controller.vehicle_5, 14.3);
		
		nodal_path = new NodePath[10];
		nodal_path[0] = new NodePath(vehicles[0].getName() + " to " + vehicles[1].getName());
		nodal_path[1] = new NodePath(vehicles[1].getName() + " to " + vehicles[6].getName());
		nodal_path[2] = new NodePath(vehicles[4].getName() + " to " + vehicles[5].getName());
		nodal_path[3] = new NodePath(vehicles[7].getName() + " to " + vehicles[4].getName());
		nodal_path[4] = new NodePath(vehicles[8].getName() + " to " + vehicles[3].getName());
		nodal_path[5] = new NodePath(vehicles[5].getName() + " to " + vehicles[8].getName());
		nodal_path[6] = new NodePath(vehicles[2].getName() + " to " + vehicles[6].getName());
		nodal_path[7] = new NodePath(vehicles[1].getName() + " to " + vehicles[9].getName());
		nodal_path[8] = new NodePath(vehicles[7].getName() + " to " + vehicles[1].getName());
		nodal_path[9] = new NodePath(vehicles[6].getName() + " to " + vehicles[5].getName());
		
		units = new Electricity[7];
		units[0] = new Electricity("Units_1", 1, 15);
		units[1] = new Electricity("Units_2", 2, 15);
		units[2] = new Electricity("Units_3", 3, 15);
		units[3] = new Electricity("Units_4", 4, 15);
		units[4] = new Electricity("Units_5", 5, 15);
		units[5] = new Electricity("Units_6", 6, 15);
		
		graph = new AdjacencyMapGraph<>(true);
		_graph = new Graph<>();
		formation();
	}
	
	private void formation() {
		assignEdge(vehicles[0], vehicles[1], nodal_path[0]);
		assignEdge(vehicles[1], vehicles[6], nodal_path[1]);
		assignEdge(vehicles[4], vehicles[5], nodal_path[2]);
		assignEdge(vehicles[7], vehicles[4], nodal_path[3]);
		assignEdge(vehicles[8], vehicles[3], nodal_path[4]);
		assignEdge(vehicles[5], vehicles[8], nodal_path[5]);
		assignEdge(vehicles[2], vehicles[6], nodal_path[6]);
		assignEdge(vehicles[1], vehicles[9], nodal_path[7]);
		assignEdge(vehicles[7], vehicles[1], nodal_path[8]);
		assignEdge(vehicles[6], vehicles[5], nodal_path[9]);
	}
	
	public void assignEdge(Vehicles init_truck, Vehicles dest_truck, NodePath current_path) {
		IVertex<Vehicles> vertex = vertices.get(init_truck);
		IVertex<Vehicles> vertex_1 = vertices.get(dest_truck);
		
		if(vertex == null) {
			vertex = graph.insertVertex(init_truck);
			vertices.put(init_truck, vertex);
		}
		if(vertex_1 == null) {
			vertex_1 = graph.insertVertex(dest_truck);
			vertices.put(dest_truck, vertex_1);
		}
		if(graph.getEdge(vertex, vertex_1) == null) {
			graph.insertEdge(vertex, vertex_1, current_path);
		}
	}
	
	private IVertex<Vehicles> getVertex(Vehicles vertices){
		for(IVertex<Vehicles> vertex : graph.vertices()) {
			if(vertex.getElement() == vertices) {
				return vertex;
			}
		}
		return null;
	}
	
	private void startVisit(IVertex<Vehicles> visit) {
		System.out.println(" : " + visit.getElement().getName());
	}
	
	private void finishVisit(IVertex<Vehicles> visit) {
		System.out.println(visit.getElement().getName());
	}
	
	public static <V, E> void depthFS(IGraph<V, E> graph, IVertex<V> u,Set<IVertex<V>> known, IMap<IVertex<V>, IEdge<E>> forest) {
		known.add(u);
		for(IEdge<E> edge : graph.outgoingEdges(u)) {
			IVertex<V> vertex = graph.opposite(u, edge);
			if(!known.contains(vertex)) {
				forest.put(vertex, edge);
				depthFS(graph, vertex, known, forest);
			}
		}
	}
	
	private void depthFSearch(IGraph<Vehicles, NodePath> g, IVertex<Vehicles> v) {
		if(!visited.containsKey(v.getElement())) {
			visited.put(v.getElement(), true);
			startVisit(v);
			for(IEdge<NodePath> edge : g.outgoingEdges(v)) {
				IVertex<Vehicles> _opposite = g.opposite(v, edge);
				if(!visited.containsKey(_opposite.getElement())) {
					depthFSearch(g, _opposite);
				}
			}
			finishVisit(v);
		}
	}
	
	public static <V, E> IMap<IVertex<V>, IEdge<E>> DFSComplete(IGraph<V, E> graph) {
		Set<IVertex<V>> known = new HashSet<>();
		IMap<IVertex<V>, IEdge<E>> forest = new ProbeHashMap<>();
		for(IVertex<V> vertex : graph.vertices()) {
			if(!known.contains(vertex)) {
				depthFS(graph, vertex,known, forest);
			}
		}
		return forest;
	}
	
	public static <V, E> PositionList<IEdge<E>> createPath(IGraph<V, E> g, IVertex<V> u, IVertex<V> v, IMap<IVertex<V>, IEdge<E>> forest) {
		PositionList<IEdge<E>> path = new PositionList<>();
		if(forest.get(v) != null) {
			IVertex<V> vertex_roam = v;
			while(vertex_roam != u) {
				IEdge<E> edge = forest.get(vertex_roam);
				path.addFirst(edge);
				vertex_roam = g.opposite(vertex_roam, edge);
			}
		}
		return path;
	}
	
	public void printDFS(Vehicles vertices) {
		IVertex<Vehicles> vertex = getVertex(vertices);
		visited = new HashTable<>();
	}
	
	void print() {
		System.out.println("Vertices: " + graph.numVertices() + " \nEdges: " + graph.numEdges());
		for(IVertex<Vehicles> vertex : graph.vertices()) {
			System.out.println(vertex.getElement());
		}
		for(IEdge<NodePath> nodal : graph.edges()) {
			System.out.println(nodal.getElement());
		}
	}
	
	public Electricity[] getElectricity() {
		return units;
	}

}
