import java.io.IOException;
import java.util.Collection;

public class IMDBMoviesGraph extends IMDBGraph{
	
	public IMDBMoviesGraph (String actorsFilename, String actressesFilename) throws IOException {
		super(actorsFilename, actressesFilename);
	}
	
	public Collection<? extends Node> getNodes (){
		return moviemap.values();
	}
	
	public Node getNodeByName (String name){
		return moviemap.get(name);
	}
}
