import java.util.*;
public class LiveObject extends FacebukObject {
	private ArrayList friends;
	private ArrayList moments;
	
	public LiveObject (String name, Image image) {
		super (name, image);
		friends = new ArrayList();
		moments = new ArrayList();
	}
	
	public void setFriends (ArrayList friends) {
		this.friends = friends;
	}
	
	public void setMoments (ArrayList moments) {
		this.moments = moments;
	}
	
	public ArrayList getFriends() {
		return this.friends;
	}
	
	public ArrayList getMoments() {
		return this.moments;
	}
}
