import java.util.ArrayList;

public class MoviesNode extends IMDBNode{
	private ArrayList<ActorsNode> neighbors;
	
	public MoviesNode(String name){
		super(name);
		this.neighbors = new ArrayList<ActorsNode>();
	}
	
	public ArrayList<ActorsNode> getNeighbors(){
		return this.neighbors;
	}
	
	public void addActors(ActorsNode actor){
		this.neighbors.add(actor);
	}
}
