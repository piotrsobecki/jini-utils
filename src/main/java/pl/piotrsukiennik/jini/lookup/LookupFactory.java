package pl.piotrsukiennik.jini.lookup;

import pl.piotrsukiennik.jini.discovery.Discovery;
import pl.piotrsukiennik.jini.discovery.MulticastDiscovery;

/**
 * Factory of Lookup objects, it should be used to receive {@link Lookup} object.
 *
 * @author Piotr Sukiennik
 * @date 19.01.14
 */
public class LookupFactory {

    public static <T extends Discovery> Lookup<T> getLookup( T discovery ) {
        return new LookupImpl<T>( discovery );
    }

    public static <T extends MulticastDiscovery> Lookup<T> getLookup( T discovery ) {
        return new LookupImpl<T>( discovery );
    }

    public static <T extends MulticastDiscovery> WaitingLookup<T> getWaitingLookup( T discovery ) {
        return new WaitingLookupImpl<T>( discovery );
    }

    private LookupFactory() {

    }
}
