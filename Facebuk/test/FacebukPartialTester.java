import static org.junit.Assert.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;


/**
 * This is a SUBSET of the unit tests with which we will grade your code.
 *
 * Make absolute sure that your code can compile together with this tester!
 * If it does not, you may get a very low grade for your assignment.
 */
public class FacebukPartialTester {
	private Person _barack, _michelle, _kevin, _ina, _joe, _malia;
	private Pet _bo, _sunny;
	private Moment _meAndBarack;
	private ArrayList _michelleAndBarack, _michelleJoeBoAndMalia;
	
	// added private variables to test isClique
	private ArrayList cliqueSet;
	private ArrayList notCliqueSet;
	private ArrayList cliqueSet2;
	private ArrayList notCliqueSet2;
	
	@Before
	public void setUp() {
		initPeople();
		initPets();
		initGroups();
		initPossessions();
		initMoments();	
		
		// a set of friends
		
				// true clique:
				cliqueSet = new ArrayList();
				cliqueSet.add(_malia);
				cliqueSet.add(_barack);
				cliqueSet.add(_kevin);
				cliqueSet.add(_michelle);
				
				// not a clique
				notCliqueSet = new ArrayList();
				notCliqueSet.add(_malia);
				notCliqueSet.add(_barack);
				notCliqueSet.add(_michelle);
				notCliqueSet.add(_kevin);
				notCliqueSet.add(_ina);
				
				// true clique:
				cliqueSet2 = new ArrayList();
				cliqueSet2.add(_michelle);
				cliqueSet2.add(_malia);
				cliqueSet2.add(_kevin);
				
				// not clique:
				notCliqueSet2 = new ArrayList();
				notCliqueSet2.add(_malia);
				notCliqueSet2.add(_bo);
				notCliqueSet2.add(_joe);
				notCliqueSet2.add(_sunny);
				
	}

	private void initPeople() {
		_michelle = new Person("Michelle", new Image("Michelle.png"));
		_barack = new Person("Barack", new Image("Barack.png"));
		_kevin = new Person("Kevin", new Image("Kevin.png"));
		_ina = new Person("Ina", new Image("Ina.png"));
		_joe = new Person("Joe", new Image("Joe.png"));
		_malia = new Person("Malia", new Image("Malia.png"));
	}

	private void initPets() {
		_bo = new Pet("Bo", new Image("Bo.png"));
		_sunny = new Pet("Sunny", new Image("Sunny.png"));

		_bo.setOwner(_barack);
		_sunny.setOwner(_michelle);
	}

	private void initGroups() {
		// Kevin, Barack, and Ina
		final ArrayList michelleFriends = new ArrayList();
		michelleFriends.add(_kevin);
		michelleFriends.add(_barack);
		michelleFriends.add(_ina);
		
		// added
		michelleFriends.add(_joe);
		michelleFriends.add(_malia);

		// Michelle and Barack
		_michelleAndBarack = new ArrayList();
		_michelleAndBarack.add(_michelle);
		_michelleAndBarack.add(_barack);

		// Michelle, Joe, Bo, and Malia
		_michelleJoeBoAndMalia = new ArrayList();
		_michelleJoeBoAndMalia.add(_michelle);
		_michelleJoeBoAndMalia.add(_joe);
		_michelleJoeBoAndMalia.add(_bo);
		_michelleJoeBoAndMalia.add(_malia);

		// Malia and Sunny
		final ArrayList maliaAndSunny = new ArrayList();
		maliaAndSunny.add(_malia);
		maliaAndSunny.add(_sunny);

		// Malia and Bo
		final ArrayList maliaAndBo = new ArrayList();
		maliaAndSunny.add(_malia);
		maliaAndSunny.add(_bo);

		// Michelle
		final ArrayList michelleList = new ArrayList();
		michelleList.add(_michelle);

		// Bo
		final ArrayList boList = new ArrayList();
		boList.add(_bo);
		
		
		
		// added
		// barack friends
		final ArrayList barackFriends = new ArrayList();
		barackFriends.add(_michelle);
		barackFriends.add(_ina);
		barackFriends.add(_malia);
		barackFriends.add(_kevin);
		
		
		// added 
		// kevin friends
		final ArrayList kevinFriends = new ArrayList();
		kevinFriends.add(_michelle);
		kevinFriends.add(_barack);
		kevinFriends.add(_joe);
		kevinFriends.add(_malia);
		
		// added 
		// ina's friends
		final ArrayList inaFriends = new ArrayList();
		inaFriends.add(_michelle);
		inaFriends.add(_barack);
		
		
		// added
		// joe's friends
		final ArrayList joeFriends = new ArrayList();
		joeFriends.add(_michelle);
		joeFriends.add(_kevin);
		
		// added
		// malia's friends
		final ArrayList maliaFriends = new ArrayList();
		maliaFriends.add(_bo);
		maliaFriends.add(_michelle);
		maliaFriends.add(_barack);
		maliaFriends.add(_kevin);
		
		// Set people's friend lists
		/*
		_michelle.setFriends(michelleFriends);
		_malia.setFriends(boList);
		_sunny.setFriends(boList);
		_barack.setFriends(michelleList);
		_kevin.setFriends(michelleList);
		_ina.setFriends(michelleList);
		*/
		
		_michelle.setFriends(michelleFriends);
		_malia.setFriends(maliaFriends);
		_sunny.setFriends(boList);
		_barack.setFriends(barackFriends);
		_kevin.setFriends(kevinFriends);
		_ina.setFriends(inaFriends);
		_joe.setFriends(joeFriends);
		
		// Finish configuring pets
		_bo.setFriends(maliaAndSunny);
		_sunny.setFriends(maliaAndBo);
		final ArrayList boAndSunny = new ArrayList();
		boAndSunny.add(_bo);
		boAndSunny.add(_sunny);
		_michelle.setPets(boAndSunny);
	}

