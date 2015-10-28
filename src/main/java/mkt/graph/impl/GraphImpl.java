package mkt.graph.impl;

import java.util.HashSet;
import java.util.Set;
import mkt.graph.Edge;
import mkt.graph.Graph;

/**
 * 
 * @param <V>
 * @since 0.0.1
 * @author mkt
 */
class GraphImpl<V> implements Graph<V> {
    
    private final Set<V> vertexSet = new HashSet<>();
    private final Set<Edge<V>> edgeSet = new HashSet<>();
    
    @Override
    public void add(V node) throws IllegalArgumentException {
        if (node == null) {
            throw new IllegalArgumentException("Node to be added must not be null.");
        }
        if (vertexSet.contains(node)) {
            return;
        }
        vertexSet.add(node);
    }
    
    @Override
    public Edge<V> connect(V node1, V node2) throws IllegalArgumentException {
        Edge<V> edge = new EdgeImpl<>(node1, node2);
        add(node1);
        add(node2);
        edgeSet.add(edge);
        return edge;
    }
    
    @Override
    public Set<V> getVertices() {
        return new HashSet<>(vertexSet);
    }
    
    @Override
    public Set<Edge<V>> getEdges() {
        return new HashSet<>(edgeSet);
    }
    
}
