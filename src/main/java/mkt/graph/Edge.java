package mkt.graph;

/**
 * 
 * @param <V>
 * @since 0.0.1
 * @author mkt
 */
public interface Edge<V> {
    
    public Pair<V> getEndpoints();
    
    public boolean isDirected();
    
    public void setWeight(double weight) throws IllegalArgumentException;
    
    public void resetWeight();
    
    public boolean isWeighted();
    
    public double getWeight() throws IllegalStateException;
    
}
