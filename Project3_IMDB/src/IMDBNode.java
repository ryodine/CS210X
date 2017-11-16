
/**
 * Superclass for ActorsNode and MoviesNode
 */
public abstract class IMDBNode implements Node{
	private String name;


	public IMDBNode(String name){
		this.name = name;
	}

	/**
	 * @return This IMDBNode's name
	 */
	public String getName(){
		return this.name;
	}
}
