package mkt.graph;

import java.util.Set;

/**
 * 
 * @param <V>
 * @since 0.0.1
 * @author mkt
 */
public interface Graph<V> {
    
    public void add(V node) throws IllegalArgumentException;
    
    public Edge<V> connect(V node1, V node2) throws IllegalArgumentException;
    
    public Set<V> getVertices();
    
    public Set<Edge<V>> getEdges();
    
}
