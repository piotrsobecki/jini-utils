package pl.piotrsukiennik.jini.exception;

/**
 * Exception thrown when errors with discovery occurs.
 *
 * @author Piotr Sukiennik
 * @date 19.01.14
 */
public class DiscoveryFailedException extends RuntimeException {
    public DiscoveryFailedException() {
        super();
    }

    public DiscoveryFailedException( String message ) {
        super( message );
    }

    public DiscoveryFailedException( String message, Throwable cause ) {
        super( message, cause );
    }

    public DiscoveryFailedException( Throwable cause ) {
        super( cause );
    }

    protected DiscoveryFailedException( String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace ) {
        super( message, cause, enableSuppression, writableStackTrace );
    }
}
