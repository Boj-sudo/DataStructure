package data_structure;
import java.util.ArrayList;

public class ProbeHashMap<K, V> extends AbstractHashMap<K, V> {

	private MapEntry<K, V>[] table;
	private MapEntry<K, V> DEACTIVATE = new MapEntry<>(null, null);
	
	public ProbeHashMap() {
		super();
	}
	
	public ProbeHashMap(int capacity) {
		super(capacity);
	}
	
	public ProbeHashMap(int capacity, int prime) {
		super(capacity, prime);
	}
	
	@SuppressWarnings("unchecked")
	protected void createTable() {
		table = (MapEntry<K, V>[]) new MapEntry[num_capacity];
	}
	
	private boolean isAvailable(int i) {
		return (table[i] == null || table[i] == DEACTIVATE);
	}
	
	private int findSlot(int h, K k) {
		int available = -1;
		int i = h;
		
		do {
			if(isAvailable(i)) {
				if(available == -1) {
					available = i;
				}
				
				if(table[i] == null) {
					break;
				} else if (table[i].getKey().equals(k)) {
					return i;
				}
				
				i = (i + 1) % num_capacity;
			}
		} while(i != h);
			
		return -(available + 1);
	}
	
	protected V getBucket(int h, K k) {
		int i = findSlot(h, k);
		
		if(i < 0) {
			return null;
		}
		
		return table[i].getValue();
	}
	
	protected V putBucket(int h, K k, V v) {
		int i = findSlot(h, k);
		
		if(i >= 0) {
			return table[i].setValue(v);
		}
		
		table[-(i + 1)] = new MapEntry<>(k, v);
		num_entries ++;
		
		return null;
	}
	
	protected V removeBucket(int h, K k) {
		int i = findSlot(h, k);
		
		if(i < 0) {
			return null;
		}
		
		V answer = table[i].getValue();
		table[i] = DEACTIVATE;
		num_entries --;
		
		return answer;
	}
	
	public Iterable<MapEntry<K, V>> entries() {
		ArrayList<MapEntry<K, V>> array_list = new ArrayList<>();
		
		for(int h = 0; h < num_capacity; h++) {
			if(!isAvailable(h)) {
				array_list.add(table[h]);
			}
		}
		
		return array_list;
	}
}
