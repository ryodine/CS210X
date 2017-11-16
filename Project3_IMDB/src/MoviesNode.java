import java.util.ArrayList;

public class MoviesNode extends IMDBNode{
	private ArrayList<ActorsNode> neighbors;
	
	public MoviesNode(String name){
		super(name);
		this.neighbors = new ArrayList<ActorsNode>();
	}

	/**
	 *
	 * @return this MovieNode's neighbors
	 */
	public ArrayList<ActorsNode> getNeighbors(){
		return this.neighbors;
	}

	/**
	 * Adds an actor to this MovieNode's neighbors
	 * @param actor ActorNode to add to this MovieNode
	 */
	public void addActors(ActorsNode actor){
		this.neighbors.add(actor);
	}
}
