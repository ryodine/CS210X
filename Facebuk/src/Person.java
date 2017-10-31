import java.util.*;

class Person extends LiveObject {
	
	private ArrayList<Possession> _possessions;
	
	// Added field according to the test file
	private ArrayList<Pet> _pets;
	
	Person (String name, Image image) {
		super (name, image);
		this._possessions = new ArrayList<Possession> ();
	}
	
	void setPossessions (ArrayList<Possession> possessions) {
		this._possessions = possessions;
	}
	
	ArrayList<Possession> getPossessions () {
		return this._possessions;
	}
	
	// Added methods according to the test file
	void setPets (ArrayList<Pet> pets) {
		for (int i = 0; i < pets.size(); i ++) {
			pets.get(i).setOwner(this);
		}
		this._pets = pets;
	}
	
	ArrayList<Pet> getPets () {
		return this._pets;
	}
}
