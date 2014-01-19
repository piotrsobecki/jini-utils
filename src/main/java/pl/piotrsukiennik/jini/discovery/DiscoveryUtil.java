package pl.piotrsukiennik.jini.discovery;

import net.jini.core.lookup.ServiceRegistrar;
import org.rioproject.url.artifact.ArtifactURLStreamHandlerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.rmi.RMISecurityManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Common Discovery class for DiscoveryUtil objects.
 *
 * @author Piotr Sukiennik
 * @date 19.01.14
 */
public abstract class DiscoveryUtil implements Discovery {

    private static final Logger LOG = LoggerFactory.getLogger( DiscoveryUtil.class );


    /**
     * Initialization block for RMI communication.
     * Uses policy.all file for java.security.policy.
     */
    static {
        URL.setURLStreamHandlerFactory( new ArtifactURLStreamHandlerFactory() ); //support for artifact protocol
        ClassLoader cl = DiscoveryUtil.class.getClassLoader();
        URL policyURL = cl.getResource( "policy.all" );
        if ( LOG.isInfoEnabled() ) {
            LOG.info( String.format( "Using policy file defined in: %s.", policyURL.toString() ) );
        }
        System.setProperty( "java.security.policy", policyURL.toString() );
        System.setSecurityManager( new RMISecurityManager() );
    }

    protected final List<ServiceRegistrar> registrarList = new ArrayList<ServiceRegistrar>();

    public List<ServiceRegistrar> getRegistrars() {
        return registrarList;
    }

}
