
public class Possession extends FacebukObject implements OwnedObjects {
	private float _price;
	private Person _owner;
	
	Possession (String name, Image image, float price) {
		super (name, image);
		this._price = price;
	}
	
	void setPrice(float price) {
		this._price = price;
	}
	
	float getPrice() {
		return this._price;
	}
	
	public void setOwner (Person owner) {
		this._owner = owner;
	}
	
	public Person getOwner() {
		return this._owner;
	}
}
