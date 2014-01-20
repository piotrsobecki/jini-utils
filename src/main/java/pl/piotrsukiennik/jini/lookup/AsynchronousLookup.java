package pl.piotrsukiennik.jini.lookup;

import pl.piotrsukiennik.jini.discovery.MulticastDiscovery;

/**
 * Asynchronous Lookup service defining simple interface for lookup of servics across {@link pl.piotrsukiennik.jini.discovery.MulticastDiscovery} objects.
 * lookup method call ServiceLookupListener methods when services of given class are found on Service Registrars.
 *
 * @author Piotr Sukiennik
 * @date 19.01.14
 */
public interface AsynchronousLookup<T extends MulticastDiscovery> {

    /**
     * Calls serviceLookupListener with random service of class given as a parameter found via Discovery of Service Registrars.
     *
     * @param clazz                 service class to find across discovery
     * @param <T>                   type of the service
     * @param serviceLookupListener service lookup listener notified when service is found
     */
    <T> void lookupRandom( Class<T> clazz, ServiceLookupListener<T> serviceLookupListener );

    /**
     * Calls serviceLookupListener with all services of class given as a parameter found via Discovery of Service Registrars.
     *
     * @param clazz                  service class to find across discovery
     * @param <T>                    type of the service
     * @param servicesLookupListener services lookup listener notified when services are found
     */
    <T> void lookupAll( Class<T> clazz, ServicesLookupListener<T> servicesLookupListener );
}
