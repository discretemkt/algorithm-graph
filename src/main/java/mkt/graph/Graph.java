package mkt.graph;

import java.util.Set;

/**
 * 
 * @param <V>
 * @since 0.0.1
 * @author mkt
 */
public interface Graph<V> {
    
    public void add(V vertex) throws IllegalArgumentException;
    
    public Edge<V> connect(V vertex1, V vertex2) throws IllegalArgumentException;
    
    public Edge<V> connect(V vertex1, V vertex2, boolean directed) throws IllegalArgumentException;
    
    public Set<V> getVertices();
    
    public Set<Edge<V>> getEdges();
    
}
