package mkt.graph;

import java.util.Set;

/**
 * 
 * @param <V>
 * @since 0.0.1
 * @author mkt
 */
public interface Digraph<V> {
    
    public void add(V node) throws IllegalArgumentException;
    
    public Arc<V> connect(V head, V tail) throws IllegalArgumentException;
    
    public Set<V> getVertices();
    
    public Set<Arc<V>> getArcs();
    
}
