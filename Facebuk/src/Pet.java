
public class Pet extends LiveObject implements OwnedObjects {
	private Person owner;
	
	Pet (String name, Image image) {
		super (name, image);
	}
	
	public void setOwner (Person owner) {
		this.owner = owner;
	}
	
	public Person getOwner() {
		return this.owner;
	}
}
