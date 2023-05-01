package Interfaces;

/**
 * 
 * @author Tsietsi Maboa
 *
 * @param <V>
 * @param <E>
 */
public interface IGraph<V, E> {
	
	/** Returns the number of vertices of the graph */
	int numVertices();
	
	/** Returns the number of edges of the graph */
	int numEdges();
	
	/**
     * Returns the number of edges for which vertex v is the destination.
     * Note that for an undirected graph, this is the same result
     * returned by outDegree
     * @throws IllegalArgumentException if v is not a valid vertex
     */
	int InDegree(IVertex<V> vertex) throws IllegalArgumentException;
	
	/**
     * Returns the number of edges leaving vertex v.
     * Note that for an undirected graph, this is the same result
     * returned by inDegree
     * @throws IllegalArgumentException if v is not a valid vertex
     */
	int outDegree(IVertex<V> vertex) throws IllegalArgumentException;
	
	/** Returns the vertices of the graph as an iterable collection */
	Iterable<IVertex<V>> vertices();
	
	/** Returns the edges of the graph as an iterable collection */
	Iterable<IEdge<E>> edges();
	
	/**
     * Returns an iterable collection of edges for which vertex v is the destination.
     * Note that for an undirected graph, this is the same result
     * returned by outgoingEdges.
     * @throws IllegalArgumentException if v is not a valid vertex
     */
	Iterable<IEdge<E>> incomingEdges(IVertex<V> vertex) throws IllegalArgumentException;
	
	/**
     * Returns an iterable collection of edges for which vertex v is the origin.
     * Note that for an undirected graph, this is the same result
     * returned by incomingEdges.
     * @throws IllegalArgumentException if v is not a valid vertex
     */
	Iterable<IEdge<E>> outgoingEdges(IVertex<V> vertex) throws IllegalArgumentException;
	
	/** Returns the edge from u to v, or null if they are not adjacent. */
    IEdge<E> getEdge(IVertex<V> u, IVertex<V> v) throws IllegalArgumentException;
    
    /**
     * Returns the vertices of edge e as an array of length two.
     * If the graph is directed, the first vertex is the origin, and
     * the second is the destination.  If the graph is undirected, the
     * order is arbitrary.
     */
	IVertex<V>[] endVertices(IEdge<E> edge) throws IllegalArgumentException;
	
	/** Returns the vertex that is opposite vertex v on edge e. */
	IVertex<V> opposite(IVertex<V> vertex, IEdge<E> edge) throws IllegalArgumentException;
	
	/** Inserts and returns a new vertex with the given element. */
	IVertex<V> insertVertex(V element);
	
	 /** Removes a vertex and all its incident edges from the graph. */
	void removeVertex(IVertex<V> vertex) throws IllegalArgumentException;
	
	/**
     * Inserts and returns a new edge between vertices u and v, storing given element.
     *
     * @throws IllegalArgumentException if u or v are invalid vertices, or if an edge already exists between u and v.
     */
	IEdge<E> insertEdge(IVertex<V> u, IVertex<V> v, E element) throws IllegalArgumentException;
	
	/** Removes an edge from the graph. */
	void removeEdge(IEdge<E> edge) throws IllegalArgumentException;
}
