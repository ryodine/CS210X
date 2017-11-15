import java.io.IOException;
import java.util.Collection;

public class IMDBActorsGraph extends IMDBGraph{
	
	public IMDBActorsGraph (String actorsFileName, String actressesFilename) throws IOException {
		super(actorsFileName, actressesFilename);
	}
	
	public Collection<? extends Node> getNodes (){
		return actors;
	}
	
	public Node getNodeByName (String name){
		return null;
	}
}
