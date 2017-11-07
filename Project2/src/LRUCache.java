import java.util.HashMap;

/**
 * An implementation of <tt>Cache</tt> that uses a least-recently-used (LRU)
 * eviction policy.
 */
public class LRUCache<T, U> implements Cache<T, U> {
	private DataProvider<T, U> provider;
	private int capacity;
	private Node<T, U> head, tail;
	private int currentSize;
	private int numMisses;
	
	private HashMap<T, Node<T, U>> cache = new HashMap<>();
	
	private class Node<T, U> {
		private T key;
		private U value;
		private Node<T, U> next, previous;
		
		public Node () {
			key = null;
			value = null;
			next = null;
			previous = null;
		}
		
		public Node (T key, U value){
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
		this.provider = provider;
		this.capacity = capacity;
	}

	/**
	 * Returns the value associated with the specified key.
	 * @param key the key
	 * @return the value associated with the key
	 */
	public U get (T key) {
		final Node<T, U> result = cache.get(key);
		if (result != null) {
			this.moveNodeToFirst(result);
			return result.getValue();
		}
		else {
			final Node<T, U> newNode = new Node (key, this.provider.get(key));
			if (this.currentSize >= this.capacity) {
				this.removeLast();
			}
			this.numMisses ++;
			this.addFirst(newNode);
			return newNode.getValue();
		}
	}
	
	private void addFirst(Node<T, U> node){
		cache.put(node.getkey(), node);
		if (head == null) {
			head = node;
			tail = node;
		}
		else {
			head.previous = node;
			node.next = head;
			head = node;
		}
		this.currentSize ++;
	}
	
	private void removeLast(){
		if (head == null) {
			return;
		}
		else if (head == tail) {
			cache.remove(tail);
			head = null;
			tail = null;
		}
		else {
			cache.remove(tail);
			tail = tail.previous;
			tail.next = null;
		}
	}
	
	private void moveNodeToFirst(Node<T, U> node) {
		final Node<T, U> before = node.previous;
		final Node<T, U> after = node.next;

		if (node == head) {
			return;
		}
		else if (node == tail) {
			before.next = null;
			tail = before;
			
		} else {
			before.next = after;
			after.previous = before;
		}
		head.previous = node;
		node.next = head;
		head = node;
	}
	
	/**
	 * Returns the number of cache misses since the object's instantiation.
	 * @return the number of cache misses since the object's instantiation.
	 */
	public int getNumMisses () {
		return this.numMisses;
	}
}
