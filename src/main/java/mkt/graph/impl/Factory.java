package mkt.graph.impl;

import mkt.graph.Digraph;
import mkt.graph.Graph;

/**
 * 
 * @since 0.0.1
 * @author mkt
 */
public class Factory {
    
    public static <V> Graph<V> createGraph() {
        return new GraphImpl<>();
    }
    
    public static <V> Digraph<V> createDigraph() {
        return new DigraphImpl<>();
    }
    
}
