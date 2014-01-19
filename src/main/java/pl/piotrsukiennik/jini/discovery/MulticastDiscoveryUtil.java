package pl.piotrsukiennik.jini.discovery;

import net.jini.core.lookup.ServiceRegistrar;
import net.jini.discovery.DiscoveryEvent;
import net.jini.discovery.DiscoveryListener;
import net.jini.discovery.LookupDiscovery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.piotrsukiennik.jini.exception.DiscoveryFailedException;

import java.io.IOException;
import java.util.Arrays;

/**
 * Utility class used to simplify MulticastDiscovery of service registrars.
 *
 * @author Piotr Sukiennik
 * @date 19.01.14
 */
public class MulticastDiscoveryUtil extends DiscoveryUtil implements MulticastDiscovery {

    private static final Logger LOG = LoggerFactory.getLogger( MulticastDiscoveryUtil.class );

    private MulticastDiscoveryUtil( String[] groups ) throws IOException {
        LookupDiscovery lookupDiscovery = new LookupDiscovery( groups );
        lookupDiscovery.addDiscoveryListener( new MulticastDiscoveryUtil.DiscoveryListenerImpl() );
    }

    @Override
    public void waitForNewRegistrars() {
        synchronized ( registrarList ) {
            try {
                if ( LOG.isInfoEnabled() ) {
                    LOG.info( "Waiting for registrars..." );
                }
                registrarList.wait();
            }
            catch ( InterruptedException e ) {
                if ( LOG.isErrorEnabled() ) {
                    LOG.error( "Error occured while waiting for registrars. ", e );
                }
            }
        }
    }

    public static synchronized MulticastDiscovery getDiscovery( String[] groups ) {
        try {
            return new MulticastDiscoveryUtil( groups );
        }
        catch ( IOException e ) {
            if ( LOG.isErrorEnabled() ) {
                LOG.error( String.format( "Error occured while constructing multicast discovery groups %s. ", Arrays.toString( groups ) ), e );
            }
            throw new DiscoveryFailedException( e );
        }
    }

    public static synchronized MulticastDiscovery getDiscovery() {
        return getDiscovery( LookupDiscovery.ALL_GROUPS );
    }

    private class DiscoveryListenerImpl implements DiscoveryListener {
        @Override
        public void discovered( DiscoveryEvent discoveryEvent ) {
            ServiceRegistrar[] registrars = discoveryEvent.getRegistrars();
            registrarList.addAll( Arrays.asList( registrars ) );
            if ( LOG.isInfoEnabled() ) {
                LOG.info( String.format( "Found %d new Service Registrars.", registrars.length ) );
            }
            synchronized ( registrarList ) {
                registrarList.notifyAll();
            }
        }

        @Override
        public void discarded( DiscoveryEvent discoveryEvent ) {
            ServiceRegistrar[] registrars = discoveryEvent.getRegistrars();
            registrarList.removeAll( Arrays.asList( registrars ) );
            if ( LOG.isInfoEnabled() ) {
                LOG.info( String.format( "Discarded %d service Registrars.", registrars.length ) );
            }
        }
    }
}
