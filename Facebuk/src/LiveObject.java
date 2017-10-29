import java.util.*;
class LiveObject extends FacebukObject {
	private ArrayList _friends;
	private ArrayList _moments;
	
	LiveObject (String name, Image image) {
		super (name, image);
		_friends = new ArrayList();
		_moments = new ArrayList();
	}

	void setFriends (ArrayList friends) {
		this._friends = friends;
	}
	
	void setMoments (ArrayList moments) {
		this._moments = moments;
	}
	
	ArrayList getFriends() {
		return this._friends;
	}
	
	ArrayList getMoments() {
		return this._moments;
	}

	/**
	 * Looks through every moment that a LiveObject appears in to find the person with the highest average happiness
	 * in those moments
	 *
	 * @return LiveObject of person (or animal) who this person (or animal) is happiest with
	 */
	LiveObject getFriendWithWhomIAmHappiest () {
		//Map of a person to the sum of their happiness
		Map<LiveObject, Float> happinessSum = new HashMap<>();

		//Map of a person to the num,ber of times they appear in moments
		Map<LiveObject, Integer> momentAppearances = new HashMap<>();

		//Note: Average happiness is equal to happiness sum / times in moments. This is why there are two maps

		for (Object m : _moments) {
			Moment thisMoment = (Moment) m;
			ArrayList participants = thisMoment.getParticipants();
			for (int i = 0; i < participants.size(); i++) {
				LiveObject liveobject = (LiveObject) participants.get(i);

				if (liveobject.equals(this)) continue;

				Float happiness = (Float) thisMoment.getSmileValues().get(i);

				Float sum = happinessSum.get(liveobject);

				if (sum != null) {
					happinessSum.put(liveobject, sum + happiness);
					momentAppearances.put(liveobject, momentAppearances.get(liveobject) + 1);
				} else {
					happinessSum.put(liveobject, happiness);
					momentAppearances.put(liveobject, 1);
				}
			}
		}

		Float maxAvg = Float.MIN_VALUE;
		LiveObject maxFriend = null;

		for (Map.Entry<LiveObject, Integer> appearances : momentAppearances.entrySet()) {
			Float avg = happinessSum.get(appearances.getKey()) / appearances.getValue();
			LiveObject thisLiveObject = appearances.getKey();

			if (maxAvg < avg) {
				maxAvg = avg;
				maxFriend = thisLiveObject;
			}

		}

		return maxFriend;
	}
	
	// !!!
	// Need to implement

	/**
	 *
	 * @return Moment in LiveObject with highest average SmileValue, or null if number of Moments in LiveObject is zero
	 */
	Moment getOverallHappiestMoment() {
		Moment happiestM = null;
		Float highest = Float.MIN_VALUE;
		for (Object m: getMoments()) {
		    Moment temp = (Moment) m;
		    Float sum = 0.0f;
            for (Object f: temp.getSmileValues()) {
                sum += (Float) f;
            }
		    Float avg = sum/temp.getSmileValues().size();
            if(avg > highest || happiestM == null){
                happiestM = temp;
                highest = avg;
            }
        }
        return happiestM;
	}

	/**
	 * Finds a clique by testing if every element of a Power Set of the freinds of this LiveObject is a clique, and
	 * keeps the clique found with the maximum size
	 *
	 * Power Set of a given ArrayList is determined by using patterns in binary
	 *
	 * @return a list containing the maximum clique of friends with whom the target person/pet could go out
	 */
	ArrayList findMaximumCliqueOfFriends () {

		ArrayList maxSet = new ArrayList();

		// Iterate from 0 to 2^(list length) - 1, since power sets have 2^(list length) subsets
		for (int i = 0; i < (1 << _friends.size()); i++) {
			ArrayList setbuilder = new ArrayList();

			//Iterate from 0 to list length - 1, each time building a bit mask of that position
			for (int j = 0; j < _friends.size(); j++) {
				int bitmask = (1 << j);

				//if i & bitmask returns nonzero, there is a 1 at the 2^j position of i, then add jth element of input
				if ((i & bitmask) > 0) {
					setbuilder.add(_friends.get(j));
				}
			}
			if (isClique(setbuilder) && setbuilder.size() > maxSet.size()) {
				maxSet = setbuilder;
			}
		}
		return maxSet;
	}

	/**
	 * Since this is a helper method that should not depend on any instance variables of the target object, it can
	 * (and should) be declared static) Even though this is a helper method, make sure it is declared public so
	 * we can test it!
	 *
	 * @return true if and only if all the people/pets in the specified set are all friends with each other.
	 */
	static boolean isClique (ArrayList set) {
		if (set.size() <= 0) {
			return false;
		}
		LiveObject oneFriend;
		ArrayList listOfFriend;
		for (int i = 0; i < set.size(); i ++) {
			oneFriend = (LiveObject) (set.get(i));
			listOfFriend = oneFriend.getFriends();
			for (int j = 0; j < set.size(); j ++) {
				if (oneFriend.equals(set.get(j))) {
					continue;
				}
				else if (! (listOfFriend.contains(set.get(j)))) {
					return false;
				}
			}
		}
		return true;
	}
}
