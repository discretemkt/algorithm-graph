package mkt.graph;

import java.util.Set;

/**
 * 
 * @param <V>
 * @since 0.0.1
 * @author mkt
 */
public interface Edge<V> {
    
    public Set<V> getEndpoints();
    
    public void setWeight(double weight) throws IllegalArgumentException;
    
    public void resetWeight();
    
    public boolean isWeighted();
    
    public double getWeight() throws IllegalStateException;
    
}
