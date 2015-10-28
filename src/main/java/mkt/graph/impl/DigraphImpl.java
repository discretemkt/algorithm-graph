package mkt.graph.impl;

import java.util.HashSet;
import java.util.Set;
import mkt.graph.Arc;
import mkt.graph.Digraph;

/**
 * 
 * @param <V>
 * @since 0.0.1
 * @author mkt
 */
class DigraphImpl<V> implements Digraph<V> {
    
    private final Set<V> vertexSet = new HashSet<>();
    private final Set<Arc<V>> arcSet = new HashSet<>();
    
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
    public Arc<V> connect(V head, V tail) throws IllegalArgumentException {
        Arc<V> arc = new ArcImpl(head, tail);
        add(head);
        add(tail);
        arcSet.add(arc);
        return arc;
    }
    
    @Override
    public Set<V> getVertices() {
        return new HashSet<>(vertexSet);
    }
    
    @Override
    public Set<Arc<V>> getArcs() {
        return new HashSet<>(arcSet);
    }
    
}
