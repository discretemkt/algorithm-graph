package mkt.graph.impl;

import mkt.graph.Pair;

/**
 * 
 * @param <V>
 * @since 0.0.1
 * @author mkt
 */
class EndpointPair<V> implements Pair<V> {
    
    private final V vertex1;
    private final V vertex2;
    
    public EndpointPair(V vertex1, V vertex2) {
        assert vertex1 != null && vertex2 != null;
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
    }
    
    @Override
    public V getFirst() {
        return vertex1;
    }
    
    @Override
    public V getSecond() {
        return vertex2;
    }
    
}
