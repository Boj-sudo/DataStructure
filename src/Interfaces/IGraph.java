package Interfaces;

public interface IGraph<V, E> {
	int numVertices();
	int numEdges();
	int InDegree(IVertex<V> vertex) throws IllegalArgumentException;
	int outDegree(IVertex<V> vertex) throws IllegalArgumentException;
	
	Iterable<IVertex<V>> vertices();
	Iterable<IEdge<E>> edges();
	Iterable<IEdge<E>> incomingEdges(IVertex<V> vertex) throws IllegalArgumentException;
	Iterable<IEdge<E>> outgoingEdges(IVertex<V> vertex) throws IllegalArgumentException;
	
	IVertex<V>[] endVertices(IEdge<E> edge) throws IllegalArgumentException;
	IVertex<V> opposite(IVertex<V> vertex, IEdge<E> edge) throws IllegalArgumentException;
	IVertex<V> insertVertex(V element);
	void removeVertex(IVertex<V> vertex) throws IllegalArgumentException;
	
	IEdge<E> insertEdge(IVertex<V> u, IVertex<V> v, E element) throws IllegalArgumentException;
	void removeEdge(IEdge<E> edge) throws IllegalArgumentException;
}
