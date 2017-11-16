import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * This class will parse the data from the given file
 * And then make the common graph for those data
 * @author nguye
 *
 */
public abstract class IMDBGraph implements Graph {
    HashMap<String, MoviesNode> moviemap = new HashMap<>();
    HashMap<String, ActorsNode> actormap = new HashMap<>();

	public IMDBGraph(String actorFileName, String actressFileName) throws IOException {
        parse(actorFileName);
        parse(actressFileName);
    }

    private void parse(String filename) throws IOException {
        Scanner file = new Scanner(new File(filename), "ISO-8859-1");
        ActorsNode actor = null;
        
        while (file.hasNextLine()){
            String newLine = file.nextLine();
            int tabIndex2 = newLine.indexOf("\t");
            
            if (tabIndex2 >= 0 && newLine.contains("(") && newLine.contains(")") && !newLine.trim().isEmpty()){
            	if (tabIndex2 > 0){
            		String name = newLine.substring(0, tabIndex2);
            		
            		removeTVActor (actor);
            		
            		actor = new ActorsNode(name);
            		System.out.println(actor.getName());
            		actormap.put(name, actor);	
            		
            		int index = newLine.indexOf(")") + 1;

                  	while (index < tabIndex2) {
                       		 index = newLine.indexOf(")", index - 1) + 1;
                   	}
            		
            		String afterName = newLine.substring(tabIndex2).replaceAll(" ", "");
            		if (newLine.contains("(TV)") || afterName.contains("\"")){
            			continue;
            		}
            		else {
            			String movie = newLine.substring(tabIndex2,index);
        				movie = movie.replaceAll("\t", "");
        				movieHelperMethod(movie, actor);
            		}
            	}
            	
            	else {
            		if (newLine.contains("(TV)") || newLine.contains("\"")) {
            			continue;
            		}
            		String newMovie = newLine.substring(0,newLine.indexOf(")") + 1);
                    newMovie = newMovie.replaceAll("\t", "");
                    movieHelperMethod(newMovie, actor);
            	}
            }
            removeTVActor (actor);
        }
    }

    private void movieHelperMethod(String movie, ActorsNode actor) {
        MoviesNode movienode = null;
        if (moviemap.containsKey(movie)) {
            movienode = moviemap.get(movie);
        } else {
            movienode = new MoviesNode(movie);
            moviemap.put(movie, movienode);
        }

        movienode.addActors(actor);
        actor.addMovies(movienode);
    }
    
    private void removeTVActor (ActorsNode actor) {
    	if (actor != null){
    		ArrayList<MoviesNode> a = actor.getNeighbors();
    		if (actor.getNeighbors().size() == 0) {
            	actormap.remove(actor.getName());
            }
    	}
    }

}
