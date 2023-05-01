package Interfaces;
import data_structure.AbstractMap;

/**
 * 
 * @author Tsietsi Maboa
 *
 * @param <K>
 * @param <V>
 */
public interface IMap<K, V> {
	V get(K key);
	void put(K key, V value);
	V remove(K key);
	int size();
	boolean isEmpty();
	Iterable<K> keys();
	Iterable<V> values();
	Iterable<AbstractMap.MapEntry<K, V>> entries();
}
