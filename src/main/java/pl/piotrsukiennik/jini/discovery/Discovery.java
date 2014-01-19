package pl.piotrsukiennik.jini.discovery;

import net.jini.core.lookup.ServiceRegistrar;

import java.util.List;

/**
 * Interface used to simplyfy discovery of registrars.
 *
 * @author Piotr Sukiennik
 * @date 19.01.14
 */
public interface Discovery {
    /**
     * Lists of all registrars found via Discovery.
     *
     * @return list of service registrars
     */
    List<ServiceRegistrar> getRegistrars();
}
