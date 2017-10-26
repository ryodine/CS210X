import java.util.*;

class Moment extends FacebukObject{
	private ArrayList<Person> _participants;
	private ArrayList<Float> _smileValues;
	
	Moment (String name, Image image, ArrayList<Person> participants, ArrayList<Float> smileValues) {
		super (name, image);
		this._participants = participants;
		this._smileValues = smileValues;
	}
	
	ArrayList<Person> getParticipants() {
		return this._participants;
	}
	
	ArrayList<Float> getSmileValues () {
		return this._smileValues;
	}
}
