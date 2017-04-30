import java.lang.Exception;

import MelbourneWeather2.MelbourneWeather2Stub;
import MelbourneWeather2.MelbourneWeather2Stub.*;

/**
 * Created by Jack on 28-Apr-17.
 */
public class Driver {


    public static void main(String[] args) throws Exception {


        LocationSubject locationSubject = new LocationSubject();

        String[] locations = locationSubject.getLocations();

        for (int i = 0; i < locations.length; i++) {
           locationSubject.attach(locationSubject.newLocationObserver(locations[i]));
        }

    }
}
