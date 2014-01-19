package pl.piotrsukiennik.jini.lookup;

import pl.piotrsukiennik.jini.discovery.MulticastDiscovery;
import pl.piotrsukiennik.jini.exception.LookupFailedException;
import pl.piotrsukiennik.jini.util.ListUtil;

import java.util.List;

/**
 * @author Piotr Sukiennik
 * @date 19.01.14
 */
class WaitingLookupImpl<T extends MulticastDiscovery> extends LookupImpl<T> implements WaitingLookup<T> {

    public WaitingLookupImpl( T multicastDiscovery ) {
        super( multicastDiscovery );
    }

    @Override
    public <T1> T1 lookupRandom( Class<T1> clazz ) {
        return ListUtil.getRandom( lookupAll( clazz ) );
    }

    @Override
    public <T> List<T> lookupAll( Class<T> clazz ) {
        try {
            return super.lookupAll( clazz );
        }
        catch ( LookupFailedException e ) {
            discovery.waitForNewRegistrars();
            return lookupAll( clazz );
        }
    }
}
