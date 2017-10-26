import java.util.*;

class Moment extends FacebukObject{
	private ArrayList _participants;
	private ArrayList _smileValues;
	
	Moment (String name, Image image, ArrayList participants, ArrayList smileValues) {
		super (name, image);
		this._participants = participants;
		this._smileValues = smileValues;
	}
	
	ArrayList getParticipants() {
		return this._participants;
	}
	
	ArrayList getSmileValues () {
		return this._smileValues;
	}
}
