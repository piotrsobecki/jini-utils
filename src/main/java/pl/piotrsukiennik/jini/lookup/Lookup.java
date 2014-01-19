package pl.piotrsukiennik.jini.lookup;

import pl.piotrsukiennik.jini.discovery.Discovery;
import pl.piotrsukiennik.jini.exception.LookupFailedException;

import java.util.List;

/**
 * Lookup service defining simple interface for lookup of servics across {@link Discovery} objects.
 *
 * @author Piotr Sukiennik
 * @date 19.01.14
 */
public interface Lookup<T extends Discovery> {
    /**
     * Returns random service of class given as a parameter found via Discovery of Service Registrars.
     *
     * @param clazz service class to find across discovery
     * @param <T>   type of the service
     * @return service proxy created with jini of clazz
     * @throws LookupFailedException if no services are found
     */
    <T> T lookupRandom( Class<T> clazz ) throws LookupFailedException;

    /**
     * Returns all services of class given as a parameter found via Discovery of Service Registrars.
     *
     * @param clazz service class to find across discovery
     * @param <T>   type of the service
     * @return service proxy created with jini of clazz
     * @throws LookupFailedException if no services are found
     */
    <T> List<T> lookupAll( Class<T> clazz ) throws LookupFailedException;
}
