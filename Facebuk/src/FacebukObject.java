
public class FacebukObject {
	private String _name;
	private Image _image;
	
	FacebukObject (String name, Image image) {
		this._name = name;
		this._image = image;
	}

	public String toString() {
		return _name;
	}
	
	String getName() {
		return this._name;
	}
	
	Image getImage() {
		return this._image;
	}
	
	public boolean equals (Object o) {
		if (o instanceof FacebukObject) {
			return (((FacebukObject) o).getName().equals(this._name));
		}
		else
			return false;
	}
	
}
