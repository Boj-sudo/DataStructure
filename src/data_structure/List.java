package data_structure;

/**
 * 
 * @author Tsietsi Maboa
 *
 * @param <T>
 */
public interface List<T> {

	T get(Integer i);
	T remove(Integer i);
	void set(Integer i, T e);
	void add(T e);
	Integer size();
	boolean isEmpty();
}
