import java.util.HashMap;

/**
 * An implementation of <tt>Cache</tt> that uses a least-recently-used (LRU)
 * eviction policy.
 */
public class LRUCache<T, U> implements Cache<T, U> {
	
	class Node {
		protected T key;
		protected U value;
		protected Node next, previous;
		
		public Node() {
			key = null;
			value = null;
			next = null;
			previous = null;
		}
		
		public Node(T key, U value){
			this.key = key;
			this.value = value;
			next = null;
			previous = null;
		}
		
		public Node(T key, U value, Node next, Node previous){
			this.key = key;
			this.value = value;
			this.next = next;
			this.previous = previous;
		}
		
		public void setNext(Node next){
			this.next = next;
		}
		
		public void setPrevious(Node previous){
			this.previous = previous;
		}
		
		public Node getNext(){
			return this.next;
		}
		
		public Node getPrevious(){
			return this.previous;
		}
		
		public void setKey(T key){
			this.key = key;
		}
		
		public T getkey(){
			return this.key;
		}
		
		public void setValue(U value){
			this.value = value;
		}
		
		public U getValue(){
			return this.value;
		}
	}
	
	/**
	 * @param provider the data provider to consult for a cache miss
	 * @param capacity the exact number of (key,value) pairs to store in the cache
	 */
	public LRUCache (DataProvider<T, U> provider, int capacity) {
	}

	/**
	 * Returns the value associated with the specified key.
	 * @param key the key
	 * @return the value associated with the key
	 */
	public U get (T key) {
		return null;  // TODO -- implement!
	}

	/**
	 * Returns the number of cache misses since the object's instantiation.
	 * @return the number of cache misses since the object's instantiation.
	 */
	public int getNumMisses () {
		return 0;
	}
}
