
public class Possession extends FacebukObject implements OwnedObjects {
	private float price;
	private Person owner;
	
	Possession (String name, Image image, float price) {
		super (name, image);
		this.price = price;
	}
	
	void setPrice(float price) {
		this.price = price;
	}
	
	float getPrice() {
		return this.price;
	}
	
	public void setOwner (Person owner) {
		this.owner = owner;
	}
	
	public Person getOwner() {
		return this.owner;
	}
}
