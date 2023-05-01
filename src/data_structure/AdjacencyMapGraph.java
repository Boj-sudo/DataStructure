package data_structure;
import Interfaces.IEdge;
import Interfaces.IGraph;
import Interfaces.IMap;
import Interfaces.IVertex;

public class AdjacencyMapGraph<V, E> implements IGraph<V, E> {

	private PositionList<IVertex<V>> vertices = new PositionList<>();
	private PositionList<IEdge<E>> edges = new PositionList<>();
	private boolean isDirected;
	
	public AdjacencyMapGraph(boolean directed) {
		this.isDirected = directed;
	}
	
    public int numVertices() {
    	return vertices.size();
    }
    
    public int numEdges() {
    	return edges.size();
    }
    
    public Iterable<IVertex<V>> vertices() {
    	return vertices;
    }
    
    public Iterable<IEdge<E>> edges() {
    	return edges;
    }

    private Edge<E> validate(IEdge<E> edge) {
    	if(!(edge instanceof Edge)) {
    		throw new IllegalArgumentException("Invalid edge");
    	}
    	Edge<E> _edge = (Edge<E>) edge; 
    	if(!_edge.validate(this)) {
    		throw new IllegalArgumentException("Invalid edge");
    	}
    	return _edge;
    }
    
    private Vertex<V> validate(IVertex<V> vertex) {
    	if(!(vertex instanceof AdjacencyMapGraph.Vertex)) {
    		throw new IllegalArgumentException("Invalid vertex");
    	}
    	Vertex<V> _vertex = (Vertex<V>) vertex;
    	if(!_vertex.validate(this)) {
    		throw new IllegalArgumentException("Invalid vertex");
    	}
    	return _vertex;
    }
    
	public int inDegree(IVertex<V> vertex) throws IllegalArgumentException {
		Vertex<V> _vertex = validate(vertex);
		return _vertex.getIncoming().size();
	}

	public int outDegree(IVertex<V> vertex) throws IllegalArgumentException {
		Vertex<V> _vertex = validate(vertex);
		return _vertex.getOutgoing().size();
	}

	public Iterable<IEdge<E>> incomingEdges(IVertex<V> vertex) throws IllegalArgumentException {
		Vertex<V> _vertex = validate(vertex);
		return _vertex.getIncoming().values();
	}

	public Iterable<IEdge<E>> outgoingEdges(IVertex<V> vertex) throws IllegalArgumentException {
		Vertex<V> _vertex = validate(vertex);
		return _vertex.getOutgoing().values();
	}

	public IVertex<V>[] endVertices(IEdge<E> edge) throws IllegalArgumentException {
		Edge<E> _edge = validate(edge);
		return _edge.getEndpoints();
	}

	public IVertex<V> opposite(IVertex<V> vertex, IEdge<E> edge) throws IllegalArgumentException {
		Edge<E> _edge = validate(edge);
		IVertex<V>[] endpoints = _edge.getEndpoints();
		if(endpoints[0] == vertex) {
			return endpoints[1];
		}else if(endpoints[1] == vertex) {
			return endpoints[0];
		}else {
			throw new IllegalArgumentException("vertex not valid for this edge");
		}
	}

	public void removeVertex(IVertex<V> vertex) throws IllegalArgumentException {
		Vertex<V> _vertex = validate(vertex);
		for(IEdge<E> edge : _vertex.getOutgoing().values()) {
			removeEdge(edge);
		}
		for(IEdge<E> edge : _vertex.getIncoming().values()) {
			removeEdge(edge);
		}
		vertices.remove(_vertex.getPosition());
		_vertex.setPosition(null);
	}

	public IEdge<E> insertEdge(IVertex<V> u, IVertex<V> v, E element) throws IllegalArgumentException {
		if(getEdge(u, v) == null) {
			Edge<E> _edge = new Edge<>(u, v,element);
			edges.addLast(_edge);
			Vertex<V> origin_pos = validate(u);
			Vertex<V> dest_pos= validate(v);
			origin_pos.getOutgoing().put(v, _edge);
			dest_pos.getIncoming().put(u, _edge);
			return (IEdge<E>) _edge;
		}else {
			throw new IllegalArgumentException("Edge exits already");
		}
	}

