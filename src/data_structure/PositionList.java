package data_structure;
import java.util.Iterator;
import Interfaces.IList;
/**
 * The Position List itself, this is just a doubly-linked list that make use of Positions
 * @author TB Maboa
 * @param <T> the type of the objects in the list.
 */
public class PositionList<T> implements IList<T> {
	private Node<T> _header = null;
	private Node<T> _trailer = null;
	private Integer _size = 0;
	/**
	 * Default constructor
	 */
	public PositionList() {
		_trailer = new Node<T>(null, null, null);
		_header = new Node<T>(_trailer, null, null);
		_trailer.setPrev(_header);
		_size = 0;
	}

	@Override
	public Iterator<T> iterator() {
		return new PositionListIterator<T>(this);
	}

	/**
	 * Add an element after a given node in the list
	 */
	@Override
	public Position<T> addAfter(Position<T> position, T item) {
		Node<T> _element = checkPosition(position);
		Node<T> new_node = new Node<T>(null, null, item);
		new_node.setPrev(_element);
		new_node.setNext(_element.getNext());
		_element.getNext().setPrev(new_node);
		_element.setNext(new_node);
		_size ++;
		return new_node;
	}

	/**
	 * Add an element before a given node in a list
	 */
	@Override
	public Position<T> addBefore(Position<T> position, T item) {
		Node<T> _element = checkPosition(position);
		Node<T> new_node = new Node<T>(null, null, item);
		new_node.setPrev(_element.getPrev());
		new_node.setNext(_element);
		_element.getPrev().setNext(new_node);
		_element.setPrev(new_node);
		_size ++;
		return new_node;
	}

	/**
	 * Add an element to the start of the list
	 */
	@Override
	public Position<T> addFirst(T item) {
		return addAfter(_header, item);
	}

	/**
	 * Add an element to the end of the list
	 */
	@Override
	public Position<T> addLast(T item) {
		return addBefore(_trailer, item);
	}

	/**
	 * Remove a specified node from the list. The removed element is returned
	 */
	@Override
	public T remove(Position<T> position) {
		Node<T> _element = checkPosition(position);
		T element = _element.element();
		_element.getPrev().setNext(_element.getNext());
		_element.getNext().setPrev(_element.getPrev());
		_element.setNext(null);
		_element.setPrev(null);
		_size --;
		return element;
	}

	@Override
	public Position<T> search(T element) {
		Node<T> current_node = _header.getNext();
		while(current_node != _trailer) {
			if(current_node.element().equals(element)) {
				return current_node;
			}
			current_node = current_node.getNext();
		}
		return null;
	}

	@Override
	public Position<T> next(Position<T> position) {
		Node<T> _node = checkPosition(position);
		return _node.getNext();
	}

	@Override
	public Position<T> prev(Position<T> position) {
		Node<T> _node = checkPosition(position);
		return _node.getPrev();
	}

	@Override
	public Position<T> first() {
		if(_header.getNext() == _trailer) {
			throw new PositionListException("List is empty");
		}
		return _header.getNext();
	}

	@Override
	public Position<T> last() {
		if(_trailer.getPrev() == _header) {
			throw new PositionListException("List is empty");
		}
		return _trailer.getPrev();
	}

	@Override
	public boolean isEmpty() {
		return (_header.getNext() == _trailer);
	}

	@Override
	public Integer size() {
		return _size;
	}
	
	public Node<T> checkPosition(Position<T> position) {
		if(!(position instanceof Node<?>)) {
			throw new PositionException("Invalid Position");
		}
		Node<T> new_node = (Node<T>) position;
		return new_node;
	}
	
	@Override
	public String toString() {
		String _result = "";
		Node<T> current_node = _header.getNext();
		while(current_node != _trailer) {
			_result += current_node.toString() + " ";
			current_node = current_node.getNext();
		}
		return _result;
	}
}
