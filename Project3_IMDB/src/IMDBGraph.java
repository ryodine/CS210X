
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

    private void parse(String filename) throws IOException{
        Scanner file = new Scanner(new File(filename), "ISO-8859-1");
        ActorsNode actor = null;
        
        while (file.hasNextLine()){
            String newLine = file.nextLine();
            int tabIndex2 = newLine.indexOf("\t");
            if (tabIndex2 >= 0 && newLine.contains("(") && newLine.contains(")")) {
                if (newLine.contains("(TV)") || newLine.contains("\"")){
                    if (tabIndex2 == 0) {
                        continue;
                    }
                    // skip this
                }
                
                if (newLine.trim().isEmpty()){
                    continue;
                    // skip this
                }
                
                if (tabIndex2 != 0) {
                	
                	
                	// Add more code to eliminate actors without any movies 
                    // 
                	if (actor != null){
                		ArrayList<MoviesNode> a = actor.getNeighbors();
                		
                		if (actor.getNeighbors().size() == 0){
                        	actormap.remove(actor.getName());
                        }
                		/*
                		for (int i = 0; i < a.size(); i++){
                    		System.out.println(a.get(i).getName());
                    	}
                    	*/
                	}
                	
                	/*
                    
                    
                    
                	
                    */
                    
                	
                    
                    // End of added code
                    
                	
                	
                    // this is the new actor
                    int tabIndex = newLine.indexOf("\t");
                    String name = newLine.substring(0,tabIndex);
                    //System.out.println("New actor: " + name);
                    
                 
                    
                    
                    actor = new ActorsNode(name);

                    actormap.put(name, actor);

                    int index = newLine.indexOf(")") + 1;

                    while (index < tabIndex) {
                        index = newLine.indexOf(")", index - 1) + 1;
                    }
                    // this is the movie
                    //System.out.println(newLine);
                    //System.out.println("ti:" + tabIndex);
                    //System.out.println(index);
                    String movie = newLine.substring(tabIndex,index);
                    if (newLine.contains("(TV)") || newLine.contains("\"")) {
                        continue;
                    } else {
                        movie = movie.replaceAll("\t", "");
                        //System.out.println(/*"Movie: " + */movie);
                        movieHelperMethod(movie, actor);
                    }
                }
                else {
                    // no new actor, just a movie
                    String newMovie = newLine.substring(0,newLine.indexOf(")") + 1);
                    newMovie = newMovie.replaceAll("\t", "");
                    //System.out.println(/*"Movie: " + */newMovie);
                    movieHelperMethod(newMovie, actor);
                }
            }
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

}
