import java.util.*;
import com.cs210x.*;

/**
  * Class to deduce the identity of mystery data structures.
  */
public class ExperimentRunner {
	private static final int NUM_DATA_STRUCTURES_TO_DEDUCE = 5;

	@SuppressWarnings("unchecked")
	public final static Collection210X<Integer>[] mysteryDataStructures = (Collection210X<Integer>[]) new Collection210X[NUM_DATA_STRUCTURES_TO_DEDUCE];

	public static void main (String[] args) {
		final String cs210XTeamIDForProject4 = "rdjohnson";

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
		System.out.println("N\tT (contains(o))");
		System.out.println(N + "\t" + elapsed);


		testSearch(2);

	}

	public static void testSearch(int index) {

		final int TEST_SIZE = 500;
		final int TEST_INCREMENT_LENGTH = 100;
		final int AVERAGE_SIZE = 120;
		final Random random = new Random();

		startTest("Random Access Test on " + index);
		logLine("length (N)", "CPUTime");


		for (int i = 1; i <= TEST_SIZE; i++) {
			final int N = i*TEST_INCREMENT_LENGTH;

			long sum = 0;
			Collection210X<Integer> dataStructure = mysteryDataStructures[index];

			for (int j = 0; j < TEST_SIZE; j++) {
				final int rand = random.nextInt(TEST_SIZE);
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
