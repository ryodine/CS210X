import java.io.IOException;
import java.util.Collection;

public class IMDBActorsGraph extends IMDBGraph{
	
	public IMDBActorsGraph (String actorsFileName, String actressesFilename) throws IOException {
		super(actorsFileName, actressesFilename);
	}

	/**
	 *
	 * @return a Collection of ActorNodes provided by the superclass IMDBGraph
	 */
	public Collection<? extends Node> getNodes (){
		return actormap.values();
	}

	/**
	 *
	 * @param name the name of the requested Node
	 * @return the ActorNode associated with that name
	 */
	public Node getNodeByName (String name){
		return actormap.get(name);
	}
}
