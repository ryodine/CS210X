import java.util.*;

public class Person extends LiveObject {
	
	private ArrayList<Possession> possessions;
	
	// Added field according to the test file
	private ArrayList<Pet> pets;
	
	public Person (String name, Image image) {
		super (name, image);
		this.possessions = new ArrayList<Possession> ();
	}
	
	public void setPossessions (ArrayList<Possession> possessions) {
		this.possessions = possessions;
	}
	
	public ArrayList<Possession> getPossessions () {
		return this.possessions;
	}
	
	// Added methods according to the test file
	public void setPets (ArrayList<Pet> pets) {
		this.pets = pets;
	}
	
	public ArrayList<Pet> getPets () {
		return this.pets;
	}
}
