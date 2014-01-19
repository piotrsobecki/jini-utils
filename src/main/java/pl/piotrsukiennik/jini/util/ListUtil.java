package pl.piotrsukiennik.jini.util;

import java.util.List;

/**
 * Utility class for Lists management.
 *
 * @author Piotr Sukiennik
 * @date 19.01.14
 */
public class ListUtil {

    /**
     * Returns random element of list.
     *
     * @param objects list of objects
     * @param <T>     object type
     * @return random object
     */
    public static <T> T getRandom( List<T> objects ) {
        return objects.get( (int) ( Math.random() * objects.size() ) );
    }

    private ListUtil() {
    }
}
