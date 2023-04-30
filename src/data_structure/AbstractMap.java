package data_structure;
import java.util.Iterator;
import Interfaces.IEntry;
import Interfaces.IMap;

public abstract class AbstractMap<K, V> implements IMap<K, V> {

	public boolean isEmpty() {
		return size() == 0;
	}
	
	public static class MapEntry<K, V> implements IEntry<K, V> {
		private K _key;
		private V _value;
		
		public MapEntry(K key, V value) {
			this._key = key;
			this._value = value;
		}
		
		public K getKey() {
			return _key;
		}
		
		public V getValue() {
			return _value;
		}
		
		protected void setKey(K key) {
			this._key = key;
		}
		
		protected V setValue(V value) {
			V old_value = _value;
			value = _value;
			return old_value;
		}
	}
}
