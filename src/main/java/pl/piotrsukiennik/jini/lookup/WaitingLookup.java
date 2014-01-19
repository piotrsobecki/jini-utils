package pl.piotrsukiennik.jini.lookup;

import pl.piotrsukiennik.jini.discovery.MulticastDiscovery;

import java.util.List;

/**
 * Waiting Lookup service defining simple interface for lookup of servics across {@link MulticastDiscovery} objects.
 * Interface method execution asserts finding services of pased type and when no service of given type are found, the method waits for new Services in ServiceRegistrars.
 *
 * @author Piotr Sukiennik
 * @date 19.01.14
 */
public interface WaitingLookup<T extends MulticastDiscovery> extends Lookup<T> {

    /**
     * Returns random service of class given as a parameter found via Discovery of Service Registrars.
     * If no services of given type are found - method halts and waits for new Services.
     *
     * @param clazz service class to find across discovery
     * @param <T>   type of the service
     * @return service proxy created with jini of clazz
     */
    <T> T lookupRandom( Class<T> clazz );

    /**
     * Returns all services of class given as a parameter found via Discovery of Service Registrars.
     * If no services of given type are found - method halts and waits for new Services.
     *
     * @param clazz service class to find across discovery
     * @param <T>   type of the service
     * @return service proxy created with jini of clazz
     */
    <T> List<T> lookupAll( Class<T> clazz );
}
