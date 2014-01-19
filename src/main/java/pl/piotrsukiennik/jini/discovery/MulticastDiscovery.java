package pl.piotrsukiennik.jini.discovery;


/**
 * Interface used to simplify detection of service registrars via MulicastDiscovery.
 *
 * @author Piotr Sukiennik
 * @date 19.01.14
 */
public interface MulticastDiscovery extends Discovery {

    /**
     * Synchronously waits for new registrars. Method will execute when new regisrars will occur in registrar List.
     */
    void waitForNewRegistrars();
}
