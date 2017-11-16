import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * This class will parse the data from the given file
 * And then make the common graph for both the IMDBActorsGraph and the IMDBMoviesGraph
 * @author nguye
 */
public abstract class IMDBGraph implements Graph {
    HashMap<String, MoviesNode> moviemap = new HashMap<>();
    HashMap<String, ActorsNode> actormap = new HashMap<>();

    /**
     * Parse data from 2 input file
     * Create all ActorsNode and MoviesNode and put them in the Graph structure
     * @param actorFileName: file name containing the actor's list
     * @param actressFileName: file name containing the actresses' list
     * @throws IOException when file is not found, or the path provided is not valid
     */
	public IMDBGraph(String actorFileName, String actressFileName) throws IOException {
        parse(actorFileName);
        parse(actressFileName);
    }

	/**
	 * Parse the data in a single input file
	 * Create all ActorsNode and MoviesNode, put them in the Graph structure
	 * @param filename: input file 
	 * @throws IOException when file is not found
	 */
    private void parse (String filename) throws IOException {
        Scanner file = new Scanner(new File(filename), "ISO-8859-1");
        ActorsNode actor = null; 
        
        while (file.hasNextLine()){
            String newLine = file.nextLine();
            int tabIndex2 = newLine.indexOf("\t");  // tabIndex2 is the index of the very first tab in the line
            
            // check if the current line is a valid line to read 
            if (tabIndex2 >= 0 && newLine.contains("(") && newLine.contains(")") && !newLine.trim().isEmpty()){
            	if (tabIndex2 > 0) {   // check if new actor appears
            		String name = newLine.substring(0, tabIndex2);
            		
            		removeTVActor (actor);  // remove actor who only stars in TV shows
            		
            		actor = new ActorsNode(name);
            		System.out.println(actor.getName());
            		actormap.put(name, actor);	
            		
            		int index = newLine.indexOf(")") + 1;

                  	while (index < tabIndex2) {
                       		 index = newLine.indexOf(")", index - 1) + 1;
                   	}
            		
            		String afterName = newLine.substring(tabIndex2).replaceAll(" ", "");
            		if (newLine.contains("(TV)") || afterName.contains("\"")){ 
            			// check whether the movie after the actor is a TV show 
            			continue;
            		}
            		else {
            			String movie = newLine.substring(tabIndex2,index);
        				movie = movie.replaceAll("\t", "");
        				movieHelperMethod(movie, actor);
            		}
            	}
            	
            	else {           // this line contains the movie only
            		if (newLine.contains("(TV)") || newLine.contains("\"")) {
            			continue;   // skip the TV shows
            		}
            		String newMovie = newLine.substring(0,newLine.indexOf(")") + 1);
                    newMovie = newMovie.replaceAll("\t", "");
                    movieHelperMethod(newMovie, actor);
            	}
            }
            removeTVActor (actor);
        }
    }

    /**
     * Add the MoviesNode to the ActorsNode's neighbor list
     * Add the ActorsNode to the MoviesNode's neighbor list
     * @param movie: input movie's name
     * @param actor: input ActorsNode
     */
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
    
    /**
     * Remove the actor that only stars in TV shows
     * by removing any ActorsNode that has no neighbor 
     * by the time the new actor is found
     * @param actor: input actor to check whether it should be removed from the Graph
     */
    private void removeTVActor (ActorsNode actor) {
    	if (actor != null){
    		ArrayList<MoviesNode> a = actor.getNeighbors();
    		if (actor.getNeighbors().size() == 0) {
            	actormap.remove(actor.getName());
            }
    	}
    }

}
