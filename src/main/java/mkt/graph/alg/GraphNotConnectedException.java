package mkt.graph.alg;

/**
 * 
 * @since 0.0.1
 * @author mkt
 */
public class GraphNotConnectedException extends RuntimeException {
    
    public GraphNotConnectedException() {
        super();
    }
    
    public GraphNotConnectedException(String message) {
        super(message);
    }
    
    public GraphNotConnectedException(Throwable cause) {
        super(cause);
    }
    
    public GraphNotConnectedException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
