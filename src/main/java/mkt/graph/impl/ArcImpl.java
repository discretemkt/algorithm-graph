package mkt.graph.impl;

import mkt.graph.Arc;

/**
 * 
 * @param <V>
 * @since 0.0.1
 * @author mkt
 */
class ArcImpl<V> implements Arc<V> {
    
    private final V head;
    private final V tail;
    
    private Double weight;
    
    public ArcImpl(V head, V tail) throws IllegalArgumentException {
        if (head == null || tail == null) {
            throw new IllegalArgumentException("Endpoint nodes must not be null.");
        }
        this.head = head;
        this.tail = tail;
    }
    
    @Override
    public V getHead() {
        return head;
    }
    
    @Override
    public V getTail() {
        return tail;
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
            throw new IllegalStateException("This Arc object currently reprensents an unweighted arc.");
        }
        return weight;
    }
    
}
