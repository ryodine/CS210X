
public class FacebukObject {
	private String name; 
	private Image image;
	
	public FacebukObject (String name, Image image) {
		this.name = name;
		this.image = image;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Image getImage() {
		return this.image;
	}
	
	public boolean equals (Object o) {
		if (o instanceof FacebukObject) {
			return (((FacebukObject) o).getName().equals(this.name));
		}
		else
			return false;
	}
	
}
