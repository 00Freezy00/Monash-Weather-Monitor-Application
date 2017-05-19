/**
 * Created by Jack on 12-May-17.
 */
public class Driver {

    public static void main(String[] args) throws Exception {
        LocationSubject locationSubject = new LocationSubject();
        final MelbourneWeatherTimeLapseGrabber MelbourneWeatherTimeLapseService = new MelbourneWeatherTimeLapseGrabber(locationSubject);
        String[] locations = MelbourneWeatherTimeLapseService.grabLocations();
        String[] weatherInfo = MelbourneWeatherTimeLapseService.grabWeather(locations[0]);
        System.out.print("Hi");
    }
}
