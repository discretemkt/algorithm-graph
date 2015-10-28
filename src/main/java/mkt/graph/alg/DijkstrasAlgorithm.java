package mkt.graph.alg;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import mkt.graph.Arc;
import mkt.graph.Digraph;
import mkt.graph.Edge;
import mkt.graph.Graph;

/**
 * 
 * @since 0.0.1
 * @author mkt
 */
public class DijkstrasAlgorithm {
    
    private static class DistanceComparator<V> implements Comparator<V> {
        
        private final Map<V, Double> distanceTable;
        
        public DistanceComparator(Map<V, Double> distanceTable) {
            assert distanceTable != null;
            this.distanceTable = distanceTable;
        }
        
        @Override
        public int compare(V v1, V v2) {
            Double d1 = distanceTable.get(v1);
            Double d2 = distanceTable.get(v2);
            if (d1 == null) {
                if (d2 == null) {
                    return 0;
                }
                return 1;
            }
            if (d2 == null) {
                return -1;
            }
            return d1.compareTo(d2);
        }
        
    }
    
    private static <V> Map<V, Map<V, Double>> createAdjacencyTable(Graph<V> graph) throws IllegalStateException {
        assert graph != null;
        Boolean graphIsWeighted = null;
        Map<V, Map<V, Double>> adjacencyTable = new HashMap<>();
        for (Edge<V> edge : graph.getEdges()) {
            if (graphIsWeighted == null) {
                graphIsWeighted = edge.isWeighted();
            }
            if (graphIsWeighted && !edge.isWeighted() || !graphIsWeighted && edge.isWeighted()) {
                throw new IllegalStateException("Both weighted and unweighted edges are found.");
            }
            if (edge.isWeighted() && edge.getWeight() < 0.0) {
                throw new IllegalStateException("An edge with negative weight is found.");
            }
            V end1 = (V) edge.getEndpoints().toArray()[0];
            V end2 = (V) edge.getEndpoints().toArray()[1];
            if (!adjacencyTable.containsKey(end1)) {
                adjacencyTable.put(end1, new HashMap<V, Double>());
            }
            if (!adjacencyTable.containsKey(end2)) {
                adjacencyTable.put(end2, new HashMap<V, Double>());
            }
            Map<V, Double> adjacents = adjacencyTable.get(end1);
            Double w = adjacents.get(end2);
            if (graphIsWeighted) {
                if (w == null || edge.getWeight() < w) {
                    adjacents.put(end2, edge.getWeight());
                    adjacencyTable.get(end2).put(end1, edge.getWeight());
                }
            } else {
                if (w == null) {
                    adjacents.put(end2, 1.0);
                    adjacencyTable.get(end2).put(end1, 1.0);
                }
            }
        }
        return adjacencyTable;
    }
    
    private static <V> Map<V, Map<V, Double>> createAdjacencyTable(Digraph<V> graph) throws IllegalStateException {
        assert graph != null;
        Boolean graphIsWeighted = null;
        Map<V, Map<V, Double>> adjacencyTable = new HashMap<>();
        for (Arc<V> arc : graph.getArcs()) {
            if (graphIsWeighted == null) {
                graphIsWeighted = arc.isWeighted();
            }
            if (graphIsWeighted && !arc.isWeighted() || !graphIsWeighted && arc.isWeighted()) {
                throw new IllegalStateException("Both weighted and unweighted arcs are found.");
            }
            if (arc.isWeighted() && arc.getWeight() < 0.0) {
                throw new IllegalStateException("An arc with negative weight is found.");
            }
            V head = arc.getHead();
            V tail = arc.getTail();
            if (!adjacencyTable.containsKey(head)) {
                adjacencyTable.put(head, new HashMap<V, Double>());
            }
            Map<V, Double> adjacents = adjacencyTable.get(head);
            Double w = adjacents.get(tail);
            if (graphIsWeighted) {
                if (w == null || arc.getWeight() < w) {
                    adjacents.put(tail, arc.getWeight());
                }
            } else {
                if (w == null) {
                    adjacents.put(tail, 1.0);
                }
            }
        }
        return adjacencyTable;
    }
    
    private static <V> List<V> findShortestPath(Map<V, Map<V, Double>> adjacencyTable, V src, V dst) throws PathNotFoundException {
        Map<V, Double> distanceTable = new HashMap<>();
        distanceTable.put(src, 0.0);
        Map<V, V> predecessorTable = new HashMap<>();
        Queue<V> queue = new PriorityQueue<>(adjacencyTable.size() * 4 / 3 + 1, new DistanceComparator<>(distanceTable));
        for (V v : adjacencyTable.keySet()) {
            queue.offer(v);
        }
        while (!queue.isEmpty()) {
            V node = queue.poll();
            if (node.equals(dst)) {
                break;
            }
            Double d1 = distanceTable.get(node);
            if (d1 == null || Double.MAX_VALUE <= d1) {
                break;
            }
            Map<V, Double> adjacents = adjacencyTable.get(node);
            for (V v : adjacents.keySet()) {
                Double w = adjacents.get(v);
                Double d2 = distanceTable.get(v);
                if (d2 == null || d1 + w < d2) {
                    queue.remove(v);
                    distanceTable.put(v, d1 + w);
                    predecessorTable.put(v, node);
                    queue.offer(v);
                }
            }
        }
        List<V> path = new LinkedList<>();
        for (V v = dst; v != null; v = predecessorTable.get(v)) {
            path.add(0, v);
        }
        if (!path.get(0).equals(src)) {
            throw new PathNotFoundException();
        }
        return Collections.unmodifiableList(path);
    }
    
    public static <V> List<V> findShortestPath(Graph<V> graph, V src, V dst) throws IllegalArgumentException, IllegalStateException {
        if (graph == null || src == null || dst == null) {
            throw new IllegalArgumentException("Arguments must not be null.");
        }
        Set<V> vertexSet = graph.getVertices();
        if (!vertexSet.contains(src) || !vertexSet.contains(dst)) {
            throw new IllegalArgumentException("Any of source or destination is not contained in the given graph.");
        }
        return findShortestPath(createAdjacencyTable(graph), src, dst);
    }
    
    public static <V> List<V> findShortestPath(Digraph<V> graph, V src, V dst) throws IllegalArgumentException {
        if (graph == null || src == null || dst == null) {
            throw new IllegalArgumentException("Arguments must not be null.");
        }
        Set<V> vertexSet = graph.getVertices();
        if (!vertexSet.contains(src) || !vertexSet.contains(dst)) {
            throw new IllegalArgumentException("Any of source or destination is not contained in the given graph.");
        }
        return findShortestPath(createAdjacencyTable(graph), src, dst);
    }
    
}
