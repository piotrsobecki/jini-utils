package pl.piotrsukiennik.jini.exception;

/**
 * Exception thrown when errors with lookup occurs.
 *
 * @author Piotr Sukiennik
 * @date 19.01.14
 */
public class LookupFailedException extends Exception {
    public LookupFailedException() {
        super();
    }

    public LookupFailedException( String message ) {
        super( message );
    }

    public LookupFailedException( String message, Throwable cause ) {
        super( message, cause );
    }

    public LookupFailedException( Throwable cause ) {
        super( cause );
    }

    protected LookupFailedException( String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace ) {
        super( message, cause, enableSuppression, writableStackTrace );
    }
}
