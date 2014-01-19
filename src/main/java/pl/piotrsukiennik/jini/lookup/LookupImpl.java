package pl.piotrsukiennik.jini.lookup;

import net.jini.core.lookup.ServiceRegistrar;
import net.jini.core.lookup.ServiceTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.piotrsukiennik.jini.discovery.Discovery;
import pl.piotrsukiennik.jini.exception.LookupFailedException;
import pl.piotrsukiennik.jini.util.ListUtil;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Piotr Sukiennik
 * @date 19.01.14
 */
class LookupImpl<T extends Discovery> implements Lookup<T> {

    private static final Logger LOG = LoggerFactory.getLogger( LookupImpl.class );

    protected T discovery;

    public LookupImpl( T discovery ) {
        this.discovery = discovery;
    }

    @Override
    public <T> T lookupRandom( Class<T> clazz ) throws LookupFailedException {
        return ListUtil.getRandom( lookupAll( clazz ) );
    }

    @Override
    public <T> List<T> lookupAll( Class<T> clazz ) throws LookupFailedException {
        ServiceTemplate template = new ServiceTemplate( null, new Class[] { clazz }, null );
        List<T> list = new ArrayList<T>();
        for ( ServiceRegistrar serviceRegistrar : discovery.getRegistrars() ) {
            try {
                T t = (T) serviceRegistrar.lookup( template );
                if ( t != null ) {
                    list.add( t );
                }
            }
            catch ( RemoteException e ) {
                if ( LOG.isErrorEnabled() ) {
                    LOG.error( String.format( "Exception occured while looking up service of class [%s].", clazz ), e );
                }
            }
        }
        if ( list.isEmpty() ) {
            if ( LOG.isInfoEnabled() ) {
                LOG.info( String.format( "Lookup found no services of class [%s].", clazz ) );
            }
            throw new LookupFailedException( "No such services found!" );
        }
        return list;
    }
}
