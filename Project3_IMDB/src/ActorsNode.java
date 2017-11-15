import java.util.ArrayList;

public class ActorsNode extends IMDBNode{
	private ArrayList<MoviesNode> neighbors;
	
	public ActorsNode(String name){
		super(name);
		this.neighbors = new ArrayList<MoviesNode>();
	}
	
	public ArrayList<MoviesNode> getNeighbors(){
		return this.neighbors;
	}
	
	public void addMovies(MoviesNode movie){
		this.neighbors.add(movie);
	}
}
