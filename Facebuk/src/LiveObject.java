import java.util.*;
class LiveObject extends FacebukObject {
	private ArrayList friends;
	private ArrayList moments;
	
	LiveObject (String name, Image image) {
		super (name, image);
		friends = new ArrayList();
		moments = new ArrayList();
	}
	
	void setFriends (ArrayList friends) {
		this.friends = friends;
	}
	
	void setMoments (ArrayList moments) {
		this.moments = moments;
	}
	
	ArrayList getFriends() {
		return this.friends;
	}
	
	ArrayList getMoments() {
		return this.moments;
	}
}
