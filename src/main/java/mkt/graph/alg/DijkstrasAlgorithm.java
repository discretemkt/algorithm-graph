package mkt.graph.alg;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
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
    
    public static <V> List<V> findShortestPath(Graph<V> graph, V source, V destination) throws IllegalArgumentException, IllegalStateException, PathNotFoundException {
        if (graph == null || source == null || destination == null) {
            throw new IllegalArgumentException();
        }
        Set<V> vertices = graph.getVertices();
        if (!vertices.contains(source) || !vertices.contains(destination)) {
            throw new IllegalArgumentException();
        }
        Map<V, Map<V, Double>> adjacencyTable = new HashMap<>();
        Boolean directed = null;
        Boolean weighted = null;
        for (Edge<V> e : graph.getEdges()) {
            if (directed == null) {
                directed = e.isDirected();
            }
            if (directed && !e.isDirected() || !directed && e.isDirected()) {
                throw new IllegalStateException("This graph contains both directed and undirected edges, and it is hard to solve the shortest path problem so far.");
            }
            if (weighted == null) {
                weighted = e.isWeighted();
            }
            if (weighted && !e.isWeighted() || !weighted && e.isWeighted()) {
                throw new IllegalStateException("This graph contains both weighted and unweighted edges, and it is hard to solve the shortest path problem so far.");
            }
            if (e.isWeighted() && e.getWeight() < 0.0) {
                throw new IllegalStateException("This graph contains an edge whose weight is less than zero, and it is hard to solve the shortest path problem so far.");
            }
            V v1 = e.getEndpoints().getFirst();
            V v2 = e.getEndpoints().getSecond();
            if (!adjacencyTable.keySet().contains(v1)) {
                adjacencyTable.put(v1, new HashMap<V, Double>());
            }
            Map<V, Double> adj = adjacencyTable.get(v1);
            Double w = adj.get(v2);
            if (w == null || weighted && e.getWeight() < w) {
                adj.put(v2, weighted ? e.getWeight() : 1.0);
                if (!directed) {
                    if (!adjacencyTable.containsKey(v2)) {
                        adjacencyTable.put(v2, new HashMap<V, Double>());
                    }
                    adjacencyTable.get(v2).put(v1, weighted ? e.getWeight() : 1.0);
                }
            }
        }
        Map<V, Double> distanceTable = new HashMap<>();
        distanceTable.put(source, 0.0);
        Map<V, V> predecessorTable = new HashMap<>();
        Queue<V> queue = new PriorityQueue<>(vertices.size() * 4 / 3 + 1, new DistanceComparator<>(distanceTable));
        for (V v : vertices) {
            queue.offer(v);
        }
        while (!queue.isEmpty()) {
            V v1 = queue.poll();
            if (v1.equals(destination) || !distanceTable.containsKey(v1)) {
                break;
            }
            Double d1 = distanceTable.get(v1);
            Map<V, Double> adj = adjacencyTable.get(v1);
            for (V v2 : adj.keySet()) {
                Double w = adj.get(v2);
                Double d2 = distanceTable.get(v2);
                if (d2 == null || d1 + w < d2) {
                    queue.remove(v2);
                    distanceTable.put(v2, d1 + w);
                    predecessorTable.put(v2, v1);
                    queue.offer(v2);
                }
            }
        }
        List<V> path = new LinkedList<>();
        for (V v = destination; v != null; v = predecessorTable.get(v)) {
            path.add(0, v);
        }
        if (!path.get(0).equals(source)) {
            throw new PathNotFoundException();
        }
        return path;
    }
    
}
