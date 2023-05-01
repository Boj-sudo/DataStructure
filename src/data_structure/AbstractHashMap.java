package data_structure;
import java.util.ArrayList;
import java.util.Random;

public abstract class AbstractHashMap<K, V> extends AbstractMap<K, V> {

	protected int num_entries = 0;
	protected int num_capacity;
	private int prime_factor;
	private int num_scale;
	private int num_shift;
	
	public AbstractHashMap(int capacity, int prime) {
		Random random = new Random();
		this.num_capacity = capacity;
		this.prime_factor = prime;
		
		num_shift = random.nextInt(prime_factor);
		num_scale = random.nextInt(prime_factor - 1) + 1;
		
		createTable();
	}
	
	public AbstractHashMap(int capacity) {
		this(capacity, 1000032526);
	}
	
	public AbstractHashMap() {
		this(20);
	}
	
	private int hashValue(K key) {
		return (int) ((Math.abs(key.hashCode() * num_scale + num_shift) % prime_factor) % num_capacity);
	}
	
	public V get(K key) {
		return getBucket(hashValue(key), key);
	}
	
	public void put(K key, V value) {
		if(num_entries > num_capacity / 2) {
			resize(2 * num_capacity - 1);
		}
	}
	
	public V remove(K key) {
		return removeBucket(hashValue(key), key);
	}
	
	public int size() {
		return num_entries;
	}
	
	// protected abstract methods to be implemented by subclasses
	protected abstract void createTable();
	protected abstract V getBucket(int h, K k);
	protected abstract V putBucket(int h, K k, V v);
	protected abstract V removeBucket(int h, K k);
	
	private void resize(int new_capacity) {
		ArrayList<MapEntry<K, V>> array_list = new ArrayList<>(num_entries);
		
		for(MapEntry<K, V> e : entries()) {
			array_list.add(e);
		}
		
		num_capacity = new_capacity;
		createTable();
		num_entries = 0;
		
		for(MapEntry<K, V> e : array_list) {
			put(e.getKey(), e.getValue());
		}
	}
}
