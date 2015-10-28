package mkt.graph.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import mkt.graph.Edge;

/**
 * 
 * @param <V>
 * @since 0.0.1
 * @author mkt
 */
class EdgeImpl<V> implements Edge<V> {
    
    private final Set<V> endpoints;
    
    private Double weight;
    
    public EdgeImpl(V endpoint1, V endpoint2) throws IllegalArgumentException {
        if (endpoint1 == null || endpoint2 == null) {
            throw new IllegalArgumentException("Endpoints must not be null.");
        }
        Set<V> es = new HashSet<>();
        es.add(endpoint1);
        es.add(endpoint2);
        endpoints = Collections.unmodifiableSet(es);
    }
    
    @Override
    public Set<V> getEndpoints() {
        return endpoints;
    }
    
    @Override
    public void setWeight(double weight) throws IllegalArgumentException {
        if (Double.valueOf(weight).equals(Double.NaN)) {
            throw new IllegalArgumentException("Weight must not be NaN.");
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
            throw new IllegalStateException("This Edge object currently represents an unweighted edge.");
        }
        return weight;
    }
    
}
