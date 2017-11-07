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
		DataProvider<Integer, Integer> provider = new TestDataProvider();
		Cache<Integer, Integer> cache = new LRUCache<Integer, Integer>(provider, 5);
		
		
		assertEquals(true, true);
	}
}
