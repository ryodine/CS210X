import java.io.IOException;
import java.util.Collection;

public class IMDBMoviesGraph extends IMDBGraph{
	
	public IMDBMoviesGraph (String actorsFilename, String actressesFilename) throws IOException {
		super(actorsFilename, actressesFilename);
	}

	/**
	 *
	 * @return a Collection of MovieNodes provided by the superclass IMDBGraph
	 */
	public Collection<? extends Node> getNodes (){
		return moviemap.values();
	}

	/**
	 *
	 * @param name the name of the requested Node
	 * @return the MovieNode associated with that name
	 */
	public Node getNodeByName (String name){
		return moviemap.get(name);
	}
}
