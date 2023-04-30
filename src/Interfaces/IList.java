package Interfaces;
import data_structure.Position;

public interface IList<T> extends Iterable<T> {
	Position<T> addAfter(Position<T> position, T item);
	Position<T> addBefore(Position<T> position, T item);
	Position<T> addFirst(T item);
	Position<T> addLast(T item);
	Position<T> search(T position);
	T remove(Position<T> element);
	
	Position<T> next(Position<T> position);
	Position<T> prev(Position<T> position);
	Position<T> first();
	Position<T> last();
	
	boolean isEmpty();
	Integer size();
}
