package data_structure;
import java.math.BigInteger;
import java.util.Iterator;
import Interfaces.IMap;
import data_structure.AbstractMap.MapEntry;
import load_shedding.Vehicles;

/**
 * Hash table using separate chaining.
 */

public class HashTable<K, V> implements IMap<K,V> {
    Object[] hashTable;
    int a, b;
    int size = 0;
    int _capacity;

    /**
     * Default Constructor
     */
    public HashTable() {
        this(1000);
    }

    /**
     * Constructor
     * @param size the size of the hashtable
     */
    public HashTable(int size) {
        //Sigh.... Java :/
        hashTable = new Object[size];

        for (int i = 0; i < size; i++) {
            hashTable[i] = new Object();
        }

        //Calculate prime factors for the MAD calculation
        a = primeSmallerThan(size);
        b = primeSmallerThan(size/2);
    }

    /**
     * Put a key/value entry into the hash table
     * @param key the key to add
     * @param value the value to add
     * @return if the item was updated then return the original value, if it was a new item then return null
     */
    @Override
    public void put(K key, V value) {
    	int hashing = (int) hash(key);
		Entry<K, V> new_item = new Entry<>(key, value);
		if(size == 0) {
			PositionList<Entry<K, V>> list = new PositionList<Entry<K, V>>();
			list.addLast(new_item);
			hashTable[hashing] = list;
			size ++;
		}else {
			PositionList<Entry<K, V>> _list = (PositionList<Entry<K, V>>) hashTable[hashing];
			_list.addLast(new_item);
			hashTable[hashing] = _list;
		}
    }

    /**
     * Remove an item from the hash table
     * @param key the key that should be removed
     * @return the value of the item that was removed, otherwise return null
     */
    @Override
    public V remove(K key) {
    	int hashing = (int) hash(key);
		PositionList<Entry<K, V>> bucket = (PositionList<Entry<K, V>>) hashTable[hashing];
		if(!bucket.isEmpty()) {
			Iterator<Entry<K, V>> bucket_list = bucket.iterator();
			while(bucket_list.hasNext()) {
				Entry<K, V> current = bucket_list.next();
				if(current.getKey().equals(key)) {
					bucket_list.remove();
					size --;
					return current.getValue();
				}
			}
		}
        return null;
    }

    /**
     * The size of the hash table
     * @return the size of the hash table
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Return true if the hash table is empty
     * @return return true if the hash table is empty
     */
    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Return an iterator over the keys in the hash table
     * REMEMBER THAT THIS IS A BUCKET ARRAY - USE AN ITERATOR
     * @return an iterator over all the keys
     */
    @Override
    public Iterable<K> keys() { 
    	PositionList<K> position_list = new PositionList<K>();
    	for(int i = 0; i < hashTable.length; i++) {
			PositionList<Entry<K, V>> bucket = (PositionList<Entry<K, V>>) hashTable[i];
			Iterator<Entry<K, V>> bucket_list = bucket.iterator();
			while(bucket_list.hasNext()) {
				position_list.addLast(bucket_list.next().getKey());
			}
		}
    	return position_list;
    }

    /**
     * Return an iterator over the values in the hash table
     * REMEMBER THAT THIS IS A BUCKET ARRAY - USE AN ITERATOR
     * @return an iterator over all the keys in the hash table.
     */
    @Override
    public Iterable<V> values() {     
    	PositionList<V> position_list = new PositionList<V>();
    	for(int i = 0; i < hashTable.length; i++) {
			PositionList<Entry<K, V>> bucket = (PositionList<Entry<K, V>>) hashTable[i];
			Iterator<Entry<K, V>> bucket_iterator = bucket.iterator();
			while(bucket_iterator.hasNext()) {
				Entry<K, V> item = bucket_iterator.next();
				position_list.addLast(item.getValue());
			}
		}
    	return position_list;
    }

    /**
     * Compute the hash for an object. This function uses the string represention of the key
     * to build the hash.
     * @param key the key that must be hashed.
     * @return an index for the given key.
     */
    private int hash(K key) {
        return mad(fnv(key.toString().toCharArray()));
    }

    /**
     * Compute a Fowler-Noll-Vo hash over a passed character array.
     * @param data a character array that contains the data to be hashed.
     * @return a 64-bit hash value
     */
    private long fnv(char[] data) {
        BigInteger hash = new BigInteger("14695981039346656037");
        byte[] b = new byte[1];
        
	//TODO: Complete - 10 Marks

        return Math.abs(hash.longValue());
    }

    /**
     * The compression function: Compress the input hash code into an index for
     * the hash table. This will use the MAD method to compress the hash
     * @param value the hash code to compress
     * @return the index for the hash table.
     */
    private int mad(long value) {
        return Math.abs((int)((a*value + b) % hashTable.length));
    }

    //Sieve out primes less than upperBound
    // This would be nicer with Java 8, but we only have Java 7 so it is what it is #yolo.
    private int primeSmallerThan(int upperBound) {
        boolean[] array = new boolean[upperBound+1];

        for (int i = 0; i < upperBound+1; i++) {
            array[i] = true;
        }
        array[0] = false;
        array[1] = false;

        //sieve!!!!!!!!!
        for (int j = 2; j < upperBound+1; j+=1) {
            if (array[j]) {
                for (int i = (j * 2); i < upperBound + 1; i += j) {
                    array[i] = false;
                }
            }
        }

        //return!!!!!!!!
        for (int j = upperBound; j >= 0; j--) {
            if (array[j]) {
                return j;
            }
        }
        return 2; //here be banshees - should never be executed.
    }

	@Override
	public V get(K key) {
		int _hash = (int) hash(key);
		PositionList<Entry<K, V>> _bucket = (PositionList<Entry<K, V>>) hashTable[_hash];
		if(!_bucket.isEmpty()) {
			Iterator<Entry<K, V>> bucket_list = _bucket.iterator();
			while(bucket_list.hasNext()) {
				Entry<K, V> current = bucket_list.next();
				if(current.getKey().equals(key)) {
					return current.getValue();
				}
			}
		}
		return null;
	}

	@Override
	public Iterable<MapEntry<K, V>> entries() {
		PositionList<AbstractMap.MapEntry<K, V>> buffer_list = new PositionList<>();
		for(int i = 0; i < _capacity; i++) {
			if(hashTable[i] != null) {
				for(AbstractMap.MapEntry<K, V> entry : ((ProbeHashMap<K, V>) hashTable[i]).entries()) {
					buffer_list.addFirst(entry);
				}
				
			}
		}
		return buffer_list;
	}

	public boolean containsKey(Vehicles element) {
		// TODO Auto-generated method stub
		return false;
	}
}

