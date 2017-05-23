import MelbourneWeather2.MelbourneWeather2Stub;
import MelbourneWeather2.MelbourneWeather2Stub.*;

import java.lang.Exception;

/**
 * MelbourneWeatherGrabber.java
 * An API like class that provides all necessary methods of the service
 * Author: Yi Fei (Freya) Gao, Yun Hao (Jack) Zhang
 */
public class MelbourneWeatherGrabber extends WeatherGrabber {
    private LocationSubject locationSubject; //Store a reference of location subject
    private MelbourneWeather2Stub melbourneWeatherService; //Store a reference of melbourneWeatherService
    public static final String SOURCE_IDENTIFIER = "_MelbourneWeather2";

    /**
     * A init function that construct MelbourneWeatherGrabber
     *
     * @param locationSubject Main controller of observer pattern
     * @throws Exception Initialise melbourne weather service failed
     */
    public MelbourneWeatherGrabber(LocationSubject locationSubject) throws Exception {
        this.locationSubject = locationSubject;
        melbourneWeatherService = new MelbourneWeather2Stub();// Init melbourne weather service
    }


    /**
     * This method is for multithreading, it checks selected location every 5 mins
     */
    @Override
    public void run() {
        while (true) {
            for (String locationID : locationSubject.getObserverHashMap().keySet()) {
                if (locationID.endsWith(SOURCE_IDENTIFIER)) {
                    try {
                        String[] location = locationID.split("_");
                        String[] rainfall = grabRainFall(location[0]);
                        String[] temperature = grabTemperature(location[0]);
                        locationSubject.updateWeather(locationID, temperature[1], temperature[0], rainfall[1],rainfall[0]);
                    } catch (Exception ex) {
                        LocationObserver aLocation = (LocationObserver) locationSubject.getObserverHashMap().get(locationID);
                        locationSubject.updateWeather(locationID, aLocation.getTemperature(),aLocation.getTemperatureTimeStamp() + " update Fail!!", aLocation.getRainfall() , aLocation.getRainfallTimeStamp()+ " update Fail!!");//If the update fails, It won't remove the previous data
                    }
                }
            }
            try {
                Thread.sleep(5 * 60 * 1000);//Delay 5 mins
            } catch (Exception e) {
                //Try it again
            }
        }
    }


    /**
     * Grab rainfall by the location from the weather service
     *
     * @param location A String that represents the name of the location
     * @return An array 0 is timestamp, 1 is rainfall
     * @throws Exception Weather Service is unavailable, particularly rainFall
     */
    @Override
    public String[] grabRainFall(String location) throws Exception {
        GetRainfall RainfallRequest = new GetRainfall();
        RainfallRequest.setLocation(location);
        GetRainfallResponse RainfallResponse = melbourneWeatherService.getRainfall(RainfallRequest);
        return RainfallResponse.get_return();
    }

    /**
     * Grab temperature by the location from the weather service
     *
     * @param location A String that represents the name of the location
     * @return An array 0 is timestamp, 1 is temperature
     * @throws Exception Weather Service is unavailable, particularly Temperature
     */
    @Override
    public String[] grabTemperature(String location) throws Exception {
        GetTemperature TemperatureRequest = new GetTemperature();
        TemperatureRequest.setLocation(location);
        GetTemperatureResponse TemperatureResponse = melbourneWeatherService.getTemperature(TemperatureRequest);
        return TemperatureResponse.get_return();
    }

    /**
     * Get all of the location available from the weather service
     *
     * @return All of location in String array
     * @throws Exception Weather Service is unavailable, particularly location
     */
    @Override
    public String[] grabLocations() throws Exception {
        GetLocationsResponse LocationsResponse = melbourneWeatherService.getLocations();
        return LocationsResponse.get_return();
    }

}
