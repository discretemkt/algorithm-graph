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
    
    private final Set<V> vertices = new HashSet<>();
    private final Set<Edge<V>> edges = new HashSet<>();
    
    public GraphImpl() {}
    
    @Override
    public void add(V vertex) throws IllegalArgumentException {
        if (vertex == null) {
            throw new IllegalArgumentException();
        }
        if (!vertices.contains(vertex)) {
            vertices.add(vertex);
        }
    }
    
    @Override
    public Edge<V> connect(V vertex1, V vertex2) throws IllegalArgumentException {
        return connect(vertex1, vertex2, false);
    }
    
    @Override
    public Edge<V> connect(V vertex1, V vertex2, boolean directed) throws IllegalArgumentException {
        Edge<V> edge = new EdgeImpl(vertex1, vertex2, directed);
        add(vertex1);
        add(vertex2);
        edges.add(edge);
        return edge;
    }
    
    @Override
    public Set<V> getVertices() {
        return new HashSet<>(vertices);
    }
    
    @Override
    public Set<Edge<V>> getEdges() {
        return new HashSet<>(edges);
    }
    
}
