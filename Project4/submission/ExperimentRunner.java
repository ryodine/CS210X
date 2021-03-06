import java.util.*;
import com.cs210x.*;

/**
  * Class to deduce the identity of mystery data structures.
  */
public class ExperimentRunner {
	private static final int NUM_DATA_STRUCTURES_TO_DEDUCE = 5;

	@SuppressWarnings("unchecked")
	public final static Collection210X<Integer>[] mysteryDataStructures = (Collection210X<Integer>[]) new Collection210X[NUM_DATA_STRUCTURES_TO_DEDUCE];
	public final static String cs210XTeamIDForProject4 = "rdjohnson";

	public static void main (String[] args) {

		// Fetch the collections whose type you must deduce.
		// Note -- you are free to change the type parameter from Integer to whatever you want. In this
		// case, make sure to replace (over the next 4 lines of code) Integer with whatever class you prefer.
		// In addition, you'll need to pass the method getMysteryDataStructure a "sample" (an instance) of 
		// the class you want the collection to store.

		for (int i = 0; i < NUM_DATA_STRUCTURES_TO_DEDUCE; i++) {
			mysteryDataStructures[i] = MysteryDataStructure.getMysteryDataStructure(cs210XTeamIDForProject4.hashCode(), i, new Integer(0));
		}

		// Write your code here...
		final Random random = new Random();  // instantiate a random number generator
		final int N = 100;
		for (int i = 0; i < N; i++) {  // populate the mystery data structure with 100 numbers
			mysteryDataStructures[0].add(new Integer(i));
		}
		final int elementToFind = random.nextInt(N);

		// This is an example of measuring an operation's time cost *without* averaging -- the times will vary wildly!		
		// You really should average...
		final long start = CPUClock.getNumTicks();
		// Time how long it takes to find a single, randomly chosen item stored in the mystery data structure
		final boolean result = mysteryDataStructures[0].contains(elementToFind);
		final long end = CPUClock.getNumTicks();
		final long elapsed = end - start;

		// Write a table of numbers (for different N -- here, we are just showing one value for simplicity) showing
		// the relationship between N and the time-cost associated with searching (with the contains method) through
		// a collection of N data.
		//System.out.println("N\tT (contains(o))");
		//System.out.println(N + "\t" + elapsed);

		/*
		testGetMax(0);
		testGetMax(1);
		testGetMax(2);
		testGetMax(3);
		testGetMax(4);
		*/

		for (int i = 0; i < 5; i++) {
			testAddMin(i);
			testGetMin(i);
			testGetMax(i);
			testSearch(i);
			testAddRandom(i);
			testRemoveMax(i);
		}

	}

	public static void testSearch(int index) {

		final int TEST_SIZE = 500;
		final int TEST_INCREMENT_LENGTH = 100;
		final int AVERAGE_SIZE = 120;
		final Random random = new Random();

		startTest("Search Test on " + index);
		logLine("length (N)", "CPUTime");

		for (int i = 1; i <= TEST_SIZE; i++) {
			final int N = i*TEST_INCREMENT_LENGTH;

			long sum = 0;
			Collection210X<Integer> dataStructure = mysteryDataStructures[index];
			dataStructure.clear();

			for (int k = 0; k < N; k++) {
				final int rand = random.nextInt(N);
				dataStructure.add(rand);
			}

			for (int j = 0; j < AVERAGE_SIZE; j++) {



				final int rand = random.nextInt(N);
				final long start = CPUClock.getNumTicks();
				dataStructure.contains(rand);
				final long end = CPUClock.getNumTicks();
				final long elapsed = end - start;
				sum+=elapsed;
			}

			logLine(new Integer(N).toString(), String.format("%2f",sum * 1.0 / AVERAGE_SIZE));
		}
	}

    public static void testGetMax(int ind) {
        int[] Ns = new int[500];
        final Random random = new Random();

        for(int i = 0; i < Ns.length; i++)
            Ns[i] = 5 * i;

        int max = Integer.MIN_VALUE;

        startTest("getMax test on " + ind);
        logLine("Size", "CPUTime");

        for(int n: Ns) {

            long sum = 0;
            for (int j = 0; j < 100; j++) {
                Collection210X<Integer> c = MysteryDataStructure.getMysteryDataStructure(cs210XTeamIDForProject4.hashCode(), ind, new Integer(0));

                for (int i = 0; i < n; i++) {
                    int randomint = random.nextInt(10000);
                    if (randomint > max)
                        max = randomint;
                    c.add(randomint);
                }

                final long start = CPUClock.getNumTicks();
                c.contains(max);
                final long end = CPUClock.getNumTicks();
                final long elapsed = end - start;
                sum += elapsed;
            }

            logLine("" + n, Long.toString(sum/100));
        }

    }

