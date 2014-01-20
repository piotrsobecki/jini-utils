package pl.piotrsukiennik.jini.lookup;


/**
 * Service Lookup listener for providing service object when found on service registrar.
 *
 * @author Piotr Sukiennik
 * @date 19.01.14
 */
public interface ServiceLookupListener<T> {
    void receive( T service );
}
