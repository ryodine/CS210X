
public abstract class IMDBNode implements Node{
	private String name;
	
	public IMDBNode(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
}
