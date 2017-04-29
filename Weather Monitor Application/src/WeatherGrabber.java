import java.lang.Exception;

import MelbourneWeather2.MelbourneWeather2Stub;
import MelbourneWeather2.MelbourneWeather2Stub.*;

/**
 * Created by Jack on 29/4/17.
 */
public class WeatherGrabber implements Runnable {
    private LocationSubject locationSubject;
    private MelbourneWeather2Stub melbourneWeatherService;
    private String[] locationArrayList;

    public WeatherGrabber(LocationSubject locationSubject) throws Exception {
        this.locationSubject = locationSubject;
        melbourneWeatherService = new MelbourneWeather2Stub();
    }


    @Override
    public void run() {
        while (true) {
            for (String location : locationSubject.getObserverHashMap().keySet()) {
                try {
                    String[] rainfall = grabRainFall(location);
                    String[] temperature = grabTemperature(location);
                    locationSubject.updateWeather(location, temperature[0], temperature[1], rainfall[1]);
                } catch (Exception ex) {
                    LocationObserver aLocation = (LocationObserver) locationSubject.getObserverHashMap().get(location);
                    locationSubject.updateWeather(location, aLocation.getTimeStamp() + " update Fail!!", aLocation.getTemperature(), aLocation.getRainfall());
                }
            }
            try {
                Thread.sleep(5 * 60 * 1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Observer newLocationObserver(String location) throws Exception {
        String[] rainfall = grabRainFall(location);
        String[] temperature = grabTemperature(location);
        return new LocationObserver(locationSubject, location, temperature[0], temperature[1], rainfall[1]);
    }

    private String[] grabRainFall(String location) throws Exception {
        GetRainfall RainfallRequest = new GetRainfall();
        RainfallRequest.setLocation(location);
        GetRainfallResponse RainfallResponse = melbourneWeatherService.getRainfall(RainfallRequest);
        return RainfallResponse.get_return();
    }

    private String[] grabTemperature(String location) throws Exception {
        GetTemperature TemperatureRequest = new GetTemperature();
        TemperatureRequest.setLocation(location);
        GetTemperatureResponse TemperatureResponse = melbourneWeatherService.getTemperature(TemperatureRequest);
        return TemperatureResponse.get_return();
    }

    public String[] grabLocations() throws Exception {
        GetLocationsResponse LocationsResponse = melbourneWeatherService.getLocations();
        locationArrayList = LocationsResponse.get_return();
        return locationArrayList;
    }

}
