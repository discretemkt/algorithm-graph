package mkt.graph.impl;

import mkt.graph.Graph;

/**
 * 
 * @since 0.0.1
 * @author mkt
 */
public class GraphFactory {
    
    public static <V> Graph<V> createGraph() {
        return new GraphImpl<>();
    }
    
}
