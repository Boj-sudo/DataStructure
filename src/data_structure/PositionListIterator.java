package data_structure;
import java.util.Iterator;
import Interfaces.IList;

public class PositionListIterator<T> implements Iterator<T> {

	IList<T> list;
	Position<T> cursor;
	
	public PositionListIterator(PositionList<T> _list) {
		this.list = _list;
		
		if(!_list.isEmpty()) {
			this.cursor = _list.first();
		}
	}
	
	@Override
	public boolean hasNext() {
		return !list.isEmpty() && list.next(cursor) != null;
	}
	
	@Override 
	public T next() {
		T element = cursor.element();
		cursor = list.next(cursor);
		
		return element;
	}
	
	@Override
	public void remove() {
		// Do nothing to keep java happy.
	}
}
