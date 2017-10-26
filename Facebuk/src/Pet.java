
public class Pet extends LiveObject implements OwnedObjects {
	private Person _owner;
	
	Pet (String name, Image image) {
		super (name, image);
	}
	
	public void setOwner (Person owner) {
		this._owner = owner;
	}
	
	public Person getOwner() {
		return this._owner;
	}
}
