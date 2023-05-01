package data_structure;

/**
 * A node in the position list
 * @author TMaboa
 *@param <T> object type for this node
 */
public class Node<T> implements Position<T> {
	T element;
	Node<T> next;
	Node<T> prev;
	
	public Node(Node<T> _next, Node<T> _prev, T _element) {
		this.element = _element;
		this.next = _next;
		this.prev = _prev;
	}
	
	public T getElement() {
		return element;
	}
	
	public Node<T> getNext() {
		return next;
	}
	
	public Node<T> getPrev() {
		return prev;
	}
	
	public void setElement(T _element) {
		this.element = _element;
	}
	
	public void setNext(Node<T> _next) {
		this.next = _next;
	}
	
	public void setPrev(Node<T> _prev) {
		this.prev = _prev;
	}
	
	public String toString() {
		if(element == null) {
			return "<>";
		}
		
		return "<" + element.toString() + ">";
	}
	
	@Override
	public T element() {
		return element;
	}
}
