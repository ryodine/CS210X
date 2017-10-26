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
	
	// !!!
	// Need to implement
	LiveObject getFriendWithWhomIAmHappiest () {
		return null;
	}
	
	// !!!
	// Need to implement
	Moment getOverallHappiestMoment () {
		return null;
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
