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
	
	/*
	 * !!!
	 * Need to implement
	 * return a list containing the maximum clique of friends with whom the target person/pet could go out, 
	 * such that each of his/her friends is also friends with everyone else in the set.
	 */
	ArrayList findMaximumCliqueOfFriends () {
		return null;
	}
	
	/*
	 * !!!
	 * Need to implement
	 * returns true if and only if all the people/pets in the specified set are all friends with each other. 
	 * Since this is a helper method that should not depend on any instance variables of the target object, it can (and should) be declared static. 
	 * Even though this is a helper method, make sure it is declared public so we can test it!
	 */
	static boolean isClique (ArrayList set) {
		return true;
	}
}
