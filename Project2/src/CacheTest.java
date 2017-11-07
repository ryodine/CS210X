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

	@Test
	public void cacheTestThree () {
		TestDataProvider provider = new TestDataProvider();
		Cache<Integer, Integer> cache = new LRUCache<Integer, Integer>(provider, 2);
		assertEquals(cache.get(10), (Integer)20); //insert A and test 10 -> 20
		assertEquals(cache.getNumMisses(), 1); //1 miss
		cache.get(10);//insert A again
		assertEquals(cache.getNumMisses(), 1); //still only 1 miss
		assertEquals(cache.get(0), (Integer)0); //insert B and test 0 -> 0
		assertEquals(cache.getNumMisses(), 2); //2 misses
		cache.get(10); //insert A again
		assertEquals(cache.getNumMisses(), 2); //still only 2 misses
		assertEquals(provider.numCalls, cache.getNumMisses()); //NumMisses = number of calls to data provider
	}

	@Test
	public void smallCacheTest() {
		TestDataProvider provider = new TestDataProvider();
		Cache<Integer, Integer> cache = new LRUCache<Integer, Integer>(provider, 1);

		assertEquals(0, cache.getNumMisses());

		assertEquals((Integer) 4, cache.get(2));
		assertEquals(1, cache.getNumMisses());
		assertEquals(provider.numCalls, cache.getNumMisses());


		assertEquals((Integer) 4, cache.get(2));
		assertEquals(1, cache.getNumMisses());
		assertEquals(provider.numCalls, cache.getNumMisses());

		assertEquals((Integer) 6, cache.get(3));
		assertEquals(2, cache.getNumMisses());
		assertEquals(provider.numCalls, cache.getNumMisses());
	}

	@Test
	public void LRUtest() {
		TestDataProvider provider = new TestDataProvider();
		Cache<Integer, Integer> cache = new LRUCache<Integer, Integer>(provider, 5);

		assertEquals((Integer) 2, cache.get(1));
		assertEquals((Integer) 4, cache.get(2));
		assertEquals((Integer) 6, cache.get(3));
		assertEquals((Integer) 8, cache.get(4));
		assertEquals((Integer) 10, cache.get(5));
		assertEquals(5, cache.getNumMisses());
		assertEquals(provider.numCalls, cache.getNumMisses());

		assertEquals((Integer) 2, cache.get(1));
		assertEquals(5, cache.getNumMisses());
		assertEquals(provider.numCalls, cache.getNumMisses());

		assertEquals((Integer) 12, cache.get(6));
		assertEquals(6, cache.getNumMisses());
		assertEquals(provider.numCalls, cache.getNumMisses());

		assertEquals((Integer) 4, cache.get(2));
		assertEquals(7, cache.getNumMisses());
		assertEquals(provider.numCalls, cache.getNumMisses());
	}
}

