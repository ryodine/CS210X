import java.util.*;

class Moment extends FacebukObject{
	private ArrayList<Person> participants;
	private ArrayList<Float> smileValues;
	
	Moment (String name, Image image, ArrayList<Person> participants, ArrayList<Float> smileValues) {
		super (name, image);
		this.participants = participants;
		this.smileValues = smileValues;
	}
	
	ArrayList<Person> getParticipants() {
		return this.participants;
	}
	
	ArrayList<Float> getSmileValues () {
		return this.smileValues;
	}
}
