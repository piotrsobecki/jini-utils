package pl.piotrsukiennik.jini.discovery;

import net.jini.core.discovery.LookupLocator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.piotrsukiennik.jini.exception.DiscoveryFailedException;

import java.io.IOException;

/**
 * Utility class used in creation of UnicastDiscovery of service registrars.
 *
 * @author Piotr Sukiennik
 * @date 19.01.14
 */
public class UnicastDiscoveryUtil extends DiscoveryUtil implements Discovery {
    private static final Logger LOG = LoggerFactory.getLogger( UnicastDiscoveryUtil.class );

    private UnicastDiscoveryUtil( String locatorUrl ) throws IOException, ClassNotFoundException {
        LookupLocator lookupLocator = new LookupLocator( locatorUrl );
        registrarList.add( lookupLocator.getRegistrar() );
    }


    public static synchronized Discovery getDiscovery( String locatorUrl ) {
        try {
            return new UnicastDiscoveryUtil( locatorUrl );
        }
        catch ( IOException e ) {
            if ( LOG.isErrorEnabled() ) {
                LOG.error( "Error occured while getting discovery. ", e );
            }
            throw new DiscoveryFailedException( e );
        }
        catch ( ClassNotFoundException e ) {
            if ( LOG.isErrorEnabled() ) {
                LOG.error( "Error occured while getting discovery. ", e );
            }
            throw new DiscoveryFailedException( e );
        }
    }


}
