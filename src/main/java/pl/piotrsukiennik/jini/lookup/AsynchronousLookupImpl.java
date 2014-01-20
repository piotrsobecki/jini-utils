package pl.piotrsukiennik.jini.lookup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.piotrsukiennik.jini.discovery.MulticastDiscovery;
import pl.piotrsukiennik.jini.exception.LookupFailedException;
import pl.piotrsukiennik.jini.util.ListUtil;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author Piotr Sukiennik
 * @date 19.01.14
 */
class AsynchronousLookupImpl<T extends MulticastDiscovery> extends LookupImpl<T> implements AsynchronousLookup<T> {
    private static final Logger LOG = LoggerFactory.getLogger( AsynchronousLookupImpl.class );

    private static final Executor executor = Executors.newFixedThreadPool( 5 );

    public AsynchronousLookupImpl( T multicastDiscovery ) {
        super( multicastDiscovery );
    }

    @Override
    public <T> void lookupRandom( Class<T> clazz, final ServiceLookupListener<T> serviceLookupListener ) {
        lookupAll( clazz, new ServicesLookupListener<T>() {
            @Override
            public void receive( List<T> services ) {
                serviceLookupListener.receive( ListUtil.getRandom( services ) );
            }
        } );
    }

    @Override
    public <T> void lookupAll( final Class<T> clazz, final ServicesLookupListener<T> servicesLookupListener ) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                List<T> foundServices = null;
                while ( foundServices == null ) {
                    try {
                        foundServices = AsynchronousLookupImpl.super.lookupAll( clazz );
                    }
                    catch ( LookupFailedException e ) {
                        if ( LOG.isDebugEnabled() ) {
                            LOG.debug( String.format( "No services found of class [%s].", clazz ), e );
                        }
                        discovery.waitForNewRegistrars();
                    }
                }
                servicesLookupListener.receive( foundServices );
            }
        };
        executor.execute( runnable );
    }
}
