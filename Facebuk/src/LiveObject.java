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
}
