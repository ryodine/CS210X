import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class GraphSearchEngineImpl implements GraphSearchEngine{

    HashMap<Node, LinkedList<Node>> paths = new HashMap<>();

    /**
     * A Dynamic programming breadth first search approach to finding the shortest path
     *
     * @param s the start node.
     * @param t the target node.
     * @return a list of the shortest path between two nodes
     */
    public List<Node> findShortestPath(Node s, Node t) {

        paths.clear();

        Queue<Node> tosee = new ConcurrentLinkedQueue<>();
        List<Node> visited = new ArrayList<>();

        //add the first node to the queue initially
        tosee.add(t);

        //add the initial 1-length path to the memoization table
        LinkedList<Node> rootlist = new LinkedList<>();
        rootlist.add(t);
        paths.put(t, rootlist);

        //BFS (break if done with list or node found)
        while (tosee.size() > 0 && !visited.contains(s)) {
            Node thisnode = tosee.poll();
            visited.add(thisnode);

            //add neighbors to the queue
            for (Node neighbor : thisnode.getNeighbors()) {
                if (!visited.contains(neighbor)) {
                    tosee.add(neighbor);
                }
            }

            //do the memoization
            addDepth(thisnode);
        }

        //By now, the memoization table is complete, and the shortest path is automatically in there
        if (paths.get(s) != null) {

            //reverse it because i did it backwards
            Collections.reverse(paths.get(s));
        }


        return paths.get(s);

    }

    /**
     * Private helper to add/update depth to the Memoization table
     * @param n node to add neighbor paths
     */
    private void addDepth(Node n) {
        for (Node node : n.getNeighbors()) {
            //Add the path if there isnt one for the node
            //If there is one for the node, check if the new path is better - if so, add it.
            if (paths.get(node) == null || paths.get(node).size() > (paths.get(n).size() + 1)) {
                LinkedList<Node> newpath = (LinkedList<Node>) paths.get(n).clone();
                newpath.add(node);
                paths.put(node, newpath);
            }
        }
    }
}