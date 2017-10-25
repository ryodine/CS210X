import java.util.*;

class Person extends LiveObject {
	
	private ArrayList<Possession> possessions;
	
	// Added field according to the test file
	private ArrayList<Pet> pets;
	
	Person (String name, Image image) {
		super (name, image);
		this.possessions = new ArrayList<Possession> ();
	}
	
	void setPossessions (ArrayList<Possession> possessions) {
		this.possessions = possessions;
	}
	
	ArrayList<Possession> getPossessions () {
		return this.possessions;
	}
	
	// Added methods according to the test file
	void setPets (ArrayList<Pet> pets) {
		this.pets = pets;
	}
	
	ArrayList<Pet> getPets () {
		return this.pets;
	}
}