    public static void testGetMin(int ind) {
        int[] Ns = new int[500];
        final Random random = new Random();

        for(int i = 0; i < Ns.length; i++)
            Ns[i] = 5 * i;

        int min = Integer.MAX_VALUE;

        startTest("getMin test on " + ind);
        logLine("Size", "CPUTime");

        for(int n: Ns) {

            long sum = 0;

            for (int j = 0; j < 100; j++) {
                Collection210X<Integer> c = MysteryDataStructure.getMysteryDataStructure(cs210XTeamIDForProject4.hashCode(), ind, new Integer(0));
                for (int i = 0; i < n; i++) {
                    int randomint = random.nextInt(10000);
                    if (randomint < min)
                        min = randomint;
                    c.add(randomint);
                }

                final long start = CPUClock.getNumTicks();
                c.contains(min);
                final long end = CPUClock.getNumTicks();
                final long elapsed = end - start;
                sum += elapsed;
            }

            logLine("" + n, Long.toString(sum/100));
        }

    }
    
    
    /**
     * test the CPU time for the data structure chosen, given the index
     * @param index: index of the data structure which we want to test
     */
    public static void testRemoveMax (int index) {
    	startTest("Random Access Test on " + index);
		logLine("length (N)", "CPUTime");
		
		Collection210X<Integer> dataStructure = mysteryDataStructures[index];
		dataStructure.clear();
		
		for (int n = 50; n <= 10000; n += 50) {  // n varies from 50 to 10000
			double sum = 0;
			for (int count = 0; count < 100; count ++) { // average the elapsed time for n size
															// for 100 times 
				dataStructure.clear();
				int max = 5000;
				
				for (int i = 0; i < n - 1; i++) {
					int random = (int) (Math.random() * 4900);
					dataStructure.add(random);
				}
				dataStructure.add(max);
				
				final long start = CPUClock.getNumTicks();
				dataStructure.remove(max);
				final long end = CPUClock.getNumTicks();
				final long elapsed = end - start;
				
				sum += elapsed;
				
				if (count == 99) {
					double average = sum * 1.0 / 50;
					logLine(new Integer(n).toString(), new Double(average).toString());
				}
			}
		}
	}
    
    public static void testAddRandom (int index) {
    	startTest("Random Access Test on " + index);
		logLine("length (N)", "CPUTime");
		
		Collection210X<Integer> dataStructure = mysteryDataStructures[index];
		dataStructure.clear();
		
		for (int n = 0; n <= 10000; n += 50) {
			double sum = 0;
			for (int count = 0; count < 200; count ++) {
				dataStructure.clear();
				
				for (int i = 0; i < n; i++) {
					int random = (int) (Math.random() * 20000);
					dataStructure.add(random);
				}
				
				int random = (int) (Math.random() * 20000);
				final long start = CPUClock.getNumTicks();
				dataStructure.add(random);
				final long end = CPUClock.getNumTicks();
				final long elapsed = end - start;
				
				sum += elapsed;
				
				if (count == 199) {
					double average = sum * 1.0 / 50;
					logLine(new Integer(n).toString(), new Double(average).toString());
				}
			}
		}
    }
    
    public static void testAddMax (int index) {
    	startTest("Random Access Test on " + index);
		logLine("length (N)", "CPUTime");
		
		Collection210X<Integer> dataStructure = mysteryDataStructures[index];
		dataStructure.clear();
		
		for (int n = 0; n <= 10000; n += 50) {
			double sum = 0;
			for (int count = 0; count < 200; count ++) {
				dataStructure.clear();
				
				for (int i = 0; i < n; i++) {
					int random = (int) (Math.random() * 20000);
					dataStructure.add(random);
				}
				int max = 25000;
				
				final long start = CPUClock.getNumTicks();
				dataStructure.add(max);
				final long end = CPUClock.getNumTicks();
				final long elapsed = end - start;
				
				sum += elapsed;
				
				if (count == 199) {
					double average = sum * 1.0 / 50;
					logLine(new Integer(n).toString(), new Double(average).toString());
				}
			}
		}
    }

    public static void testAddMin (int index) {
    	startTest("Random Access Test on " + index);
		logLine("length (N)", "CPUTime");
		
		Collection210X<Integer> dataStructure = mysteryDataStructures[index];
		dataStructure.clear();
		
		for (int n = 0; n <= 10000; n += 50) {
			double sum = 0;
			for (int count = 0; count < 200; count ++) {
				dataStructure.clear();
				
				for (int i = 0; i < n; i++) {
					int random = 1000 + (int) (Math.random() * 20000);
					dataStructure.add(random);
				}
				int min = 5;
				
				final long start = CPUClock.getNumTicks();
				dataStructure.add(min);
				final long end = CPUClock.getNumTicks();
				final long elapsed = end - start;
				
				sum += elapsed;
				
				if (count == 199) {
					double average = sum * 1.0 / 50;
					logLine(new Integer(n).toString(), new Double(average).toString());
				}
			}
		}
    }
	public static void startTest(String name) {
		System.out.println("!!!!! Starting test: " + name);
	}

	public static void logLine(String ... lines) {
		for (int i = 0; i < lines.length; i++) {
			System.out.print(lines[i]);
			if (i != lines.length - 1) {
				System.out.print("\t");
			} else {
				System.out.println();
			}
		}
	}
}
