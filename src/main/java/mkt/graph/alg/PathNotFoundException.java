package mkt.graph.alg;

/**
 * 
 * @since 0.0.1
 * @author mkt
 */
public class PathNotFoundException extends RuntimeException {
    
    public PathNotFoundException() {
        super();
    }
    
    public PathNotFoundException(String message) {
        super(message);
    }
    
    public PathNotFoundException(Throwable cause) {
        super(cause);
    }
    
    public PathNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
