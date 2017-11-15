import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class GraphSearchEngineImpl implements GraphSearchEngine{

    HashMap<Node, LinkedList<Node>> paths = new HashMap<>();


    public List<Node> findShortestPath(Node s, Node t){

        paths.clear();

        Queue<Node> tosee = new ConcurrentLinkedQueue<>();
        List<Node> visited = new ArrayList<>();


        tosee.add(t);

        LinkedList<Node> rootlist = new LinkedList<>();
        rootlist.add(t);
        paths.put(t, rootlist);

        while (tosee.size() > 0 && !visited.contains(s)) {
            Node thisnode = tosee.poll();
            visited.add(thisnode);
            for (Node neighbor : thisnode.getNeighbors()) {
                if (!visited.contains(neighbor)) {
                    tosee.add(neighbor);
                }
            }
            addDepth(thisnode);
        }

        if (paths.get(s) != null) {
            Collections.reverse(paths.get(s));
        }
        return paths.get(s);

    }

    private void addDepth(Node n) {
        for (Node node : n.getNeighbors()) {
            if (paths.get(node) == null || paths.get(node).size() > (paths.get(n).size() + 1)) {
                LinkedList<Node> newpath = (LinkedList<Node>) paths.get(n).clone();
                newpath.add(node);
                paths.put(node, newpath);
            }
        }
    }
}