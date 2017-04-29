import java.lang.Exception;

import MelbourneWeather2.MelbourneWeather2Stub;
import MelbourneWeather2.MelbourneWeather2Stub.*;

/**
 * Created by Jack on 28-Apr-17.
 */
public class Driver {


    public static void main(String[] args) throws Exception {


        LocationSubject locationSubject = new LocationSubject();

        locationSubject.attach(new LocationObserver(locationSubject,"Laverton","29/04/2017 11:59:04",Double.parseDouble("17.6"),Double.parseDouble("0.0")));
        locationSubject.attach(new LocationObserver(locationSubject,"Melbourne Airport","29/04/2017 11:59:04",Double.parseDouble("17.0"),Double.parseDouble("0.0")));
        locationSubject.attach(new LocationObserver(locationSubject,"Pound Creek","29/04/2017 11:59:04",Double.parseDouble("17.1"),Double.parseDouble("0.6")));

        locationSubject.updateWeather("Laverton","29/04/2017 12:08:04",Double.parseDouble("30.0"),Double.parseDouble("0.0"));
        locationSubject.updateWeather("Laverton","29/04/2017 12:09:04",Double.parseDouble("31.0"),Double.parseDouble("0.0"));
        locationSubject.updateWeather("Laverton","29/04/2017 12:010:04",Double.parseDouble("32.0"),Double.parseDouble("0.0"));

    }
}
