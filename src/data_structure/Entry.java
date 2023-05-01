package data_structure;

/**
 * 
 * @author Tsietsi Maboa
 *
 * @param <K>
 * @param <V>
 */
public class Entry<K, V> {

	private K key;
	private V value;
	
	public Entry(K _key, V _value) {
		this.key = _key;
		this.value = _value;
	}
	
	public K getKey() {
		return key;
	}
	
	public V getValue() {
		return value;
	}
	
	public void setKey(K _key) {
		this.key = _key;
	}
	
	public void setValue(V _value) {
		this.value = _value;
	}
	
	public String toString() {
		return key.toString() + "," + value.toString();
	}
}
