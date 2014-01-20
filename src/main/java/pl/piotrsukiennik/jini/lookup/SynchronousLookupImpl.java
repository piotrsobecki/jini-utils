package pl.piotrsukiennik.jini.lookup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.piotrsukiennik.jini.discovery.MulticastDiscovery;
import pl.piotrsukiennik.jini.exception.LookupFailedException;
import pl.piotrsukiennik.jini.util.ListUtil;

import java.util.List;

/**
 * @author Piotr Sukiennik
 * @date 19.01.14
 */
class SynchronousLookupImpl<T extends MulticastDiscovery> extends LookupImpl<T> implements SynchronousLookup<T> {
    private static final Logger LOG = LoggerFactory.getLogger( SynchronousLookupImpl.class );

    public SynchronousLookupImpl( T multicastDiscovery ) {
        super( multicastDiscovery );
    }

    @Override
    public <T> T lookupRandom( Class<T> clazz ) {
        return ListUtil.getRandom( lookupAll( clazz ) );
    }

    @Override
    public <T> List<T> lookupAll( Class<T> clazz ) {
        try {
            return super.lookupAll( clazz );
        }
        catch ( LookupFailedException e ) {
            if ( LOG.isDebugEnabled() ) {
                LOG.debug( String.format( "No services found of class [%s].", clazz ), e );
            }
            discovery.waitForNewRegistrars();
            return lookupAll( clazz );
        }
    }
}