	public void removeEdge(IEdge<E> edge) throws IllegalArgumentException {
		Edge<E> _edge = validate(edge);
		Vertex<V>[] vertex = (Vertex<V>[]) _edge.getEndpoints();
		vertex[0].getOutgoing().remove(vertex[1]);
		vertex[1].getIncoming().remove(vertex[0]);
		edges.remove(_edge.getPosition());
		_edge.setPosition(null);
	}
	
	public IVertex<V> insertVertex(V element) {
		Vertex<V> vertex = new Vertex<>(element, isDirected);
		vertices.addLast(vertex);
		return vertex;
	}

	public IEdge<E> getEdge(IVertex<V> vertex, IVertex<V> vertex_1) throws IllegalArgumentException {
		Vertex<V> _vertex = validate(vertex);
		return _vertex.getOutgoing().get(vertex_1);
	}
	
	public String toString() {
		StringBuilder str_builder = new StringBuilder();
		for(IVertex<V> _vertex : vertices) {
			str_builder.append("Vertex " + _vertex.getElement() + "\n");
			if(isDirected) {
				str_builder.append(" [Outgoing]");
			}
			str_builder.append(" " + outDegree(_vertex) + " adjacency vertices");
			for(IEdge<E> _edge : outgoingEdges(_vertex)) {
				str_builder.append(String.format(" (%s, %s)", opposite(_vertex, _edge).getElement(), _edge.getElement()));
			}
			str_builder.append("\n");
			if(isDirected) {
				str_builder.append(" [Incoming]");
				str_builder.append(" " + inDegree(_vertex) + " adjacency vertices");
				for(IEdge<E> edge : incomingEdges(_vertex)) {
					str_builder.append(String.format(" (%s, %s)", opposite(_vertex, edge).getElement(), edge.getElement()));
				}
				str_builder.append("\n");
			}
		}
		return str_builder.toString(); 
	}
	
	private class Vertex<V> implements IVertex<V> {
		private V element;
		private IMap<IVertex<V>, IEdge<E>> out_going, in_coming;
		private Position<IVertex<V>> position;
		
		public Vertex(V _element, boolean is_directed) {
			this.element = _element;
			out_going = new ProbeHashMap<>();
			if(is_directed) {
				in_coming = new ProbeHashMap<>();
			}else {
				in_coming = out_going;
			}
		}
		
		public V getElement() {
			return element;
		}
		
		public IMap<IVertex<V>, IEdge<E>> getIncoming() {
			return in_coming;
		}
		
		public IMap<IVertex<V>, IEdge<E>> getOutgoing() {
			return out_going;
		}
		
		public Position<IVertex<V>> getPosition() {
			return position;
		}
		
		public void setPosition(Position<IVertex<V>> pos) {
			this.position = pos;
		}
		
		
		public boolean validate(IGraph<V, E> g) {
			return (AdjacencyMapGraph.this == g && position != null);
		}
	}
	
	private class Edge<E> implements IEdge<E> {
		private E element;
		private Position<IEdge<E>> position;
		private IVertex<V>[] end_points;
		
		public Edge(IVertex<V> u, IVertex<V> v, E _element) {
			this.element = _element;
			end_points = (IVertex<V>[]) new IVertex[] {u, v};
		}
		
		public E getElement() {
			return element;
		}
		
		public Position<IEdge<E>> getPosition() {
			return position;
		}
		
		public IVertex<V>[] getEndpoints() {
			return end_points;
		}
		
		public void setPosition(Position<IEdge<E>> pos) {
			this.position = pos;
		}
		
		public boolean validate(IGraph<V, E> g) {
			return AdjacencyMapGraph.this == g && position != null;
		}
	}

	@Override
	public int InDegree(IVertex<V> vertex) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return 0;
	}
}
