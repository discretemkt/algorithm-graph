package mkt.graph.impl;

import mkt.graph.Edge;
import mkt.graph.Pair;

/**
 * 
 * @param <V>
 * @since 0.0.1
 * @author mkt
 */
class EdgeImpl<V> implements Edge<V> {
    
    private static class EndpointPair<V> implements Pair<V> {
        
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
    
    private final V vertex1;
    private final V vertex2;
    private final boolean directed;
    
    private Double weight;
    
    public EdgeImpl(V vertex1, V vertex2, boolean directed) throws IllegalArgumentException {
        if (vertex1 == null || vertex2 == null) {
            throw new IllegalArgumentException();
        }
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.directed = directed;
    }
    
    @Override
    public Pair<V> getEndpoints() {
        return new EndpointPair(vertex1, vertex2);
    }
    
    @Override
    public boolean isDirected() {
        return directed;
    }
    
    @Override
    public void setWeight(double weight) throws IllegalArgumentException {
        if (Double.valueOf(weight).equals(Double.NaN)) {
            throw new IllegalArgumentException();
        }
        this.weight = weight;
    }
    
    @Override
    public void resetWeight() {
        weight = null;
    }
    
    @Override
    public boolean isWeighted() {
        return weight != null;
    }
    
    @Override
    public double getWeight() throws IllegalStateException {
        if (weight == null) {
            throw new IllegalStateException();
        }
        return weight;
    }
    
}
