import java.util.*;

public class Moment extends FacebukObject{
	private ArrayList<Person> participants;
	private ArrayList<Float> smileValues;
	
	public Moment (String name, Image image, ArrayList<Person> participants, ArrayList<Float> smileValues) {
		super (name, image);
		this.participants = participants;
		this.smileValues = smileValues;
	}
	
	public ArrayList<Person> getParticipants() {
		return this.participants;
	}
	
	public ArrayList<Float> getSmileValues () {
		return this.smileValues;
	}
}
