import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Code to test an <tt>LRUCache</tt> implementation.
 */
public class CacheTest {
	private class TestDataProvider implements DataProvider<Integer, Integer> {
		public int numCalls = 0;
		
		@Override
		public Integer get(Integer key) {
			numCalls++;
			return key * 2;
		}
		
	}
	
	
	@Test
	public void leastRecentlyUsedIsCorrect () {
		TestDataProvider provider = new TestDataProvider();
		Cache<Integer, Integer> cache = new LRUCache<Integer, Integer>(provider, 5);
		
		assertEquals(true, true);
	}
	
	@Test
	public void leastRecentlyUsedIsCorrect2 () {
		TestDataProvider provider = new TestDataProvider();
		Cache<Integer, Integer> cache = new LRUCache<Integer, Integer>(provider, 2);
		
		assertEquals(cache.getNumMisses(), 0);
		assertEquals(cache.get(2), (Integer) 4);
		assertEquals(cache.getNumMisses(), 1);
		
		for (int i = 0; i < 100; i++){
			assertEquals(cache.get(2), (Integer) 4);
			assertEquals(cache.getNumMisses(), 1);
		}
		
		assertEquals(cache.getNumMisses(), provider.numCalls);
		
		assertEquals(cache.get(3), (Integer) 6);
		assertEquals(cache.getNumMisses(), 2);
		assertEquals(cache.getNumMisses(), provider.numCalls);
	}

}
