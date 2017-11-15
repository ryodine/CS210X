import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class GraphSearchEngineImpl implements GraphSearchEngine{

    public AbstractMap<Node, Integer> breadthFirstSearch (Node s, Node t){
        List<Node> visitedNodes = new ArrayList<Node>();
        Queue<Node> nodesToVisit = new ConcurrentLinkedQueue<Node>();

        nodesToVisit.add(s);
        while(nodesToVisit.size() > 0){
            Node n = nodesToVisit.remove();
            visitedNodes.add(n);

            for(Node e: n.getNeighbors()){
                if(e == t){
                    visitedNodes.add(e);
                    return visitedNodes;
                }
                if(!nodesToVisit.contains(e) && !visitedNodes.contains(e)){
                    nodesToVisit.add(e);
                }
            }
        }
        return null;
    }

    public List<Node> findShortestPath(Node s, Node t){
        List<Node> visitedNodes = breadthFirstSearch(s, t);

        if(visitedNodes == null){
            return null;
        }

        List<Node> path = new ArrayList<Node>();
        path.add(0, t);

    }
}
