import java.util.ArrayList;

public class ActorsNode extends IMDBNode{
	private ArrayList<MoviesNode> neighbors;
	
	public ActorsNode(String name){
		super(name);
		this.neighbors = new ArrayList<MoviesNode>();
	}

	/**
	 *
	 * @return this ActorNode's neighbors
	 */
	public ArrayList<MoviesNode> getNeighbors(){
		return this.neighbors;
	}

	/**
	 * Adds a movie to this ActorNode's neighbors
	 * @param movie MovieNode to add to this ActorNode
	 */
	public void addMovies(MoviesNode movie){
		this.neighbors.add(movie);
	}
}