	private void initPossessions() {
		final Possession boxingBag = new Possession("BoxingBag", new Image("BoxingBag.png"), 20.0f);
		boxingBag.setOwner(_michelle);
		final ArrayList michellePossessions = new ArrayList();
		michellePossessions.add(boxingBag);
		_michelle.setPossessions(michellePossessions);
	}

	private void initMoments() {
		// Smiles
		final ArrayList michelleAndBarackSmiles = new ArrayList();
		michelleAndBarackSmiles.add(0.25f);
		michelleAndBarackSmiles.add(0.75f);

		final ArrayList michelleJoeBoAndMaliaSmiles = new ArrayList();
		michelleJoeBoAndMaliaSmiles.add(0.2f);
		michelleJoeBoAndMaliaSmiles.add(0.3f);
		michelleJoeBoAndMaliaSmiles.add(0.4f);
		michelleJoeBoAndMaliaSmiles.add(0.5f);

		// Moments
		_meAndBarack = new Moment("Me & Barack", new Image("MeAndBarack.png"), _michelleAndBarack, michelleAndBarackSmiles);
		final Moment meJoeAndCo = new Moment("Me, Joe & co.", new Image("MeJoeAndCo.png"), _michelleJoeBoAndMalia, michelleJoeBoAndMaliaSmiles);

		final ArrayList michelleMoments = new ArrayList();
		michelleMoments.add(_meAndBarack);
		michelleMoments.add(meJoeAndCo);
		_michelle.setMoments(michelleMoments);

		final ArrayList barackMoments = new ArrayList();
		barackMoments.add(_meAndBarack);
		_barack.setMoments(barackMoments);

		final ArrayList joeMoments = new ArrayList();
		joeMoments.add(meJoeAndCo);
		_joe.setMoments(joeMoments);

		final ArrayList maliaMoments = new ArrayList();
		maliaMoments.add(meJoeAndCo);
		_malia.setMoments(maliaMoments);

		final ArrayList boMoments = new ArrayList();
		boMoments.add(meJoeAndCo);
		_bo.setMoments(boMoments);
	}

	@Test
	public void testEquals() {
		assertEquals(_michelle, new Person("Michelle", new Image("Michelle.png")));
		assertEquals(_michelle, new Person("Michelle", new Image("Michelle2.png")));  // should still work
		assertNotEquals(_michelle, _barack);
	}

	@Test
	public void testFindBestMoment() {
		assertEquals(_michelle.getOverallHappiestMoment(), _meAndBarack);
	}

	@Test
	public void testGetFriendWithWhomIAmHappiest() {
		assertEquals(_michelle.getFriendWithWhomIAmHappiest(), _barack);
		assertEquals(_bo, _malia.getFriendWithWhomIAmHappiest());
		assertNull(_kevin.getFriendWithWhomIAmHappiest());
	}

	// TODO: write more methods to test getFriendWithWhomIAmHappiest 
	// TODO: write more methods to test getOverallHappiestMoment 

	// TODO: write methods to test isClique 
	// TODO: write methods to test findMaximumCliqueOfFriends

	@Test
	public void findMaximumCliqueOfFriends() {
		ArrayList expectedBarackFriendClique = new ArrayList();
		expectedBarackFriendClique.add(_michelle);
		expectedBarackFriendClique.add(_malia);
		expectedBarackFriendClique.add(_kevin);
		ArrayList barackFriends = _barack.findMaximumCliqueOfFriends();
		assertTrue(barackFriends.containsAll(expectedBarackFriendClique) && expectedBarackFriendClique.containsAll(barackFriends));

		ArrayList expectedJoeFriendClique = new ArrayList();
		expectedJoeFriendClique.add(_kevin);
		expectedJoeFriendClique.add(_michelle);
		ArrayList joeFriends = _joe.findMaximumCliqueOfFriends();
		assertTrue(joeFriends.containsAll(expectedJoeFriendClique) && expectedJoeFriendClique.containsAll(joeFriends));
	}
	
	@Test public void testIsClique() {
		assertEquals(LiveObject.isClique(new ArrayList()), false);
		assertEquals(LiveObject.isClique(cliqueSet), true);
		assertEquals(LiveObject.isClique(notCliqueSet), false);
		assertEquals(LiveObject.isClique(cliqueSet2), true);
		assertEquals(LiveObject.isClique(notCliqueSet2), false);
		
		ArrayList cliqueSet3 = new ArrayList();
		cliqueSet3.add(_kevin);
		cliqueSet3.add(_michelle);
		
		assertEquals(LiveObject.isClique(cliqueSet3), true);
		
		ArrayList cliqueSet4 = new ArrayList();
		cliqueSet4.add(_kevin);
		cliqueSet4.add(_michelle);
		cliqueSet4.add(_barack);
		cliqueSet4.add(_malia);
		cliqueSet4.add(_joe);
		
		assertEquals(LiveObject.isClique(cliqueSet4), false);
		
		/*System.out.println(LiveObject.isClique(cliqueSet2));
		System.out.println(cliqueSet2.toString());
		System.out.println(cliqueSet.toString());
		System.out.println(notCliqueSet2.toString());*/
	}
}