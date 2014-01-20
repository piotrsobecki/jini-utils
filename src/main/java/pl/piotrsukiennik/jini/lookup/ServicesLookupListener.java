package pl.piotrsukiennik.jini.lookup;


import java.util.List;

/**
 * Service Lookup listener for providing list of looked up services object when found on service registrar.
 *
 * @author Piotr Sukiennik
 * @date 19.01.14
 */
public interface ServicesLookupListener<T> {
    void receive( List<T> services );
}
