import java.io.IOException;

public class test {
	public static void main (String[] agrs){
		try{
			IMDBGraph actorsGraph = new IMDBActorsGraph("actresses_first_10000_lines2.list", "actors_first_10000_lines2.list");
		}
		catch(IOException e){
			System.out.println("Can't load data");
		}
	}
}
