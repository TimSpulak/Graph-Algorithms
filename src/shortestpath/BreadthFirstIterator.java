package shortestpath;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstIterator<T extends Node> implements Iterator<Node> {
    private final Queue<Node> queue = new LinkedList<>();
    private final boolean[] visited;
    public static int[] indices;

    public BreadthFirstIterator(Graph graph, Node startNode) {
        this.visited = new boolean[graph.getNodeCount()];
        this.queue.add(startNode);
        indices = new int[graph.getNodeCount()];
    }

    @Override
    public boolean hasNext() {
        return !this.queue.isEmpty();
    }

    /**
     * Removes the first node of the queue and finds the node's neighbor-nodes.
     * Each neighbor-node will be given the currently observed node's index value incremented by 1.
     * @return -> currently observed node
     */
    @Override
    public Node next() {
        Node currentNode = this.queue.remove();
        visited[currentNode.getIndex()] = true;
        int currentNodeIdx = indices[currentNode.getIndex()];

        currentNode.neighborNodes().forEach(neighborNode -> {
            if (!visited[neighborNode.getIndex()] && !queue.contains(neighborNode)) {
                indices[neighborNode.getIndex()] = currentNodeIdx + 1;
                queue.add(neighborNode);
            }
        });
        return currentNode;
    }
}
